package com.flow.adapter.util;

import com.flow.adapter.common.exception.ErrorType;
import com.flow.adapter.common.exception.InvalidInputException;
import java.util.function.Predicate;

public class Util {

    public static <T> void require(final Predicate<T> predicate, final T target, final ErrorType errorType) {
        if (predicate.test(target)) {
            throw new InvalidInputException(errorType);
        }
    }

    public static void check(final boolean condition, final ErrorType errorType) {
        if (condition) {
            throw new InvalidInputException(errorType);
        }
    }
}
