package com.javacreed.api.sql.query;

public class FromTable implements From {

  private final Table table;

  public FromTable(final String name) {
    this(new Table(name));
  }

  public FromTable(final String name, final String alias) {
    this(new Table(name, alias));
  }

  public FromTable(final Table table) {
    this.table = table;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql(" FROM ").append(table);
  }
}