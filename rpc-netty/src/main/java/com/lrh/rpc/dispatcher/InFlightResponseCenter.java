package com.lrh.rpc.dispatcher;

import com.lrh.rpc.transport.command.str.StringResponseCommand;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 17:39
 */
public class InFlightResponseCenter {

  private static Map<Long, ResponseResultFuture> stringFutureMap = new ConcurrentHashMap<>();

  public static void addFuture(Long streamId, ResponseResultFuture future) {
    stringFutureMap.put(streamId, future);
  }

  public static void setResult(Long streamId, StringResponseCommand responseCommand) {
    ResponseResultFuture future = stringFutureMap.get(streamId);
    if (future != null) {
      future.setSuccess(responseCommand);
      stringFutureMap.remove(streamId);
    }
  }
}
