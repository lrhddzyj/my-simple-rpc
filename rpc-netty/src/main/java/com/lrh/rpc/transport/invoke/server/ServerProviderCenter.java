package com.lrh.rpc.transport.invoke.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 14:58
 */
public class ServerProviderCenter {

  private static Map<String,Object> providerMap = new ConcurrentHashMap();

  public static <T> void addProvider(String serviceName,T t) {
    providerMap.put(serviceName, t);
  }

  public static Object getProvider(String serviceName) {
    return providerMap.get(serviceName);
  }

}
