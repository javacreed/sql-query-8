package com.javacreed.api.sql.query;

import java.util.Objects;

public class BetweenClause implements Clause {

  private ToSql parameter;
  private ToSql firstRange;
  private ToSql secondRange;

  public BetweenClause() {}

  public BetweenClause(final ToSql parameter, final ToSql firstRange, final ToSql secondRange) {
    p(parameter);
    fr(firstRange);
    sr(secondRange);
  }

  public BetweenClause fr(final ToSql firstRange) {
    this.firstRange = Objects.requireNonNull(firstRange);
    return this;
  }

  public BetweenClause frParameter(final Object parameter) {
    return fr(new ParameterSqlValue(parameter));
  }

  public BetweenClause p(final ToSql parameter) {
    this.parameter = Objects.requireNonNull(parameter);
    return this;
  }

  public BetweenClause pColumn(final String name) {
    return p(new Column(name));
  }

  public BetweenClause pColumn(final String alias, final String name) {
    return p(new Column(alias, name));
  }

  public BetweenClause sr(final ToSql secondRange) {
    this.secondRange = Objects.requireNonNull(secondRange);
    return this;
  }

  public BetweenClause srParameter(final Object parameter) {
    return sr(new ParameterSqlValue(parameter));
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    builder.append(parameter).sql(" BETWEEN ").append(firstRange).sql(" AND ").append(secondRange);
    return builder;
  }
}
