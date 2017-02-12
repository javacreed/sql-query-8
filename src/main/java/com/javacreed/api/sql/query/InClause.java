package com.javacreed.api.sql.query;

import java.util.ArrayList;
import java.util.List;

public class InClause implements Clause {

  private Column column;

  private final List<SqlValue> in = new ArrayList<>();

  public InClause() {}

  public InClause(final Column column) {
    this.column = column;
  }

  public InClause(final String name) {
    this(new Column(name));
  }

  public InClause(final String alias, final String name) {
    this(new Column(alias, name));
  }

  public InClause column(final Column column) {
    this.column = column;
    return this;
  }

  public InClause column(final String name) {
    return column(new Column(name));
  }

  public InClause column(final String alias, final String name) {
    return column(new Column(alias, name));
  }

  public Column getColumn() {
    return column;
  }

  public InClause literal(final Number literal) {
    return value(new NumberSqlValue(literal));
  }

  public InClause literal(final String literal) {
    return value(new StringSqlValue(literal));
  }

  public InClause parameter(final Object parameter) {
    return value(new ParameterSqlValue(parameter));
  }

  public InClause parameters(final Iterable<?> parameters) {
    for (final Object parameter : parameters) {
      parameter(parameter);
    }
    return this;
  }

  public InClause parameters(final Object parameter, final Object... parameters) {
    parameter(parameter);
    for (final Object p : parameters) {
      parameter(p);
    }
    return this;
  }

  public InClause parameters(final Object[] parameters) {
    for (final Object parameter : parameters) {
      parameter(parameter);
    }
    return this;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    if (false == in.isEmpty()) {
      builder.append(column).sql(" IN (").append(in.get(0));
      for (int i = 1, size = in.size(); i < size; i++) {
        builder.sql(", ").append(in.get(i));
      }
      builder.sql(")");
    }
    return builder;
  }

  public InClause value(final SqlValue value) {
    in.add(value);
    return this;
  }

  public InClause values(final SqlValue... values) {
    for (final SqlValue value : values) {
      in.add(value);
    }
    return this;
  }

}
