package com.javacreed.api.sql.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultQuery implements Query {

  private final String sql;

  private final List<Object> parameters;

  private String toString;

  public DefaultQuery(final String sql, final List<Object> parameters) {
    this.sql = sql;
    if (CollectionUtils.isNullOrEmpty(parameters)) {
      this.parameters = Collections.emptyList();
    } else {
      this.parameters = Collections.unmodifiableList(new ArrayList<>(parameters));
    }
  }

  public DefaultQuery(final String sql, final Object... parameters) {
    this.sql = sql;
    if (ArrayUtils.isNullOrEmpty(parameters)) {
      this.parameters = Collections.emptyList();
    } else {
      this.parameters = Arrays.asList(parameters);
    }
  }

  @Override
  public List<Object> getParameters() {
    return parameters;
  }

  @Override
  public String getSql() {
    return sql;
  }

  @Override
  public String toString() {
    if (toString == null) {
      toString = (StringUtils.isBlank(sql) ? "Blank Query" : sql) + " (using "
          + (CollectionUtils.isNullOrEmpty(parameters) ? "no parameters"
              : "'" + StringUtils.glueDiffLast("', '", "' and '", parameters) + "'")
          + ")";
    }
    return toString;
  }
}