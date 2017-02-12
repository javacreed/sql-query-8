package com.javacreed.api.sql.query;

public enum JoinType implements ToSql {

  INNER("INNER"), LEFT_OUTER("LEFT OUTER"), RIGHT_OUTER("RIGHT OUTER");

  private final String sql;

  private JoinType(final String sql) {
    this.sql = sql;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql(sql);
  }

}
