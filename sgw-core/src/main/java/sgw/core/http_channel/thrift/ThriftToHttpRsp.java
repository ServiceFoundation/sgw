package sgw.core.http_channel.thrift;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;
import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sgw.core.data_convertor.FullHttpResponseGenerator;
import sgw.core.http_channel.HttpChannelContext;
import sgw.core.service_channel.thrift.ThriftCallWrapper;

import java.util.List;

public class ThriftToHttpRsp extends MessageToMessageEncoder<ThriftCallWrapper> {

    private final Logger logger = LoggerFactory.getLogger(ThriftToHttpRsp.class);

    private static final int INITIAL_BUFFER_SIZE = 128;
    private static final int MAX_BUFFER_SIZE = 1024*1024*1024;

    private HttpChannelContext httpCtx;

    public ThriftToHttpRsp(HttpChannelContext httpCtx) {
        this.httpCtx = httpCtx;
    }

    /**
     *
     * @param out only return a http response
     */
    @Override
    public void encode(ChannelHandlerContext ctx, ThriftCallWrapper wrapper, List<Object> out) throws Exception {
        httpCtx.put("response_convertor_handler_start", System.currentTimeMillis());
        TBase result = wrapper.getResult();
        FullHttpResponseGenerator responseGenerator = httpCtx.getResponseGenerator();
        logger.debug("Request {}: Converting Thrift response to Http response BY {}",
                httpCtx.getRequestId(), responseGenerator.getClass().getName());
        // get size
        int size = 0;
        while (result.fieldForId(size) != null) {
            size ++;
        }

        // construct array
        Object[] arr = new Object[size];
        while (size > 0) {
            -- size;
            TFieldIdEnum field = result.fieldForId(size);
            arr[size] = result.getFieldValue(field);
        }

        ByteBuf buf = ctx.alloc().ioBuffer(INITIAL_BUFFER_SIZE, MAX_BUFFER_SIZE);

        FullHttpResponse response;
        try {
            response = responseGenerator.generate(arr, buf);
        } catch (Exception e) {
            e.printStackTrace();
            // release buf
            int refCnt = ReferenceCountUtil.refCnt(buf);
            ReferenceCountUtil.release(refCnt);
            throw e;
        }
        out.add(response);
    }

}