package com.xiangxue.ioctest.javainject;

import com.xiangxue.ioctest.util.EventBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date:2020/2/27
 * author:lwb
 * Desc:
 */
//@EventBase()
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClickJava {
    int[] value() default -1;
}
