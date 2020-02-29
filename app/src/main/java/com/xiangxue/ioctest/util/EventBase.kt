package com.xiangxue.ioctest.util

import android.view.View
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * Date:2020/2/26
 * author:lwb
 * Desc:
 */
// 定义在注解上的。比如写在@OnClick
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(RetentionPolicy.RUNTIME)
annotation class EventBase(
    //1：setOnClickListener订阅关系
    val listenerSetting: String,
    //3：事件处理程序
    val callBackMethod: String,
    //2：new View.OnClickListener()事件本身
    val listenerType: KClass<*>
)

@Target(AnnotationTarget.FUNCTION)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(
    listenerSetting = "setOnClickListener",
    listenerType = View.OnClickListener::class,
    callBackMethod = "onClick"
)
annotation class OnClick(
    vararg val value: Int
)







