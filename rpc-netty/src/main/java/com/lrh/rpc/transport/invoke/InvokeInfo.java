package com.lrh.rpc.transport.invoke;

import java.io.Serializable;
import lombok.Data;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 11:47
 */
@Data
public class InvokeInfo implements Serializable {

  private String invokeClassName;

  private String invokeMethod;

  /**
   * 假设现在就支持一个参数
   */
  private String param;

}
