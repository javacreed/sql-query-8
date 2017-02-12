package com.javacreed.api.sql.query;

public class Main {

  public static void main(final String[] args) {
    final Select select = new Select();
    select.from("abc", "a");
    // select.join(new JoinTable(JoinType.INNER, "def", "b").on(new EqualsClause("a", "def_id", "b", "id")));
    // select.join(new JoinTable(JoinType.INNER, "ghi", "c").on(new EqualsClause("ghi_id", "id")));
    // select.join(new JoinSelect(JoinType.INNER, new Select().from("ghi"), "c").on(new EqualsClause("a", "ghi_id", "c",
    // "id")));
    // select.clause(new EqualsParameterizedClause("id", 1));
    // select.clause(new EqualsValueClause("name", "test'name"));
    select.clause(new InClause("id").parameters(1, 2).literal(4));

    System.out.println(select.getQuery());
  }

}
