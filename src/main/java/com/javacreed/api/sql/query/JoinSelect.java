package com.javacreed.api.sql.query;

public class JoinSelect extends AbstractJoin {

  private final Select select;
  private final String alias;

  public JoinSelect(final JoinType type, final Select select, final String alias) {
    super(type);
    this.select = select;
    this.alias = alias;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql(" ").append(type).sql(" (").append(select).sql(") ").sql(alias).append(condition);
  }
}
