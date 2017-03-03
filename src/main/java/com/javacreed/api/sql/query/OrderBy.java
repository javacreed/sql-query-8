package com.javacreed.api.sql.query;

import java.util.ArrayList;
import java.util.List;

public class OrderBy implements ToSql {

  public static enum Direction implements ToSql {
    ASC(" ASC"), DESC(" DESC");

    private final String sql;

    private Direction(final String sql) {
      this.sql = sql;
    }

    @Override
    public QueryBuilder toSql(final QueryBuilder builder) {
      return builder.sql(sql);
    }
  }

  public static class OrderColumn implements ToSql {
    private Column column;
    private Direction direction;

    public OrderColumn() {}

    public OrderColumn(final Column column) {
      this.column = column;
    }

    public OrderColumn(final Column column, final Direction direction) {
      this.column = column;
      this.direction = direction;
    }

    public OrderColumn column(final Column column) {
      this.column = column;
      return this;
    }

    public OrderColumn column(final Column column, final Direction direction) {
      return column(column).direction(direction);
    }

    public OrderColumn direction(final Direction direction) {
      this.direction = direction;
      return this;
    }

    @Override
    public QueryBuilder toSql(final QueryBuilder builder) {
      if (column != null) {
        builder.append(column);
        if (direction != null) {
          builder.append(direction);
        }
      }
      return builder;
    }
  }

  private final List<ToSql> columns = new ArrayList<>();

  public OrderBy column(final Column column) {
    columns.add(new OrderColumn(column));
    return this;
  }

  public OrderBy column(final Column column, final Direction direction) {
    columns.add(new OrderColumn(column, direction));
    return this;
  }

  public OrderBy column(final String name) {
    return column(new Column(name));
  }

  public OrderBy column(final String name, final Direction direction) {
    return column(new Column(name), direction);
  }

  public OrderBy column(final String alias, final String name) {
    return column(new Column(alias, name));
  }

  public OrderBy column(final String alias, final String name, final Direction direction) {
    return column(new Column(alias, name), direction);
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    if (false == columns.isEmpty()) {
      builder.sql(" ORDER BY ").append(columns.get(0));

      for (int i = 1, size = columns.size(); i < size; i++) {
        builder.sql(", ").append(columns.get(i));
      }
    }
    return builder;
  }
}
