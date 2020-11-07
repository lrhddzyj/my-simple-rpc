package com.lrh;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/25 00:24
 */
public class DemoInvokeHandle implements InvocationHandler {



  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    System.out.println("aaaa");
    if (Object.class.equals(method.getDeclaringClass())) {
      return method.invoke(this, args);
    }
    return null;
  }
}
