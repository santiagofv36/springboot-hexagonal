package com.santiago.hexagonal.application.interfaces;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseCase {
}
