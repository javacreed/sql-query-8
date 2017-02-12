package com.javacreed.api.sql.query;

public class JoinTable extends AbstractJoin {

  private final Table table;

  public JoinTable(final JoinType type, final String name) {
    this(type, new Table(name));
  }

  public JoinTable(final JoinType type, final String name, final String alias) {
    this(type, new Table(name, alias));
  }

  public JoinTable(final JoinType type, final Table table) {
    super(type);
    this.table = table;
  }

  public String getTableAlias() {
    return Table.getTableAlias(table);
  }

  public String getTableName() {
    return Table.getTableName(table);
  }

  public String getTableSchema() {
    return Table.getTableSchema(table);
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    return builder.sql(" ").append(type).sql(" JOIN ").append(table).append(condition);
  }
}
