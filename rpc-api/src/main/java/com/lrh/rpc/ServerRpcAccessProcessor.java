package com.lrh.rpc;

import java.io.Closeable;
import java.net.URI;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 10:29
 */
public interface ServerRpcAccessProcessor extends NameServiceAccessProcessor, Closeable {

  /**
   * 生成访问服务，并返回远端调用的URI
   * @param providerClass
   * @param service
   * @param <T>
   * @return
   */
  <T> URI addProvider(Class<T> providerClass,T service);

  /**
   * 启动
   */
  void start();

}
