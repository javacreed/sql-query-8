package com.javacreed.api.sql.query;

import java.util.Collection;

public class CollectionUtils {

  public static boolean isNotNullOrEmpty(final Collection<?> collection) {
    return false == CollectionUtils.isNullOrEmpty(collection);
  }

  public static boolean isNullOrEmpty(final Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static <T, C extends Collection<T>> C requireNotEmpty(final C collection, final String message)
      throws IllegalArgumentException {
    if (CollectionUtils.isNullOrEmpty(collection)) {
      throw new IllegalArgumentException(message);
    }

    return collection;
  }

  private CollectionUtils() {}
}
