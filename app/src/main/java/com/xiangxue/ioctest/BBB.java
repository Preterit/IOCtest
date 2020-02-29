package com.xiangxue.ioctest;

import com.xiangxue.ioctest.util.ContentView;

import java.lang.reflect.Proxy;

/**
 * Date:2020-02-23
 * author:lwb
 * Desc:
 */
public class BBB {

    @AAA(value = {1, 3, 3})
    public void getD(Object context){

        Class<?> clazz = context.getClass();
        //接下来会在clazz上面去执行setContentView

        ContentView contentView = clazz.getAnnotation(ContentView.class);

        Class<? extends BBB> aClass = this.getClass();
//        Proxy.newProxyInstance(listenerType.getClassLoader(),new Class[]{listenerType},invocationHandler)
    }

}
