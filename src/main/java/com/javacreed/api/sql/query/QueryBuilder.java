package com.javacreed.api.sql.query;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

  private final StringBuilder query = new StringBuilder();

  private final List<Object> parameters = new ArrayList<>();

  private String prefixNextWrite;

  public QueryBuilder() {}

  public QueryBuilder append(final ToSql toSql) {
    if (toSql == null) {
      return this;
    }

    return toSql.toSql(this);
  }

  public Query build() {
    return new DefaultQuery(query.toString(), parameters.toArray());
  }

  public QueryBuilder clearPrefixNextWrite() {
    this.prefixNextWrite = null;
    return this;
  }

  public int length() {
    return query.length();
  }

  public QueryBuilder parameter(final Object parameter) {
    parameters.add(parameter);
    return this;
  }

  public QueryBuilder parameters(final List<Object> parameters) {
    this.parameters.addAll(parameters);
    return this;
  }

  public QueryBuilder prefixNextWrite(final String prefixNextWrite) {
    this.prefixNextWrite = prefixNextWrite;
    return this;
  }

  public QueryBuilder sql(final Number sql) {
    return sql(sql.toString());
  }

  public QueryBuilder sql(final String sql) {
    if (prefixNextWrite != null) {
      query.append(prefixNextWrite);
      prefixNextWrite = null;
    }
    query.append(sql);
    return this;
  }

  public String toSql() {
    return query.toString();
  }
}
