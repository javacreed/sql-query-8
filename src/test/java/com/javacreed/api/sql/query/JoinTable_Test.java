package com.javacreed.api.sql.query;

import org.junit.Assert;
import org.junit.Test;

public class JoinTable_Test {

  @Test
  public void innerJoinOnOneColumn() {
    final JoinTable join = new JoinTable(JoinType.INNER, "abc", "b");
    join.on("a", "id", "b", "abc_id");

    final Query query = join.getQuery();
    Assert.assertEquals(" INNER JOIN abc b ON a.id=b.abc_id", query.getSql());
    Assert.assertTrue(query.getParameters().isEmpty());
  }

  @Test
  public void simpleInnerJoin() {
    final JoinTable join = new JoinTable(JoinType.INNER, "abc");

    final Query query = join.getQuery();
    Assert.assertEquals(" INNER JOIN abc", query.getSql());
    Assert.assertTrue(query.getParameters().isEmpty());
  }
}
