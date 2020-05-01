package dev.drf.awaitility.demo.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ServiceResult<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -6296543061895476518L;

    private final Status status;
    private final T payload;
    private final List<ServiceError> errors;

    private ServiceResult(Status status, T payload, List<ServiceError> errors) {
        this.status = requireNonNull(status);
        this.payload = payload;
        this.errors = errors;
    }

    public Status getStatus() {
        return status;
    }

    public T getPayload() {
        return payload;
    }

    public List<ServiceError> getErrors() {
        return errors;
    }

    public static <T extends Serializable> ServiceResult<T> toSuccess(T payload) {
        return new ServiceResult<>(Status.SUCCESS, payload, null);
    }

    public static <T extends Serializable> ServiceResult<T> toWarning(T payload, ServiceError error) {
        return new ServiceResult<>(Status.WARNING, payload, Collections.singletonList(error));
    }

    public static <T extends Serializable> ServiceResult<T> toError(List<ServiceError> errors) {
        return new ServiceResult<>(Status.ERROR, null, errors);
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "status=" + status +
                ", payload=" + payload +
                ", errors=" + errors +
                '}';
    }
}
