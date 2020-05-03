package dev.drf.awaitility.demo.service;

import dev.drf.awaitility.demo.AsyncService;
import dev.drf.awaitility.demo.dto.AsyncPublisher;
import dev.drf.awaitility.demo.dto.ServiceParams;

public class AsyncServiceImpl implements AsyncService {
    @Override
    public AsyncPublisher<String> doAsyncCall(ServiceParams<Integer> param) {
        // TODO
        return null;
    }
}
