package com.lrh.rpc.transport.command;

import lombok.Data;

/**
 *
 * @description:
 * @author: lrh
 * @date: 2020/10/24 11:00
 */
@Data
public abstract class ResponseCommandBody extends CommandBody {

  private int errCode;

  private String errorMsg;

  @Override
  public String toString() {
    return "ResponseCommandBody{" +
        "errCode=" + errCode +
        ", errorMsg='" + errorMsg + '\'' +
        "} " + super.toString();
  }
}
