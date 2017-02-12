package com.javacreed.api.sql.query;

import java.lang.reflect.Array;
import java.util.Objects;

public class ArrayUtils {

  /**
   * Returns {@code true} if the given {@code array} is either {@code null} or empty ({@code array.length == 0}),
   * {@code false} otherwise. This methods returns {@code false} if the given object is not of type array.
   *
   * @param array
   *          the array to check
   * @return {@code true} if the given {@code array} is either {@code null} or empty ({@code array.length == 0}),
   *         {@code false} otherwise
   */
  public static boolean isNullOrEmpty(final Object array) {
    if (array == null) {
      return true;
    }

    final Class<?> type = array.getClass();
    if (false == type.isArray()) {
      return false;
    }

    return Array.getLength(array) == 0;
  }

  public static <T> T requireElementsNonNull(final T array) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(array);

    final Class<?> type = array.getClass();
    if (false == type.isArray()) {
      throw new IllegalArgumentException();
    }

    for (int i = 0, size = Array.getLength(array); i < size; i++) {
      Objects.requireNonNull(Array.get(array, i));
    }

    return array;
  }

  private ArrayUtils() {}

}
