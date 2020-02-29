package com.xiangxue.ioctest.javainject;

import android.nfc.Tag;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Date:2020/2/28
 * author:lwb
 * Desc:
 */
public class ListenerInvocation implements InvocationHandler {

    private static final String TAG = "ListenerInvocation";

    private Object activity;
    private Method activityMethod;

    public ListenerInvocation(Object activity, Method activityMethod) {
        this.activity = activity;
        this.activityMethod = activityMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e(TAG, "args --- " + args[0].toString());
        return activityMethod.invoke(activity, args);
    }
}
