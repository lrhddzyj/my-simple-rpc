package com.lrh.rpc.transport.command;

import lombok.Data;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 10:57
 */
@Data
public class CommandHeader {

  private int opCode;

  private int version;

  private long streamId;

  @Override
  public String toString() {
    return "CommandHeader{" +
        "opCode=" + opCode +
        ", version=" + version +
        ", streamId=" + streamId +
        '}';
  }
}
