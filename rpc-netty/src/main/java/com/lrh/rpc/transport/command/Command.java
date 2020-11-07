package com.lrh.rpc.transport.command;

import lombok.Data;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 10:58
 */
@Data
public abstract class Command< T extends CommandBody> {

  private CommandHeader header;

  private T body;


}
