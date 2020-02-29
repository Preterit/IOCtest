package com.xiangxue.ioctest.util


/**
 * Date:2020-02-23
 * author:lwb
 * Desc:
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ContentView(val value: Int)


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewInject(val value: Int)