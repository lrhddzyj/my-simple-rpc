package com.lrh.rpc.nameservice;

import com.lrh.rpc.NameService;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 15:47
 */
public class LocalNameService implements NameService {


  private Map<String, URI> serviceMap = new HashMap<>();

  @Override
  public void register(String serviceName, URI uri) {
    serviceMap.put(serviceName, uri);
  }

  @Override
  public URI getService(String serviceName) {
    return serviceMap.get(serviceName);
  }

}
