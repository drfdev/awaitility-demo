package dev.drf.awaitility.demo.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.function.Supplier;

public final class AsyncPublisher<R extends Serializable> {
    private final long SLEEP_TIME = 100L;

    private final Supplier<R> task;

    public AsyncPublisher(Supplier<R> task) {
        this.task = task;
    }

    public ServiceResult<R> execute() {
        R result = null;
        while (result == null) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                return ServiceResult.toError(
                        Collections.singletonList(
                                ServiceError.of("sync", "Sleep exception")));
            }
            result = task.get();
        }
        return ServiceResult.toSuccess(result);
    }
}
