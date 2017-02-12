package com.javacreed.api.sql.query;

public class LikeClause implements Clause {

  private Column column;

  private SqlValue value;

  public LikeClause(final Column column) {
    column(column);
  }

  public LikeClause(final String name) {
    column(new Column(name));
  }

  public LikeClause(final String alias, final String name) {
    column(new Column(alias, name));
  }

  public LikeClause column(final Column column) {
    this.column = column;
    return this;
  }

  public LikeClause literal(final String literal) {
    return value(new StringSqlValue(literal));
  }

  public LikeClause parameter(final Object parameter) {
    return value(new ParameterSqlValue(parameter));
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.append(column).sql(" LIKE ").append(value);
  }

  public LikeClause value(final SqlValue value) {
    this.value = value;
    return this;
  }
}
