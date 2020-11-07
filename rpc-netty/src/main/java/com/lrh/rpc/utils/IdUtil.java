package com.lrh.rpc.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 11:33
 */
public class IdUtil {

  private static AtomicLong atomicLong = new AtomicLong();

  public static long next() {
    return atomicLong.incrementAndGet();
  }

}
