package com.lrh;

import com.lrh.test.rpc.HelloApi;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/25 00:27
 */
public class TestDynamic {

  public static void main(String[] args) {
    HelloApi helloApi = (HelloApi)Proxy.newProxyInstance(TestDynamic.class.getClassLoader(), new Class[]{HelloApi.class},
        new DemoInvokeHandle());
    helloApi.hello("aa");


  }

}
