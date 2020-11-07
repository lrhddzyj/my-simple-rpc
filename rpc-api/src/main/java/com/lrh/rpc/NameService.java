package com.lrh.rpc;

import java.net.URI;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 10:19
 */
public interface NameService {

  void register(String serviceName, URI uri);

  URI getService(String serviceName);


}
