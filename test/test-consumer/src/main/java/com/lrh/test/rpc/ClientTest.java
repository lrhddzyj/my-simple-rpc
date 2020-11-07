package com.lrh.test.rpc;

import com.lrh.rpc.client.NettyRpcClient;
import com.lrh.rpc.client.Transport;
import com.lrh.rpc.dispatcher.ResponseResultFuture;
import com.lrh.rpc.transport.command.Command;
import com.lrh.rpc.transport.command.CommandBody;
import com.lrh.rpc.transport.command.CommandHeader;
import com.lrh.rpc.transport.command.str.StringRequestCommand;
import com.lrh.rpc.transport.command.str.StringRequestCommandBody;
import com.lrh.rpc.transport.command.str.StringResponseCommandBody;
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
public class ClientTest {

  public static void main(String[] args)
      throws TimeoutException, InterruptedException, ExecutionException {
    NettyRpcClient nettyRpcClient = new NettyRpcClient();
    InetSocketAddress inetSocketAddress = new InetSocketAddress(8090);
    Transport transport = nettyRpcClient.createTransport(inetSocketAddress, 10000);
    Command command = buildCommand();
    ResponseResultFuture future = transport.send(command);
    System.out.println(future.get());
  }

  private  static  Command buildCommand() {
    CommandHeader header = new CommandHeader();
    header.setOpCode(1);
    header.setVersion(1);
    header.setStreamId(IdUtil.next());

    StringRequestCommandBody commandBody = new StringRequestCommandBody();
    InvokeInfo invokeInfo = new InvokeInfo();
    invokeInfo.setInvokeClassName(HelloApi.class.getCanonicalName());
    invokeInfo.setInvokeMethod("hello");
    invokeInfo.setParam("G2");
    commandBody.setMessage(JsonUtils.toJson(invokeInfo));

    StringRequestCommand command = new StringRequestCommand();
    command.setHeader(header);
    command.setBody(commandBody);

    return command;
  }

}
