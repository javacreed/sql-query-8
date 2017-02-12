package com.javacreed.api.sql.query;

public class NumberSqlValue implements SqlValue {

  private final Number value;

  public NumberSqlValue(final Number value) {
    this.value = value;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql(value.toString());
  }
}