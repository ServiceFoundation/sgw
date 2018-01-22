package sgw.core.service_channel.thrift;

import io.netty.buffer.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TTransport;
import sgw.core.service_channel.thrift.transport.BytebufWriteTransport;

public class ThriftEncoder extends MessageToByteEncoder<TWrapper> {
    /**
     *
     */
    private static final int INITIAL_BUFFER_SIZE = 128;
    private static final int MAX_BUFFER_SIZE = 1024*1024*1024;
    private final byte[] i32buf = new byte[4];

    /**
     * @param preferDirect whether using direct buffer as decode output.
     */
    public ThriftEncoder(boolean preferDirect) {
        super(preferDirect);
    }

    @Override
    public void encode(ChannelHandlerContext ctx, TWrapper wrapper, ByteBuf out) throws TException {
        ctx.channel().eventLoop();
        writeFrameBuffer(out, wrapper);
        writeSizeBuffer(out);
    }

    private void writeSizeBuffer(ByteBuf buf) {
        int frameSize = buf.readableBytes() - 4;
        TFramedTransport.encodeFrameSize(frameSize, i32buf);
        // this op doesnt change write/read index.
        buf.setBytes(0, i32buf);
    }

    private void writeFrameBuffer(ByteBuf buf, TWrapper wrapper) throws TException {
        TBase args = wrapper.getValue();
        String methodName = wrapper.getMethod();

        // Leave space to write frame size. Use the same Bytebuf to avoid data copy.
        buf.setIndex(0, 4);

        // write frame buffer
        TTransport transport = new BytebufWriteTransport(buf);
        TProtocol protocol = new TCompactProtocol.Factory().getProtocol(transport);
        protocol.writeMessageBegin(new TMessage(methodName, TMessageType.CALL, 0));
        args.write(protocol);
        protocol.writeMessageEnd();
    }

    /**
     * TODO: enable config
     * using default initial_capacity and max_frame_length
     */
    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, @SuppressWarnings("unused") TWrapper msg,
                                       boolean preferDirect) {
        if (preferDirect) {
            return ctx.alloc().ioBuffer(INITIAL_BUFFER_SIZE, MAX_BUFFER_SIZE);
        } else {
            return ctx.alloc().heapBuffer(INITIAL_BUFFER_SIZE, MAX_BUFFER_SIZE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}