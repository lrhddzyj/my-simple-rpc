package com.lrh.rpc.utils;

import com.google.gson.Gson;
import com.lrh.rpc.transport.command.str.StringRequestCommandBody;
import com.lrh.rpc.transport.invoke.InvokeInfo;

/**
 * @description:
 * @author: lrh
 * @date: 2020/10/24 11:42
 */
public class JsonUtils {

  private static final Gson GSON = new Gson();

  public static String toJson(Object o) {
    return GSON.toJson(o);
  }

  public static <T> T fromJson(String json, Class<T> clazz) {
    return GSON.fromJson(json, clazz);
  }

}
