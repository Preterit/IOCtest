# IOCtest
kotlin 注解 / 反射  demo

在使用反射的时候调用动态代理 调用invoke 方法是第二个参数是java的可变参数，然而在kotlin中无法将 Array<out Any> 类型直接当作参数使用， 
下面代码是解决方法。

```kotlin
override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
     Log.e(TAG, "method name ---- ${method.toString()} ---- atgs --- ${args?.get(0).toString()}")
     return activityMethod.invoke(activity, *(args ?: emptyArray()))
     return activityMethod.invoke(activity, *(args!!))
}
```

