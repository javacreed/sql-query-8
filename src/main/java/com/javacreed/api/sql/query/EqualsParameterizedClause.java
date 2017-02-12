package com.javacreed.api.sql.query;

public class EqualsParameterizedClause extends EqualsClause {

  public EqualsParameterizedClause(final Column column, final Object parameter) {
    super(column, new ParameterSqlValue(parameter));
  }

  public EqualsParameterizedClause(final String name, final Object parameter) {
    this(new Column(name), parameter);
  }

  public EqualsParameterizedClause(final String alias, final String name, final Object parameter) {
    this(new Column(alias, name), parameter);
  }
}