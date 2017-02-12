package com.javacreed.api.obaf.sql.query;

import org.junit.Assert;
import org.junit.Test;

import com.javacreed.api.sql.query.InClause;
import com.javacreed.api.sql.query.Query;

public class InClause_Test {

  @Test
  public void blank() {
    final InClause clause = new InClause("abc");
    final Query query = clause.getQuery();
    Assert.assertEquals("", query.getSql());
    Assert.assertTrue(query.getParameters().isEmpty());
  }
}
