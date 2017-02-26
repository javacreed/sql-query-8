package com.javacreed.api.sql.query;

import java.util.Objects;

public class BinaryOperatorClause implements Clause {

  private ToSql lhs;
  private ToSql operator;
  private ToSql rhs;

  public BinaryOperatorClause() {}

  public BinaryOperatorClause(final String lhsAlias, final String lhsName, final ToSql operator, final String rhsAlias,
      final String rhsName) {
    this(new Column(lhsAlias, lhsName), operator, new Column(rhsAlias, rhsName));
  }

  public BinaryOperatorClause(final String lhsAlias, final String lhsName, final ToSql operator, final ToSql rhs) {
    this(new Column(lhsAlias, lhsName), operator, rhs);
  }

  public BinaryOperatorClause(final String lhsName, final ToSql operator, final ToSql rhs) {
    this(new Column(lhsName), operator, rhs);
  }

  public BinaryOperatorClause(final ToSql lhs, final ToSql operator, final ToSql rhs) {
    lhs(lhs);
    this.operator = operator;
    this.rhs = rhs;
  }

  public BinaryOperatorClause lhs(final ToSql lhs) throws NullPointerException {
    this.lhs = Objects.requireNonNull(lhs);
    return this;
  }

  public BinaryOperatorClause lhsColumn(final String name) {
    return lhs(new Column(name));
  }

  public BinaryOperatorClause lhsColumn(final String alias, final String name) {
    return lhs(new Column(alias, name));
  }

  public BinaryOperatorClause lhsParameter(final Object parameter) {
    return lhs(new ParameterSqlValue(parameter));
  }

  public BinaryOperatorClause operator(final ToSql operator) {
    this.operator = Objects.requireNonNull(operator);
    return this;
  }

  public BinaryOperatorClause rhs(final ToSql rhs) {
    this.rhs = Objects.requireNonNull(rhs);
    return this;
  }

  public BinaryOperatorClause rhsColumn(final String name) {
    return rhs(new Column(name));
  }

  public BinaryOperatorClause rhsColumn(final String alias, final String name) {
    return rhs(new Column(alias, name));
  }

  public BinaryOperatorClause rhsParameter(final Object parameter) {
    return rhs(new ParameterSqlValue(parameter));
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.append(lhs).append(operator).append(rhs);
  }
}