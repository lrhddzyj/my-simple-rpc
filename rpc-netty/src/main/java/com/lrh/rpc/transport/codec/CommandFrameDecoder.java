package com.lrh.rpc.transport.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 11:18
 */
public class CommandFrameDecoder extends LengthFieldBasedFrameDecoder {

  public CommandFrameDecoder() {
    super(Integer.MAX_VALUE, 0, 2, 0, 2);
  }
}
