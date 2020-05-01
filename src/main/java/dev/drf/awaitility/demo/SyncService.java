package dev.drf.awaitility.demo;

import dev.drf.awaitility.demo.dto.ServiceParams;
import dev.drf.awaitility.demo.dto.ServiceResult;

public interface SyncService {
    ServiceResult<String> doSyncCall(ServiceParams<Integer> param);
}
