package com.lrh.rpc.client.handler;

import com.lrh.rpc.dispatcher.InFlightResponseCenter;
import com.lrh.rpc.transport.command.str.StringResponseCommand;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 20:09
 */
public class ResponseDispatcherHandler extends SimpleChannelInboundHandler<StringResponseCommand> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, StringResponseCommand msg)
      throws Exception {
    InFlightResponseCenter.setResult(msg.getHeader().getStreamId(), msg);
  }
}
