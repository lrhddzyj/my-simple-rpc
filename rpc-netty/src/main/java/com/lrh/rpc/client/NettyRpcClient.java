package com.lrh.rpc.client;

import com.lrh.rpc.client.handler.ResponseDispatcherHandler;
import com.lrh.rpc.transport.RpcClient;
import com.lrh.rpc.transport.codec.CommandFrameDecoder;
import com.lrh.rpc.transport.codec.CommandFrameEncoder;
import com.lrh.rpc.transport.codec.client.CommandProtocolDecoder;
import com.lrh.rpc.transport.codec.client.CommandProtocolEncode;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.net.SocketAddress;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 17:47
 */
@Slf4j
public class NettyRpcClient implements RpcClient {

  private NioEventLoopGroup eventExecutors;

  private Channel channel;

  @Override
  public Transport createTransport(SocketAddress address, long connectionTimeout)
      throws InterruptedException, TimeoutException {
    Channel channel = newChanel(address, connectionTimeout);
    NettyTransport nettyTransport = new NettyTransport(channel);
    return nettyTransport;
  }

  private Channel newChanel(SocketAddress address, long connectionTimeout) {
    eventExecutors = new NioEventLoopGroup();
    Bootstrap bootstrap = newBootstrap(eventExecutors, newChannelHandler());
    ChannelFuture connectFuture = bootstrap.connect(address);
    try {
      connectFuture.await(connectionTimeout);
    } catch (InterruptedException e) {
      log.error("连接超时");
    }
    channel = connectFuture.channel();
    return channel;
  }

  private ChannelHandler newChannelHandler() {
    return new ChannelInitializer<NioSocketChannel>() {
      @Override
      protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
        pipeline.addLast(new CommandFrameDecoder());
        pipeline.addLast(new CommandFrameEncoder());
        pipeline.addLast(new CommandProtocolDecoder());
        pipeline.addLast(new CommandProtocolEncode());
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new ResponseDispatcherHandler());
      }
    };
  }

  private Bootstrap  newBootstrap(EventLoopGroup eventExecutors,ChannelHandler channelHandler) {
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.channel(NioSocketChannel.class)
        .group(eventExecutors)
        .handler(new LoggingHandler(LogLevel.INFO))
        .handler(channelHandler);
    return bootstrap;
  }


  @Override
  public void close() {

    if (channel != null) {
      channel.close();
    }
    if (eventExecutors != null) {
      eventExecutors.shutdownGracefully();
    }

  }
}
