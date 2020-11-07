package com.lrh.rpc.server;

import com.lrh.rpc.NameService;
import com.lrh.rpc.ServerRpcAccessProcessor;
import com.lrh.rpc.nameservice.LocalNameService;
import com.lrh.rpc.transport.RpcServer;
import com.lrh.rpc.transport.invoke.server.ServerProviderCenter;
import java.io.IOException;
import java.net.URI;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 15:59
 */
public class NettyServerRpcAccessProcessor implements ServerRpcAccessProcessor {

  private NameService nameService;

  private String host;

  private int port;

  private RpcServer rpcServer;

  private final URI uri = URI.create("rpc://" + host + ":" + port);

  public NettyServerRpcAccessProcessor(String host, int port) {
    this.host = host;
    this.port = port;
    nameService = new LocalNameService();
    rpcServer = new NettyRpcServer();
  }

  @Override
  public <T> URI addProvider(Class<T> providerClass, T service) {
    ServerProviderCenter.addProvider(providerClass.getCanonicalName(), service);
    nameService.register(providerClass.getCanonicalName(), uri);
    return uri;
  }

  @Override
  public void start() {
    try {
      rpcServer.start(port);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public NameService getNameService() {
    return nameService;
  }


  @Override
  public void close() throws IOException {
    if (rpcServer != null) {
      rpcServer.stop();
    }
  }
}
