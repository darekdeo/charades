package com.dariuszdeoniziak.charades.views;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringRes;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Font {
    String path() default "";
    @StringRes int text() default 0;
}
