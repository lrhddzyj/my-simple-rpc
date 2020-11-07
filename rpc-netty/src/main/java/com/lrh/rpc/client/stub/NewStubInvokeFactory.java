package com.lrh.rpc.client.stub;

import com.lrh.rpc.client.Transport;
import com.lrh.rpc.dispatcher.ResponseResultFuture;
import com.lrh.rpc.transport.command.Command;
import com.lrh.rpc.transport.command.CommandHeader;
import com.lrh.rpc.transport.command.str.StringRequestCommand;
import com.lrh.rpc.transport.command.str.StringRequestCommandBody;
import com.lrh.rpc.transport.command.str.StringResponseCommand;
import com.lrh.rpc.transport.invoke.InvokeInfo;
import com.lrh.rpc.utils.IdUtil;
import com.lrh.rpc.utils.JsonUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/25 01:08
 */
public class NewStubInvokeFactory implements InvocationHandler {

  private Class clazz;

  private Transport transport;

  public NewStubInvokeFactory(Transport transport) {
    this.transport = transport;
  }

  public <T> T getStubProxy(Class<T> clazz) {
    this.clazz = clazz;
    return (T) Proxy
        .newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    ResponseResultFuture future = transport.send(buildCommand(method, args));
    StringResponseCommand stringResponseCommand = future.get();
    String result = stringResponseCommand.getBody().getMessage();
    return result;
  }

  private  Command buildCommand(Method method, Object[] args) {
    CommandHeader header = new CommandHeader();
    header.setOpCode(1);
    header.setVersion(1);
    header.setStreamId(IdUtil.next());

    StringRequestCommandBody commandBody = new StringRequestCommandBody();
    InvokeInfo invokeInfo = new InvokeInfo();
    invokeInfo.setInvokeClassName(clazz.getCanonicalName());
    invokeInfo.setInvokeMethod(method.getName());
    invokeInfo.setParam((String) args[0]);
    commandBody.setMessage(JsonUtils.toJson(invokeInfo));

    StringRequestCommand command = new StringRequestCommand();
    command.setHeader(header);
    command.setBody(commandBody);

    return command;
  }
}
