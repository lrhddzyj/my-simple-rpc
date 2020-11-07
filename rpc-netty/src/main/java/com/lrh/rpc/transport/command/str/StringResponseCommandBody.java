package com.lrh.rpc.transport.command.str;

import com.lrh.rpc.transport.command.ResponseCommandBody;
import lombok.Data;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 11:10
 */
@Data
public class StringResponseCommandBody extends ResponseCommandBody {

  /**
   * 假设目前传出的对象值是字符串（不考虑对象的序列化和反序列化）
   */
  private String message;

  @Override
  public String toString() {
    return "StringResponseCommandBody{" +
        "message='" + message + '\'' +
        "} " + super.toString();
  }
}
