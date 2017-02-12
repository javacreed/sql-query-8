package com.javacreed.api.sql.query;

@FunctionalInterface
public interface ToSql {

  default Query getQuery() {
    return toSql(new QueryBuilder()).build();
  }

  QueryBuilder toSql(QueryBuilder builder);
}