package dev.drf.awaitility.demo;

import dev.drf.awaitility.demo.dto.AsyncPublisher;
import dev.drf.awaitility.demo.dto.ServiceParams;

public interface AsyncService {
    AsyncPublisher<String> doAsyncCall(ServiceParams<Integer> param);
}
