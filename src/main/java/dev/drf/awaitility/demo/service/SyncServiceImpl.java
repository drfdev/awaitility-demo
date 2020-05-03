package dev.drf.awaitility.demo.service;

import dev.drf.awaitility.demo.SyncService;
import dev.drf.awaitility.demo.dto.ServiceError;
import dev.drf.awaitility.demo.dto.ServiceParams;
import dev.drf.awaitility.demo.dto.ServiceResult;
import dev.drf.awaitility.demo.resource.ResourceStub;
import dev.drf.awaitility.demo.resource.ResourceTask;

import java.util.Collections;

public class SyncServiceImpl implements SyncService {
    private final long SLEEP_TIME = 100L;

    @Override
    public synchronized ServiceResult<String> doSyncCall(ServiceParams<Integer> param) {
        ResourceStub resourceStub = ResourceStub.getInstance();
        Long taskId = null;
        try {
            taskId = resourceStub.scheduleTask(task(param));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return error("Create task error");
        }
        String result = null;
        while (result == null) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                return error("Sleep exception");
            }
            result = (String) resourceStub.checkResult(taskId);
        }
        return ServiceResult.toSuccess(result);
    }

    private ResourceTask task(ServiceParams<Integer> param) {
        return () -> {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                return "!! Exception !!";
            }
            return param.getValue().toString();
        };
    }

    private ServiceResult<String> error(String message) {
        return ServiceResult.toError(Collections.singletonList(ServiceError.of("sync", message)));
    }
}
