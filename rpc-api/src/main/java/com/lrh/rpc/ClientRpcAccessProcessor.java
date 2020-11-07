package com.lrh.rpc;

import java.io.Closeable;
import java.net.URI;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 10:24
 */
public interface ClientRpcAccessProcessor extends Closeable,NameServiceAccessProcessor {

  /**
   * 为客户端的创建远程RPC的桩服务（动态代理服务）
   * @param clazz
   * @param <T>
   * @return
   */
  <T> T getRemoteStubService(Class<T> clazz);



}
