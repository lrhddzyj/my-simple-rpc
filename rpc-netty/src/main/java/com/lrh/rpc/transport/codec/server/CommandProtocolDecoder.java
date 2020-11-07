package com.lrh.rpc.transport.codec.server;

import com.lrh.rpc.transport.command.CommandHeader;
import com.lrh.rpc.transport.command.str.StringRequestCommand;
import com.lrh.rpc.transport.command.str.StringRequestCommandBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import java.util.List;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 11:28
 */
public class CommandProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
    int opCode = byteBuf.readInt();
    int version = byteBuf.readInt();
    long streamId = byteBuf.readLong();

    CommandHeader commandHeader = new CommandHeader();
    commandHeader.setOpCode(opCode);
    commandHeader.setVersion(version);
    commandHeader.setStreamId(streamId);

    String serverInfo = byteBuf.toString(CharsetUtil.UTF_8);
    StringRequestCommandBody stringRequestCommandBody = new StringRequestCommandBody();
    stringRequestCommandBody.setMessage(serverInfo);

    StringRequestCommand command = new StringRequestCommand();
    command.setHeader(commandHeader);
    command.setBody(stringRequestCommandBody);

    out.add(command);
  }
}
