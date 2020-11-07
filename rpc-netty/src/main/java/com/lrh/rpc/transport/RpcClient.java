package com.lrh.rpc.transport;

import com.lrh.rpc.client.Transport;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 17:09
 */
public interface RpcClient extends Closeable {

  Transport createTransport(SocketAddress address, long connectionTimeout) throws InterruptedException, TimeoutException;

  @Override
  void close();
}
