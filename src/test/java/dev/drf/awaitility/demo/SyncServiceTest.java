package dev.drf.awaitility.demo;

import dev.drf.awaitility.demo.dto.ServiceParams;
import dev.drf.awaitility.demo.dto.ServiceResult;
import dev.drf.awaitility.demo.dto.Status;
import dev.drf.awaitility.demo.service.SyncServiceImpl;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;

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
        await().until(
                () -> service.doSyncCall(ServiceParams.of(10)),
                success("10")
        );
    }
}
