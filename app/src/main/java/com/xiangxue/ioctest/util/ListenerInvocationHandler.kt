package com.xiangxue.ioctest.util

import android.util.Log
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * Date:2020/2/27
 * author:lwb
 * Desc:
 */
class ListenerInvocationHandler(private val activity: Any, private val activityMethod: Method) :
    InvocationHandler {

    private val TAG = this.javaClass.name

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        Log.e(TAG, "method name ---- ${method.toString()} ---- atgs --- ${args?.get(0).toString()}")
        return activityMethod.invoke(activity, *(args ?: emptyArray()))
//        return activityMethod.invoke(activity, *(args!!))
    }
}