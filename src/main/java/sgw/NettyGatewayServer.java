package sgw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sgw.core.filters.FilterMngr;
import sgw.core.http_channel.HttpChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import sgw.core.http_channel.routing.Router;
import sgw.core.http_channel.routing.RouterGeneratorFactory;
import sgw.core.service_discovery.RpcInvokerDiscoverer;

public class NettyGatewayServer {

    private final Logger logger = LoggerFactory.getLogger(NettyGatewayServer.class);

    private NioEventLoopGroup acceptor;
    private NioEventLoopGroup workerGroup;
    private NioEventLoopGroup backendGroup;

    private int serverPort;
    private HttpChannelInitializer httpChannelInitializer;
    private Router router;
    private RpcInvokerDiscoverer discoverer;

    /**
     *
     * @param config configuration for thread pool strategy.
     */
    public NettyGatewayServer(NettyGatewayServerConfig config) throws Exception {
        serverPort = config.getPort();
        ThreadPoolStrategy strategy = config.getThreadPoolStrategy();
        strategy.createThreadPool();
        acceptor = strategy.getAcceptor();
        workerGroup = strategy.getWorkerGroup();
        backendGroup = strategy.getBackendGroup();

        try {
            router = new RouterGeneratorFactory(config.getRouterDataSource()).create().generate();
            discoverer = new RpcInvokerDiscoverer.Builder().loadFromConfig().build();

            httpChannelInitializer = new HttpChannelInitializer(config);
            httpChannelInitializer.setRouter(router);
            httpChannelInitializer.setDiscoverer(discoverer);
        } catch (Exception e) {
            logger.error("Server Initialization failed.");
            throw e;
        }
    }

    public void start() throws Exception {
        try {
            discoverer.start();
            ServerBootstrap b = new ServerBootstrap();
            b.group(acceptor, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(httpChannelInitializer)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(serverPort).sync();
            f.channel().closeFuture().sync();
        } finally {
            close();
        }
    }

    public void close() throws InterruptedException {
        logger.info("server shutting down...");
        backendGroup.shutdownGracefully().sync();
        workerGroup.shutdownGracefully().sync();
        acceptor.shutdownGracefully().sync();
        discoverer.close();
    }

    public static void main(String[] args) {
        try {
            NettyGatewayServerConfig config = NettyGatewayServerConfig.getDebugConfig();
            ThreadPoolStrategy strategy = new ThreadPoolStrategy(ThreadPoolStrategy.MULTI_WORKERS, 2, 0);
            config.setThreadPoolStrategy(strategy);

            NettyGatewayServer server = new NettyGatewayServer(config);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
