package com.lrh.rpc.client;

import com.lrh.rpc.dispatcher.InFlightResponseCenter;
import com.lrh.rpc.dispatcher.ResponseResultFuture;
import com.lrh.rpc.transport.command.Command;
import io.netty.channel.Channel;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 17:31
 */
public class NettyTransport implements Transport {

  private Channel channel;

  public NettyTransport(Channel channel) {
    this.channel = channel;
  }


  @Override
  public ResponseResultFuture send(Command command) {
    ResponseResultFuture responseResultFuture = new ResponseResultFuture();
    long streamId = command.getHeader().getStreamId();
    InFlightResponseCenter.addFuture(streamId, responseResultFuture);
    channel.writeAndFlush(command);
    return responseResultFuture;
  }
}
