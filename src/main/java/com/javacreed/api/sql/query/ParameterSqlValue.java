package com.javacreed.api.sql.query;

public class ParameterSqlValue implements SqlValue {

  private final Object parameter;

  public ParameterSqlValue(final Object parameter) {
    this.parameter = parameter;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql("?").parameter(parameter);
  }
}