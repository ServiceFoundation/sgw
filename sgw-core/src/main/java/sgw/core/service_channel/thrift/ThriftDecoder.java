package sgw.core.service_channel.thrift;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sgw.core.service_channel.RpcInvokerDef;
import sgw.core.service_channel.thrift.transport.ByteBufReadTransport;

import java.util.List;

public class ThriftDecoder extends ByteToMessageDecoder {

    private final Logger logger = LoggerFactory.getLogger(ThriftDecoder.class);

    private final byte[] i32buf = new byte[4];
    private int frameSize;
    private boolean sizeDecoded;
    private ThriftChannelContext thriftCtx;

    public ThriftDecoder(ThriftChannelContext thriftCtx) {
        sizeDecoded = false;
        this.thriftCtx = thriftCtx;
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        thriftCtx.setRpcRecvTime(System.currentTimeMillis());
        logger.debug("Request {}: Received Thrift response, start decoding Thrift response.",
                thriftCtx.getHttpRequestId());
        if (!sizeDecoded) {
            tryDecodeFrameSize(buf);
        }

        if (sizeDecoded && buf.readableBytes() >= frameSize) {
            ThriftCallWrapper wrapper = thriftCtx.getCallWrapper();
            decodeFrame(wrapper, buf);
            out.add(wrapper);
        }
    }

    private void tryDecodeFrameSize(ByteBuf buf) {
        if (buf.readableBytes() >= 4) {
            // read frame size
            buf.readBytes(i32buf, 0, 4);
            frameSize = TFramedTransport.decodeFrameSize(i32buf);
            sizeDecoded = true;
        }
    }

    private void decodeFrame(ThriftCallWrapper wrapper, ByteBuf buf) throws Exception {
        String serviceName = wrapper.getServiceName();

        TTransport transport = new ByteBufReadTransport(buf);
        TProtocol basicProtocol = new TCompactProtocol.Factory().getProtocol(transport);
        TProtocol protocol = new TMultiplexedProtocol(basicProtocol, serviceName);

        TBase result = wrapper.getResult();
        TMessage msg = protocol.readMessageBegin();
        if (msg.type == TMessageType.EXCEPTION) {
            TApplicationException x = new TApplicationException();
            x.read(protocol);
            protocol.readMessageEnd();
            throw x;
        }
        result.read(protocol);
        protocol.readMessageEnd();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
