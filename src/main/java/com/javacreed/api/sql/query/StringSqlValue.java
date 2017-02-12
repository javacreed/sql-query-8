package com.javacreed.api.sql.query;

public class StringSqlValue implements SqlValue {

  private final String value;

  public StringSqlValue(final String value) {
    this.value = value;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql("'").sql(SqlValueUtils.cleanStringValue(value)).sql("'");
  }
}