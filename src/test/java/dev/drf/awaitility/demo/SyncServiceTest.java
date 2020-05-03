package dev.drf.awaitility.demo;

import dev.drf.awaitility.demo.dto.ServiceParams;
import dev.drf.awaitility.demo.dto.ServiceResult;
import dev.drf.awaitility.demo.dto.Status;
import dev.drf.awaitility.demo.service.SyncServiceImpl;
import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;

@Ignore
public class SyncServiceTest {
    private SyncServiceImpl service = new SyncServiceImpl();

    private Matcher<ServiceResult<String>> success(String value) {
        return allOf(
                hasProperty("status", is(Status.SUCCESS)),
                hasProperty("payload", is(value)),
                hasProperty("errors", nullValue())
        );
    }

    @Test
    public void singleCallTest() {
        Callable<ServiceResult<String>> callable = () -> service.doSyncCall(ServiceParams.of(10));
        await().until(callable,success("10"));
    }

    @Test
    public void singleCallTest_whenTimeout() {
        await()
                .atMost(2, SECONDS)
                .until(
                () -> service.doSyncCall(ServiceParams.of(15)),
                success("15")
        );
        await()
                .atMost(2, SECONDS)
                .until(
                        () -> service.doSyncCall(ServiceParams.of(20)),
                        success("20")
                );
    }
}
