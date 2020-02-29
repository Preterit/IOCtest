package com.xiangxue.ioctest.util

import android.util.EventLog
import android.util.Log
import android.view.View
import com.xiangxue.ioctest.javainject.ListenerInvocation
import java.lang.reflect.Method
import java.lang.reflect.Proxy


/**
 * Date:2020-02-23
 * author:lwb
 * Desc:
 */
object InjectUtil {

    private val TAG = this.javaClass.name

    fun inject(context: Any) {
        injectLayout(context)
        injectView(context)
        injectEvent(context)
    }

    private fun injectEvent(context: Any) {
        //一次性处理安卓中的23中事件
        val clazz: Class<Any> = context.javaClass
        // 获取到所有的方法
        val methods = clazz.declaredMethods
        for (method in methods) {
            //获取方法上面所有的注解Annotations
            val annotations = method.annotations
            for (annotation in annotations) {
                Log.e(TAG, "annotation ---- ${annotation.toString()}")
                //得到注解的类型   OnClick.class
                val annotationClass = annotation.annotationClass.java
                // 得到eventBase注解
                val eventBase = annotationClass.getAnnotation(EventBase::class.java)
                Log.e(TAG, "eventBase ---- ${eventBase == null}")
                if (eventBase == null) {
                    continue
                }
                //存在EventBase 说明是一个事件处理方法
                //开始获取事件处理的三要素。用于确定是那种事件
                /**
                 * 1：setOnClickListener 订阅关系
                 * 2：new View.OnClickListener 事件本身
                 * 3: 事件处理程序  callBackMethod
                 */
                val listenerSetting = eventBase.listenerSetting  //订阅关系
                val listenerType = eventBase.listenerType   // 事件本身
                val callBackMethod = eventBase.callBackMethod  // 事件的处理程序

                //得到3要素之后，就可以执行代码了。
                val valueMethod = annotationClass.getDeclaredMethod("value")
                //获取到所有绑定事件的ID
                val viewIds = valueMethod.invoke(annotation) as IntArray
                for (id in viewIds) {
                    // 获取到MainActivity 中的 findViewById() 方法
                    val findViewById = clazz.getMethod("findViewById", Int::class.java)
                    val view = findViewById.invoke(context, id) ?: continue
                    val view1 = view as View
                    //context == activity  method == onClick
                    val invocationHandler = ListenerInvocationHandler(context, method)

                    //做代理
                    val proxy = Proxy.newProxyInstance(
                        listenerType.java.classLoader,
                        arrayOf<Class<*>>(listenerType.java),
                        invocationHandler
                    )
                    //执行 让proxy执行onClick
                    val clickMethod =
                        view1::class.java.getMethod(listenerSetting, listenerType.java)
                    //这时候点击按钮就会去执行代理类中的invoke方法
                    clickMethod.invoke(view1, proxy)
                }
            }
        }
    }

    /**
     * 注解+动态代理
     *
     * 1：获取context对象中的所有的方法
     * 2：获取每个方法上面的所有注解，(有的方法上面没有注解)
     * 3：找到有EventBase标注的注解
     * 4：获取事件处理的三要素
     * 5：自定义一个动态代理绑定到OnClickListener对象上
     * 6：让框架执行代理(onClick---->代理中的invoke上)
     * 7：让代理执行用户写的abc()方法
     *
     */
//    private fun injectEvent(context: Any) {
//        //一次性处理安卓中的23中事件
//        val clazz: Class<Any> = context.javaClass
//        // 获取到所有的方法
//        val methods = clazz.declaredMethods
//        for (method in methods) {
//            //获取方法上面所有的注解Annotations
//            val annotations = method.annotations
//            for (annotation in annotations) {
//                Log.e(TAG, "annotation ---- ${annotation.toString()}")
//                //得到注解的类型   OnClick.class
//                val annotationClass = annotation::class.java
//                // 得到eventBase注解
//                Log.e(TAG, "annotationClass  ---- ${annotationClass.annotations.size}")
//                for (annotation in annotationClass.annotations) {
//                    Log.e(TAG, "onClick - annotation  ---- ${annotation.toString()}")
//                }
//                val eventBase = annotationClass.getAnnotation(EventBase::class.java)
//                Log.e(TAG, "eventBase ---- ${eventBase == null}")
//                if (eventBase == null) {
//                    continue
//                }
//                //存在EventBase 说明是一个事件处理方法
//                //开始获取事件处理的三要素。用于确定是那种事件
//                /**
//                 * 1：setOnClickListener 订阅关系
//                 * 2：new View.OnClickListener 事件本身
//                 * 3: 事件处理程序  callBackMethod
//                 */
//                val listenerSetting = eventBase.listenerSetting  //订阅关系
//                val listenerType = eventBase.listenerType   // 事件本身
//                val callBackMethod = eventBase.callBackMethod  // 事件的处理程序
//
//                //得到3要素之后，就可以执行代码了。
//                var valueMethod: Method? = null
//
//                valueMethod = annotationClass.getDeclaredMethod("value")
//                //获取到所有绑定事件的ID
//                val viewIds = valueMethod.invoke(annotation) as IntArray
//                for (id in viewIds) {
//                    Log.e(TAG, "annotation ---- ${id.javaClass.name}")
//                    // 获取到findViewById方法
//                    val findViewById = clazz.getMethod("findViewById", Int.javaClass)
//                    val view = findViewById.invoke(context, id) ?: continue
//                    val view1 = view as View
//                    //context == activity  method == onClick
//                    val invocationHandler = ListenerInvocationHandler(context, method)
//
//                    //做代理
//                    val proxy = Proxy.newProxyInstance(
//                        listenerType.java.classLoader,
//                        arrayOf<Class<*>>(listenerType.java),
//                        invocationHandler
//                    )
//                    //执行 让proxy执行onClick
//                    val clickMethod = view1.javaClass.getMethod(listenerSetting, listenerType.java)
//                    //这时候点击按钮就会去执行代理类中的invoke方法
//                    clickMethod.invoke(view1, proxy)
//                }
//            }
//        }
//    }

    private fun injectView(context: Any) {
        val clazz = context.javaClass
        val fields = clazz.declaredFields
        for (field in fields) {
            val viewInject = field.getAnnotation(ViewInject::class.java)
            if (viewInject != null) {
                val valueId = viewInject.value
                // 执行到这里获取控件id
                // 执行findViewById
                try {

                    val method = context.javaClass.getMethod("findViewById", Int::class.java)
                    val view: View = method.invoke(context, valueId) as View
                    field.isAccessible = true

                    //给字段赋值
                    field.set(context, view)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun injectLayout(context: Any) {
        var layoutId = 0
        var clazz: Class<*> = context.javaClass

        //接下来会在clazz上面去执行setContentView

        val contentView =
            clazz.getAnnotation(ContentView::class.java)

        if (contentView != null) {
            //取到括号内的id
            layoutId = contentView.value

            //反射执行setContentView

            try {

                val method: Method = context.javaClass.getMethod("setContentView", Int::class.java)
                method.invoke(context, layoutId)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}