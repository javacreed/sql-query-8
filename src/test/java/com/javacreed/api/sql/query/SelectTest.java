package com.javacreed.api.sql.query;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.javacreed.api.sql.query.OrderBy.Direction;

public class SelectTest {

  @Test
  public void emptyInClause() {
    final Select select = new Select();
    select.from("abc");
    select.clause(new InClause("a"));

    final Query query = select.getQuery();
    Assert.assertEquals("SELECT * FROM abc", query.getSql());
    Assert.assertNotNull(query.getParameters());
    Assert.assertTrue(query.getParameters().isEmpty());
  }

  @Test
  public void orderBY() {
    final Select select = new Select();
    select.from("abc");
    select.orderBy("a", Direction.DESC);

    final Query query = select.getQuery();
    Assert.assertEquals("SELECT * FROM abc ORDER BY a DESC", query.getSql());
    Assert.assertNotNull(query.getParameters());
    Assert.assertTrue(query.getParameters().isEmpty());
  }

  @Test
  public void selectWithLimit() {
    final Select select = new Select();
    select.from("abc");
    select.limit(10, 5);

    final Query query = select.getQuery();
    Assert.assertEquals("SELECT * FROM abc LIMIT 10 OFFSET 5", query.getSql());
    Assert.assertNotNull(query.getParameters());
    Assert.assertTrue(query.getParameters().isEmpty());
  }

  @Test
  public void selectWithParameters() {
    final Select select = new Select();
    select.from("abc", "a");
    select.clause(new EqualsParameterizedClause("id", 1));
    select.clause(new EqualsValueClause("name", "test'name"));

    final Query query = select.getQuery();
    Assert.assertEquals("SELECT * FROM abc a WHERE id=? AND name='test\\'name'", query.getSql());
    Assert.assertNotNull(query.getParameters());
    Assert.assertFalse(query.getParameters().isEmpty());
    Assert.assertEquals(Arrays.asList(1), query.getParameters());
  }

  @Test
  public void simpleSelectAll() {
    final Select select = new Select();
    select.from("abc");

    final Query query = select.getQuery();
    Assert.assertEquals("SELECT * FROM abc", query.getSql());
    Assert.assertNotNull(query.getParameters());
    Assert.assertTrue(query.getParameters().isEmpty());
  }

  @Test
  public void simpleSelectAllWithTableAlias() {
    final Select select = new Select();
    select.from("abc", "a");

    final Query query = select.getQuery();
    Assert.assertEquals("SELECT * FROM abc a", query.getSql());
    Assert.assertNotNull(query.getParameters());
    Assert.assertTrue(query.getParameters().isEmpty());
  }

  @Test
  public void simpleSelectOneColumn() {
    final Select select = new Select();
    select.column("a");
    select.from("abc");

    final Query query = select.getQuery();
    Assert.assertEquals("SELECT a FROM abc", query.getSql());
    Assert.assertNotNull(query.getParameters());
    Assert.assertTrue(query.getParameters().isEmpty());
  }
}
