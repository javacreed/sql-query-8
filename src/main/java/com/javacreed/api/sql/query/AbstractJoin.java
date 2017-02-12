package com.javacreed.api.sql.query;

public abstract class AbstractJoin implements Join {

  protected final JoinType type;
  protected JoinCondition condition = NoJoinCondition.INSTANCE;

  public AbstractJoin(final JoinType type) {
    this.type = type;
  }

  public AbstractJoin on(final Clause... clauses) {
    condition = new On().clauses(clauses);
    return this;
  }

  public AbstractJoin on(final String lhsName, final String rhsName) {
    return on(new EqualsClause(lhsName, rhsName));
  }

  public AbstractJoin on(final String lhsAlias, final String lhsName, final String rhsAlias, final String rhsName) {
    return on(new EqualsClause(lhsAlias, lhsName, rhsAlias, rhsName));
  }

  public AbstractJoin using(final String column) {
    condition = new Using(column);
    return this;
  }

}
