package com.lrh.rpc.transport.invoke.server;

import com.lrh.rpc.transport.command.str.StringRequestCommand;
import com.lrh.rpc.transport.command.str.StringRequestCommandBody;
import com.lrh.rpc.transport.command.str.StringResponseCommand;
import com.lrh.rpc.transport.command.str.StringResponseCommandBody;
import com.lrh.rpc.transport.invoke.InvokeInfo;
import com.lrh.rpc.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 14:55
 */
@Slf4j
public class ServerInvokeHandler extends SimpleChannelInboundHandler<StringRequestCommand> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, StringRequestCommand msg) throws Exception {
    StringRequestCommandBody body = msg.getBody();
    String message = body.getMessage();
    InvokeInfo invokeInfo = JsonUtils.fromJson(message, InvokeInfo.class);
    String invokeClassName = invokeInfo.getInvokeClassName();
    String invokeMethod = invokeInfo.getInvokeMethod();
    String param = invokeInfo.getParam();
    Object provider = ServerProviderCenter.getProvider(invokeClassName);

    //反射调用返回
    String result = (String)JavaReflectionInvoke.invoke(provider, invokeMethod, param);
    StringResponseCommandBody stringResponseCommandBody = new StringResponseCommandBody();
    stringResponseCommandBody.setMessage(result);

    StringResponseCommand stringResponseCommand = new StringResponseCommand();
    stringResponseCommand.setHeader(msg.getHeader());
    stringResponseCommand.setBody(stringResponseCommandBody);
    if (result == null) {
      log.error("not writable now, message dropped");
      return;
    }
    log.info("send response: {}",stringResponseCommand);

    if (ctx.channel().isActive() && ctx.channel().isWritable()) {
      ctx.writeAndFlush(stringResponseCommand);
    } else {
      log.error("not writable now, message dropped");
    }

  }
}
