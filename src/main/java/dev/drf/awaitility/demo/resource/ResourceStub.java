package dev.drf.awaitility.demo.resource;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public final class ResourceStub implements AutoCloseable {
    private static final int CAPACITY = 10;
    private static final int POOL_SIZE = 4;
    private static final long DELAY = 1000L;
    private static final long POLL_DELAY = 200L;

    private final BlockingQueue<TaskHolder> queue;
    private final ScheduledExecutorService executorService;

    private final AtomicLong taskIdGenerator = new AtomicLong(0);
    private final Map<Long, Object> resultMap = new ConcurrentHashMap<>();

    private ResourceStub() {
        queue = new LinkedBlockingQueue<>(CAPACITY);
        executorService = Executors.newScheduledThreadPool(POOL_SIZE);

        executorService.schedule(this::completeTask, DELAY, TimeUnit.MILLISECONDS);
    }

    public static ResourceStub getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static ResourceStub INSTANCE = new ResourceStub();
    }

    private static class TaskHolder {
        final long id;
        final ResourceTask task;

        public TaskHolder(long id, ResourceTask task) {
            this.id = id;
            this.task = task;
        }
    }

    public long scheduleTask(ResourceTask task) throws InterruptedException {
        final long id = taskIdGenerator.incrementAndGet();
        queue.put(new TaskHolder(id, task));
        return id;
    }

    public synchronized Object checkResult(long id) {
        Object value = resultMap.get(id);
        if (value != null) {
            resultMap.remove(id);
        }
        return value;
    }

    @Override
    public void close() throws Exception {
        executorService.shutdownNow();
    }

    private void completeTask() {
        if (queue.isEmpty()) {
            return;
        }
        TaskHolder holder = null;
        try {
            holder = queue.poll(POLL_DELAY, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        if (holder == null) {
            return;
        }

        final long taskId = holder.id;
        final ResourceTask task = holder.task;

        Object result = task.planTask();
        resultMap.put(taskId, result);
    }
}
