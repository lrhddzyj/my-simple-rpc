package com.lrh.rpc.transport;

import com.lrh.rpc.transport.invoke.server.ServerProviderCenter;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 16:04
 */
public interface RpcServer {

  void start(int port)  throws Exception;

  void stop();

}
