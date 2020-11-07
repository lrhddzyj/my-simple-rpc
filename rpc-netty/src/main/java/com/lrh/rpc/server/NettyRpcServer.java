package com.lrh.rpc.server;

import com.lrh.rpc.transport.RpcServer;
import com.lrh.rpc.transport.codec.CommandFrameDecoder;
import com.lrh.rpc.transport.codec.CommandFrameEncoder;
import com.lrh.rpc.transport.codec.server.CommandProtocolDecoder;
import com.lrh.rpc.transport.codec.server.CommandProtocolEncode;
import com.lrh.rpc.transport.invoke.server.ServerInvokeHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 15:57
 */
public class NettyRpcServer implements RpcServer {

  private EventLoopGroup acceptEventLoopGroup;
  private EventLoopGroup ioEventLoopGroup;
  private Channel channel;

  @Override
  public void start(int port) throws Exception {
    acceptEventLoopGroup = newEventLoopGroup();
    ioEventLoopGroup = newEventLoopGroup();
    ChannelHandler channelHandler = newChildHandler();
    ServerBootstrap serverBootstrap = newServerBootstrap(channelHandler, acceptEventLoopGroup,
        ioEventLoopGroup);
    ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
    channel = channelFuture.channel();
  }

  @Override
  public void stop() {
    if (acceptEventLoopGroup != null) {
      acceptEventLoopGroup.shutdownGracefully();
    }
    if (ioEventLoopGroup != null) {
      ioEventLoopGroup.shutdownGracefully();
    }
    if (channel != null) {
      channel.close();
    }
  }

  private ServerBootstrap newServerBootstrap(ChannelHandler childHandler,
      EventLoopGroup acceptEventLoopGroup, EventLoopGroup ioEventLoopGroup) {
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    serverBootstrap
        .channel(NioServerSocketChannel.class)
        .handler(new LoggingHandler(LogLevel.INFO))
        .group(acceptEventLoopGroup, ioEventLoopGroup)
        .childHandler(childHandler)
        .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

    return serverBootstrap;
  }

  private ChannelHandler newChildHandler() {
    return new ChannelInitializer() {
      @Override
      protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
        pipeline.addLast(new CommandFrameDecoder());
        pipeline.addLast(new CommandFrameEncoder());
        pipeline.addLast(new CommandProtocolDecoder());
        pipeline.addLast(new CommandProtocolEncode());
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new ServerInvokeHandler());
      }
    };
  }

  private EventLoopGroup newEventLoopGroup() {
    if (Epoll.isAvailable()) {
      return new EpollEventLoopGroup();
    } else {
      return new NioEventLoopGroup();
    }
  }


}
