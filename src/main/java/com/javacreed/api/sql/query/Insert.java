package com.javacreed.api.sql.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Insert implements ToSql {

  private Table table;

  private final List<Column> columns = new ArrayList<>();

  private final List<SqlValue> values = new ArrayList<>();

  public Insert() {}

  public Insert(final String name) throws NullPointerException {
    table(name);
  }

  public Insert(final Table table) throws NullPointerException {
    table(table);
  }

  public Insert table(final String name) throws NullPointerException {
    return table(new Table(name));
  }

  public Insert table(final Table table) throws NullPointerException {
    this.table = Objects.requireNonNull(table);
    return this;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    builder.sql("INSERT INTO ").append(table);

    if (CollectionUtils.isNotNullOrEmpty(columns)) {
      builder.sql(" (").append(columns.get(0));
      for (int i = 1, size = columns.size(); i < size; i++) {
        builder.sql(", ").append(columns.get(i));
      }
      builder.sql(")");
    }

    if (CollectionUtils.isNotNullOrEmpty(values)) {
      builder.sql(" VALUES (").append(values.get(0));
      for (int i = 1, size = values.size(); i < size; i++) {
        builder.sql(", ").append(values.get(i));
      }
      builder.sql(")");
    }

    return builder;
  }

  public Insert value(final Column column, final SqlValue value) {
    this.columns.add(column);
    this.values.add(value);
    return this;
  }

  public Insert value(final SqlValue value) {
    this.values.add(value);
    return this;
  }

  public Insert value(final String name, final String value) {
    return value(new Column(name), new StringSqlValue(value));
  }

}
