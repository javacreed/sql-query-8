package com.javacreed.api.sql.query;

import org.junit.Assert;
import org.junit.Test;

public class ClausesTest {

  @Test
  public void emptyInClause() {
    final On clauses = new On();
    clauses.clause(new InClause("a"));

    final Query query = clauses.getQuery();
    Assert.assertEquals("", query.getSql());
  }

  @Test
  public void inClauseWithOneValue() {
    final On clauses = new On();
    clauses.clause(new InClause("a").literal(1));

    final Query query = clauses.getQuery();
    Assert.assertEquals(" ON a IN (1)", query.getSql());
  }

  @Test
  public void multipleClauses() {
    final On clauses = new On();
    clauses.clause(new InClause("a").literal(1));
    clauses.clause(new LikeClause("b").literal("xyz"));

    final Query query = clauses.getQuery();
    Assert.assertEquals(" ON a IN (1) AND b LIKE 'xyz'", query.getSql());
  }

  @Test
  public void multipleEmptyInClauses() {
    final On clauses = new On();
    clauses.clause(new InClause("a"));
    clauses.clause(new InClause("b"));

    final Query query = clauses.getQuery();
    Assert.assertEquals("", query.getSql());
  }
}
