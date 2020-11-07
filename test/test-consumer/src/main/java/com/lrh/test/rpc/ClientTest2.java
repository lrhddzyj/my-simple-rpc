package com.lrh.test.rpc;

import com.lrh.rpc.client.NettyClientRpcAccessProcessor;
import com.lrh.rpc.client.NettyRpcClient;
import com.lrh.rpc.client.Transport;
import com.lrh.rpc.dispatcher.ResponseResultFuture;
import com.lrh.rpc.transport.command.Command;
import com.lrh.rpc.transport.command.CommandHeader;
import com.lrh.rpc.transport.command.str.StringRequestCommand;
import com.lrh.rpc.transport.command.str.StringRequestCommandBody;
import com.lrh.rpc.transport.invoke.InvokeInfo;
import com.lrh.rpc.utils.IdUtil;
import com.lrh.rpc.utils.JsonUtils;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 18:53
 */
public class ClientTest2 {

  public static void main(String[] args)
      throws TimeoutException, InterruptedException, ExecutionException {
    NettyClientRpcAccessProcessor nettyClientRpcAccessProcessor = new NettyClientRpcAccessProcessor(
        "localhost", 8090);
    HelloApi helloApi = nettyClientRpcAccessProcessor.getRemoteStubService(HelloApi.class);
    System.out.println(helloApi.hello("aaa"));
  }



}
