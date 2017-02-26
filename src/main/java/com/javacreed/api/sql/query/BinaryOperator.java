package com.javacreed.api.sql.query;

public enum BinaryOperator implements ToSql {

  EQUALS("="), LESS_THAN("<"), LESS_THAN_EQUALS("<="), GREATER_THAN(">"), GREATER_THAN_EQUALS(">=");

  private final String sign;

  private BinaryOperator(final String sign) {
    this.sign = sign;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql(sign);
  }
}
