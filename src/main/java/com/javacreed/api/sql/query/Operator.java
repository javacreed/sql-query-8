package com.javacreed.api.sql.query;

public enum Operator implements ToSql {

  EQUALS("="), LESS_THAN("=");

  private final String sign;

  private Operator(final String sign) {
    this.sign = sign;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql(sign);
  }
}
