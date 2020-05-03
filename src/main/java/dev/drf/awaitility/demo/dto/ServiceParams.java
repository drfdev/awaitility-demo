package dev.drf.awaitility.demo.dto;

import java.io.Serializable;

import static java.util.Objects.requireNonNull;

public final class ServiceParams<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -4884297181260846813L;

    private final T value;

    private ServiceParams(T value) {
        this.value = requireNonNull(value);
    }

    public T getValue() {
        return value;
    }

    public static <T extends Serializable> ServiceParams<T> of(T value) {
        return new ServiceParams<>(value);
    }

    @Override
    public String toString() {
        return "ServiceParams{" +
                "value=" + value +
                '}';
    }
}
