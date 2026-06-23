package com.santiago.hexagonal.application.interfaces;

public interface IApplicationService<T, R> {
    R execute(T request);
}
