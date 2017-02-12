package com.javacreed.api.sql.query;

public class CastUtils {

  public static <T> T cast(final Object object) {
    @SuppressWarnings("unchecked")
    final T t = (T) object;
    return t;
  }

  private CastUtils() {}

}
