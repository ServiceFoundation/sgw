package sgw.parser;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.apache.commons.codec.Charsets;

/**
 * stateless
 */
public class EchoServiceEchoResult implements FullHttpResponseGenerator {

    public EchoServiceEchoResult() {}

    @Override
    public FullHttpResponse generate(Object[] results, ByteBuf buf) {
        String result = (String) results[0];
        buf.writeCharSequence(result, Charsets.UTF_8);
        return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
    }

}