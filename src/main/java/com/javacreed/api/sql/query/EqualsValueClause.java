package com.javacreed.api.sql.query;

public class EqualsValueClause extends EqualsClause {

  public EqualsValueClause(final Column column, final String parameter) {
    super(column, new StringSqlValue(parameter));
  }

  public EqualsValueClause(final String name, final String parameter) {
    this(new Column(name), parameter);
  }

  public EqualsValueClause(final String alias, final String name, final String parameter) {
    this(new Column(alias, name), parameter);
  }
}