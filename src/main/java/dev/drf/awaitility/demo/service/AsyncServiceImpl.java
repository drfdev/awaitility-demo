package dev.drf.awaitility.demo.service;

import dev.drf.awaitility.demo.AsyncService;
import dev.drf.awaitility.demo.dto.ServiceParams;
import dev.drf.awaitility.demo.dto.ServiceResult;

public class AsyncServiceImpl implements AsyncService {
    @Override
    public ServiceResult<String> doAsyncCall(ServiceParams<Integer> param) {
        // TODO
        return null;
    }
}
