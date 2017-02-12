package com.javacreed.api.sql.query;

public class NoJoinCondition implements JoinCondition {

  public static final JoinCondition INSTANCE = new NoJoinCondition();

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder;
  }

}
