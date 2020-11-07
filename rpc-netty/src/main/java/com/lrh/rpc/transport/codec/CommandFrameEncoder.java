package com.lrh.rpc.transport.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 11:26
 */
public class CommandFrameEncoder extends LengthFieldPrepender {

  public CommandFrameEncoder() {
    super(2);
  }
}
