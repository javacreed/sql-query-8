package com.javacreed.api.sql.query;

public class SqlValueUtils {

  public static String cleanStringValue(final String value) {
    if (value.contains("\'")) {
      return value.replaceAll("\\\'", "\\\\\'");
    }

    return value;
  }

  public static SqlValue toSqlValue(final Object value) {
    if (value instanceof Number) {
      return new NumberSqlValue((Number) value);
    }

    if (value instanceof String) {
      return new StringSqlValue((String) value);
    }

    throw new UnsupportedSqlValueException();
  }

  private SqlValueUtils() {}

}
