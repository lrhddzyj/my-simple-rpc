package com.lrh.test.rpc;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 16:35
 */
public class HelloServiceImpl implements HelloApi {

  @Override
  public String hello(String msg) {
    return "hello:" + msg;
  }
}
