package dev.drf.awaitility.demo.service;

import dev.drf.awaitility.demo.FutureService;
import dev.drf.awaitility.demo.dto.ServiceParams;
import dev.drf.awaitility.demo.dto.ServiceResult;

import java.util.concurrent.CompletableFuture;

public class FutureServiceImpl implements FutureService {
    @Override
    public CompletableFuture<ServiceResult<String>> doFutureCall(ServiceParams<Integer> param) {
        // TODO
        return null;
    }
}
