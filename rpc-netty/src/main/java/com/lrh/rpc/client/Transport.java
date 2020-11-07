package com.lrh.rpc.client;

import com.lrh.rpc.dispatcher.ResponseResultFuture;
import com.lrh.rpc.transport.command.Command;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 17:31
 */
public interface Transport {

  ResponseResultFuture send(Command command);
}

