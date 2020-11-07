package com.lrh.rpc.transport.invoke.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 15:11
 */
public class JavaReflectionInvoke {

  public static Object invoke(Object object, String methodName, String arg)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method = object.getClass().getMethod(methodName, String.class);
    return method.invoke(object, arg);
  }
}
