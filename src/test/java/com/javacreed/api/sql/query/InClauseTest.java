package com.javacreed.api.sql.query;

import org.junit.Assert;
import org.junit.Test;

public class InClauseTest {

  @Test
  public void blank() {
    final InClause clause = new InClause("abc");
    final Query query = clause.getQuery();
    Assert.assertEquals("", query.getSql());
    Assert.assertTrue(query.getParameters().isEmpty());
  }
}
