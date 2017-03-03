package com.javacreed.api.sql.query;

public class Limit implements ToSql {

  private long limit;

  private long offset;

  public Limit() {}

  public Limit(final long limit) {
    this.limit = limit;
  }

  public Limit(final long limit, final long offset) {
    this.limit = limit;
    this.offset = offset;
  }

  public Limit limit(final long limit) {
    this.limit = limit;
    return this;
  }

  public Limit limit(final long limit, final long offset) {
    return limit(limit).offset(offset);
  }

  public Limit offset(final long offset) {
    this.offset = offset;
    return this;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    if (limit > 0) {
      builder.sql(" LIMIT ").sql(limit);
      if (offset > 0) {
        builder.sql(" OFFSET ").sql(offset);
      }
    }
    return builder;
  }
}
