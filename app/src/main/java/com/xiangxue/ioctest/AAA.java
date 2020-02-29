package com.xiangxue.ioctest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Proxy;

/**
 * Date:2020-02-23
 * author:lwb
 * Desc:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AAA {

//    int value() default -1;

//    String ge();
//
//    Class<?> listenerType();
//
//    String callBackMethod();
//
//    int[] value() default -1;
//
//    String[] value1() default " -1";
//
//    int[] value2() default {};

    int[] value() default -1;
}


