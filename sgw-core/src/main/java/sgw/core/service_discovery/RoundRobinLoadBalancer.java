package sgw.core.service_discovery;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * All methods should be thread-safe
 */
public class RoundRobinLoadBalancer<T> implements LoadBalancer<T> {

    private final AtomicInteger count = new AtomicInteger(0);
    private final AtomicInteger size = new AtomicInteger(0);
    private CopyOnWriteArrayList<T> itemList;
    private HashMap<T, Integer> itemMap;

    public RoundRobinLoadBalancer() {
        itemList = new CopyOnWriteArrayList<>();
        itemMap = new HashMap<>();
    }

    /**
     * This method will be frequently invoked in all eventloops. If any eventloop blocks,
     * all the channels inside it will block. So synchronization is not a good idea.
     * This method is thread safe.
     */
    @Override
    public T next() {
        int size = this.size.get();
        if (size == 0)
            return null;
        T item;
        try {
            int index = Math.abs(count.getAndIncrement() % size);
            item = itemList.get(index);
        } catch(ArithmeticException e) {
            return null;
        } catch(IndexOutOfBoundsException e) {
            return next();
        }
        return item;
    }

    @Override
    public int size() {
        return size.get();
    }

    @Override
    public synchronized int add(T item) {
        itemList.add(item);
        itemMap.put(item, size.getAndIncrement());
        return size.get();
    }

    @Override
    public synchronized int remove(T item) {
        int size = this.size.decrementAndGet();
        int index = itemMap.remove(item);
        T endItem = itemList.get(size);
        itemList.set(index, endItem);
        itemList.remove(size);
        itemMap.put(endItem, index);
        return size;
    }
}
