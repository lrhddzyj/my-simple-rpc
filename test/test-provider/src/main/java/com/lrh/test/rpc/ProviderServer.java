package com.lrh.test.rpc;

import com.lrh.rpc.ServerRpcAccessProcessor;
import com.lrh.rpc.server.NettyServerRpcAccessProcessor;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 16:36
 */
public class ProviderServer {

  public static void main(String[] args) {
    ServerRpcAccessProcessor serverRpcAccessProcessor = new NettyServerRpcAccessProcessor("localhost",
        8090);
    HelloApi helloApi = new HelloServiceImpl();
    serverRpcAccessProcessor.addProvider(HelloApi.class, helloApi);
    serverRpcAccessProcessor.start();
  }

}
