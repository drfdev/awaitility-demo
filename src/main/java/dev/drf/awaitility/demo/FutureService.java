package dev.drf.awaitility.demo;

import dev.drf.awaitility.demo.dto.ServiceParams;
import dev.drf.awaitility.demo.dto.ServiceResult;

import java.util.concurrent.CompletableFuture;

public interface FutureService {
    CompletableFuture<ServiceResult<String>> doFutureCall(ServiceParams<Integer> param);
}
