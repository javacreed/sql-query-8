package com.javacreed.api.sql.query;

public class EqualsClause extends BinaryOperatorClause {

  public EqualsClause(final String lhsName, final String rhsName) {
    this(new Column(lhsName), new Column(rhsName));
  }

  public EqualsClause(final String lhsAlias, final String lhsName, final String rhsAlias, final String rhsName) {
    this(new Column(lhsAlias, lhsName), new Column(rhsAlias, rhsName));
  }

  public EqualsClause(final String lhsAlias, final String lhsName, final ToSql rhs) {
    this(new Column(lhsAlias, lhsName), rhs);
  }

  public EqualsClause(final String lhsName, final ToSql rhs) {
    this(new Column(lhsName), rhs);
  }

  public EqualsClause(final ToSql lhs, final ToSql rhs) {
    super(lhs, Operator.EQUALS, rhs);
  }
}