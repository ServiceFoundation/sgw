package sgw.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sgw.core.routing.Router;
import sgw.core.services.RpcInvokerDef;

import java.net.URI;

public class HttpServerHandler extends ChannelInboundHandlerAdapter{

    private final Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);
    private HttpPostRequestDecoder postDecoder;
    private boolean readingChunks;
    private HttpRequest request;
    private Router router;
    private static final HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);

    public void setRouter(Router router) {
        this.router = router;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info(msg.getClass().getName());
        /**
         * msg types:
         * {@link HttpRequest}
         * {@link HttpContent}
         */
        if (msg instanceof HttpRequest) {
            HttpRequest request = this.request = (HttpRequest) msg;
            HttpMethod method = request.method();
            URI uri = new URI(request.uri());

            logger.info("HttpRequest Method : {}", method);
            logger.info("HttpRequest URI: {}", uri.toString());

            HttpRequestDef httpRequestDef = new HttpRequestDef(request);
            RpcInvokerDef invokerDef = router.getRpcInvokerDef(httpRequestDef);
        }

        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            HttpMethod method = request.method();
            URI uri = new URI(request.uri());

            logger.info("HttpRequest Method: {}", method);
            logger.info("HttpRequest URI: {}", uri.toString());

            if (method.equals(HttpMethod.GET)) {
                writeResponse(ctx.channel());
                return;
            }

            if (method.equals(HttpMethod.POST)) {
                postDecoder = new HttpPostRequestDecoder(factory, request);
                readingChunks = HttpUtil.isTransferEncodingChunked(request);
            }
        }

        if (msg instanceof HttpContent) {
            if (msg == LastHttpContent.EMPTY_LAST_CONTENT)
                return;

            // New chunk has arrived
            HttpContent chunk = (HttpContent) msg;

            // if it is the last chunk, swith `readingChunks`
            if (chunk instanceof LastHttpContent) {
                writeResponse(ctx.channel());
                readingChunks = false;
                reset();
                return;
            }

            // postDecoder should have been created before
            assert postDecoder != null;
            writeResponse(ctx.channel());
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("Channel read complete.");
        ctx.channel().flush();
        ctx.flush();
    }

//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.fireExceptionCaught(cause);
//    }

    private void reset() {
        postDecoder.destroy();
        postDecoder = null;
    }

    private void writeResponse(Channel channel) {
        ByteBuf buf = Unpooled.copiedBuffer("hahahaha".toString(), CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);

        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");

        ChannelFuture future = channel.write(response);
    }


}
