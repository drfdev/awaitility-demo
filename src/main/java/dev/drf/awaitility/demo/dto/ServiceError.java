package dev.drf.awaitility.demo.dto;

import java.io.Serializable;

import static java.util.Objects.requireNonNull;

public class ServiceError implements Serializable {
    private static final long serialVersionUID = -7628266330148030711L;

    private final String code;
    private final String message;

    private ServiceError(String code, String message) {
        this.code = requireNonNull(code);
        this.message = requireNonNull(message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ServiceError of(String code, String message) {
        return new ServiceError(code, message);
    }

    @Override
    public String toString() {
        return "ServiceError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
