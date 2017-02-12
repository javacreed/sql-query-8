package com.javacreed.api.sql.query;

public class FromSelect implements From {

  private final Select select;
  private final String alias;

  public FromSelect(final Select select, final String alias) {
    this.select = select;
    this.alias = alias;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql(" FROM (").append(select).sql(") ").sql(alias);
  }
}