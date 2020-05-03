package dev.drf.awaitility.demo.service;

import dev.drf.awaitility.demo.SyncService;
import dev.drf.awaitility.demo.dto.ServiceParams;
import dev.drf.awaitility.demo.dto.ServiceResult;

public class SyncServiceImpl implements SyncService {
    @Override
    public synchronized ServiceResult<String> doSyncCall(ServiceParams<Integer> param) {
        // TODO
        return null;
    }
}
