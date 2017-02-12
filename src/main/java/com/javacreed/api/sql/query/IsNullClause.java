package com.javacreed.api.sql.query;

import java.util.Objects;

public class IsNullClause implements Clause {

  private Column column;

  public IsNullClause() {}

  public IsNullClause(final Column column) throws NullPointerException {
    column(column);
  }

  public IsNullClause(final String name) throws NullPointerException {
    this(new Column(name));
  }

  public IsNullClause(final String alias, final String name) throws NullPointerException {
    this(new Column(alias, name));
  }

  public IsNullClause column(final Column column) throws NullPointerException {
    this.column = Objects.requireNonNull(column);
    return null;
  }

  public IsNullClause column(final String name) throws NullPointerException {
    return column(new Column(name));
  }

  public IsNullClause column(final String alias, final String name) throws NullPointerException {
    return column(new Column(alias, name));
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    if (column != null) {
      builder.append(column).sql(" IS NULL");
    }
    return builder;
  }
}
