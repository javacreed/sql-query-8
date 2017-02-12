package com.javacreed.api.sql.query;

import java.util.Objects;

public class IsNotNullClause implements Clause {

  private Column column;

  public IsNotNullClause() {}

  public IsNotNullClause(final Column column) throws NullPointerException {
    column(column);
  }

  public IsNotNullClause(final String name) throws NullPointerException {
    this(new Column(name));
  }

  public IsNotNullClause(final String alias, final String name) throws NullPointerException {
    this(new Column(alias, name));
  }

  public IsNotNullClause column(final Column column) throws NullPointerException {
    this.column = Objects.requireNonNull(column);
    return null;
  }

  public IsNotNullClause column(final String name) throws NullPointerException {
    return column(new Column(name));
  }

  public IsNotNullClause column(final String alias, final String name) throws NullPointerException {
    return column(new Column(alias, name));
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    if (column != null) {
      builder.append(column).sql(" IS NOT NULL");
    }
    return builder;
  }
}
