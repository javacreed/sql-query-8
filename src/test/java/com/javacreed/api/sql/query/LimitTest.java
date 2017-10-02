package com.javacreed.api.sql.query;

import org.junit.Assert;
import org.junit.Test;

public class LimitTest {

  @Test
  public void test() {
    Assert.assertEquals("", new Limit().getQuery().getSql());
    Assert.assertEquals(" LIMIT 10", new Limit(10).getQuery().getSql());
    Assert.assertEquals(" LIMIT 10 OFFSET 5", new Limit(10, 5).getQuery().getSql());
    Assert.assertEquals("", new Limit().offset(5).getQuery().getSql());
  }
}
