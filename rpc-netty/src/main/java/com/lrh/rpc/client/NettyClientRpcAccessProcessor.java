package com.lrh.rpc.client;

import com.lrh.rpc.ClientRpcAccessProcessor;
import com.lrh.rpc.NameService;
import com.lrh.rpc.client.stub.NewStubInvokeFactory;
import com.lrh.rpc.nameservice.LocalNameService;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 17:07
 */
public class NettyClientRpcAccessProcessor implements ClientRpcAccessProcessor {

  private NameService nameService;

  private String host;

  private int port;

  private NettyRpcClient nettyRpcClient;

  private NewStubInvokeFactory stubFactory;

  public NettyClientRpcAccessProcessor(String host, int port) {
    this.host = host;
    this.port = port;
    nameService = new LocalNameService();
    init();
  }

  private void init(){
    try {
      nettyRpcClient = new NettyRpcClient();
      InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
      Transport transport = nettyRpcClient.createTransport(inetSocketAddress, 10000);
      stubFactory = new NewStubInvokeFactory(transport);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }


  @Override
  public <T> T getRemoteStubService(Class<T> clazz) {
    return stubFactory.getStubProxy(clazz);
  }

  @Override
  public NameService getNameService() {
    return nameService;
  }

  @Override
  public void close() throws IOException {
    if (nettyRpcClient != null) {
      nettyRpcClient.close();
    }
  }
}
