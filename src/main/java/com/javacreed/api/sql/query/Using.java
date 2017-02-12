package com.javacreed.api.sql.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Using implements JoinCondition {

  private final List<Column> columns;

  public Using(final Column... columns) {
    this(Arrays.asList(columns));
  }

  public Using(final List<Column> columns) {
    this.columns = Collections.unmodifiableList(new ArrayList<>(columns));
  }

  public Using(final String name) {
    this(new Column(name));
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    builder.sql(" USING (").append(columns.get(0));
    for (int i = 1, size = columns.size(); i < size; i++) {
      builder.sql(", ").append(columns.get(i));
    }
    builder.sql(")");
    return builder;
  }
}