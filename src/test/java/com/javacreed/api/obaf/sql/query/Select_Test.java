package com.javacreed.api.obaf.sql.query;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.javacreed.api.sql.query.EqualsParameterizedClause;
import com.javacreed.api.sql.query.EqualsValueClause;
import com.javacreed.api.sql.query.InClause;
import com.javacreed.api.sql.query.Query;
import com.javacreed.api.sql.query.Select;

public class Select_Test {

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

  @Test
  public void test() {
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
}
