package com.lrh.rpc.transport.command.str;

import com.lrh.rpc.transport.command.RequestCommandBody;
import lombok.Data;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 11:09
 */
@Data
public class StringRequestCommandBody extends RequestCommandBody {

  /**
   * 假设目前传入的对象值是字符串（不考虑对象的序列化和反序列化）
   */
  private String message;

  @Override
  public String toString() {
    return "StringRequestCommandBody{" +
        "message='" + message + '\'' +
        "} " + super.toString();
  }
}
