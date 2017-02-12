package com.javacreed.api.sql.query;

public class Column implements ToSql {

  private final String alias;
  private final String name;

  public Column(final String name) {
    this(null, name);
  }

  public Column(final String alias, final String name) {
    this.alias = alias;
    this.name = name;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    if (StringUtils.isNotBlank(alias)) {
      builder.sql(alias).sql(".");
    }

    return builder.sql(name);
  }
}