package com.lrh.rpc.transport.codec.server;

import com.lrh.rpc.transport.command.CommandHeader;
import com.lrh.rpc.transport.command.str.StringResponseCommand;
import com.lrh.rpc.transport.command.str.StringResponseCommandBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 14:42
 */
public class CommandProtocolEncode extends MessageToMessageEncoder<StringResponseCommand> {

  @Override
  protected void encode(ChannelHandlerContext ctx, StringResponseCommand command, List<Object> out)
      throws Exception {
    ByteBuf buffer = ctx.alloc().buffer();
    CommandHeader header = command.getHeader();
    StringResponseCommandBody body = command.getBody();

    buffer.writeInt(header.getOpCode());
    buffer.writeInt(header.getVersion());
    buffer.writeLong(header.getStreamId());
    buffer.writeBytes(body.getMessage().getBytes());
    out.add(buffer);
  }
}
