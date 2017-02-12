package com.javacreed.api.sql.query;

public class QueryBuilderException extends RuntimeException {

  private static final long serialVersionUID = 2994303957366029094L;

  public QueryBuilderException() {
    super();
  }

  public QueryBuilderException(final String message) {
    super(message);
  }

  public QueryBuilderException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public QueryBuilderException(final Throwable cause) {
    super(cause);
  }

}
