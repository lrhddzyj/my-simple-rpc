package com.lrh.rpc.transport.codec.client;

import com.lrh.rpc.transport.command.CommandHeader;
import com.lrh.rpc.transport.command.str.StringRequestCommand;
import com.lrh.rpc.transport.command.str.StringRequestCommandBody;
import com.lrh.rpc.transport.command.str.StringResponseCommand;
import com.lrh.rpc.transport.command.str.StringResponseCommandBody;
import com.lrh.rpc.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import java.util.List;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 14:42
 */
public class CommandProtocolEncode extends MessageToMessageEncoder<StringRequestCommand> {

  @Override
  protected void encode(ChannelHandlerContext ctx, StringRequestCommand command, List<Object> out)
      throws Exception {
    ByteBuf buffer = ctx.alloc().buffer();
    CommandHeader header = command.getHeader();
    StringRequestCommandBody body = command.getBody();


    buffer.writeInt(header.getOpCode());
    buffer.writeInt(header.getVersion());
    buffer.writeLong(header.getStreamId());
    buffer.writeBytes(body.getMessage().getBytes(CharsetUtil.UTF_8));
    out.add(buffer);
  }
}
