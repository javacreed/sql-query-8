package com.javacreed.api.sql.query;

public class On extends Clauses<On> implements JoinCondition {

  public On() {
    super("ON");
  }
}
