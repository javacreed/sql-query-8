package com.javacreed.api.sql.query;

public class Table implements ToSql {

  public static String getTableAlias(final Table table) {
    return table == null ? null : table.getAlias();
  }

  public static String getTableName(final Table table) {
    return table == null ? null : table.getName();
  }

  public static String getTableSchema(final Table table) {
    return table == null ? null : table.getSchema();
  }

  private final String schema;
  private final String name;
  private final String alias;

  public Table(final String name) {
    this(name, null);
  }

  public Table(final String name, final String alias) {
    this(null, name, alias);
  }

  public Table(final String schema, final String name, final String alias) {
    this.schema = schema;
    this.name = name;
    this.alias = alias;
  }

  public String getAlias() {
    return alias;
  }

  public String getName() {
    return name;
  }

  public String getSchema() {
    return schema;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    if (StringUtils.isNotBlank(schema)) {
      builder.sql(schema).sql(".");
    }

    builder.sql(name);
    if (StringUtils.isNotBlank(alias)) {
      builder.sql(" ").sql(alias);
    }

    return builder;
  }
}