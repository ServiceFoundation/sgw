package sgw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolStrategy {

    private final Logger logger = LoggerFactory.getLogger(ThreadPoolStrategy.class);

    public static final int SINGLE_THREAD = 0;
    public static final int MULTI_WORKERS = 1;
    public static final int MULTI_WORKERS_AND_BACKENDS = 2;

    public static ThreadPoolStrategy DEBUG_MODE = new ThreadPoolStrategy(SINGLE_THREAD, 0, 0);


    private int strategy;
    private int workerThreads;
    private int backendThreads;

    /**
     *
     * @param strategy single_thread, multi_workers or multi_workers_and_backends
     * @param workerThreads 0 means using default (Netty internally use CPU*2)
     * @param backendThreads 0 means using default
     */
    ThreadPoolStrategy(int strategy, int workerThreads, int backendThreads) throws IllegalStateException{
        if (strategy < 0 || strategy > 2 || workerThreads < 0 || backendThreads < 0) {
            IllegalStateException e = new IllegalStateException("Invalid thread pool strategy.");
            logger.error(e.getMessage());
            throw e;
        }
        this.strategy = strategy;
        this.workerThreads = workerThreads;
        this.backendThreads = backendThreads;
    }

    public boolean isSingleThread() {
        return strategy == SINGLE_THREAD;
    }

    public boolean isMultiWorkers() {
        return strategy == MULTI_WORKERS;
    }

    public boolean isMultiWorkersBackends() {
        return strategy == MULTI_WORKERS_AND_BACKENDS;
    }

    public int getWorkerThreads() {
        return workerThreads;
    }

    public int getBackendThreads() {
        return backendThreads;
    }

}
