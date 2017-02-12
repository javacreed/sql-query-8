package com.javacreed.api.sql.query;

public class BinaryOperatorClause implements Clause {

  private final ToSql lhs;
  private final ToSql operator;
  private final ToSql rhs;

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
    this.lhs = lhs;
    this.operator = operator;
    this.rhs = rhs;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.append(lhs).append(operator).append(rhs);
  }
}