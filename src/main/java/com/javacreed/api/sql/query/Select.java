package com.javacreed.api.sql.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Select implements ToSql {

  private boolean distinct = false;

  private final List<Column> columns = new ArrayList<>();

  private From from;

  private final Joins joins = new Joins();

  private final Where where = new Where();

  private final Map<String, Clause> labelledClauses = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

  public Select all(final String alias) {
    return column(new Column(alias, "*"));
  }

  public Select clause(final Clause clause) {
    where.clause(clause);
    return this;
  }

  public Select column(final Column column) {
    columns.add(column);
    return this;
  }

  public Select column(final String name) {
    return column(new Column(name));
  }

  public Select column(final String alias, final String name) {
    return column(new Column(alias, name));
  }

  public Select columns(final Column... column) {
    columns.addAll(Arrays.asList(column));
    return this;
  }

  public Select distinct() {
    distinct = true;
    return this;
  }

  public <T extends Clause> T findClause(final String label) {
    return CastUtils.cast(labelledClauses.get(label));
  }

  public Select from(final Select select, final String alias) {
    from = new FromSelect(select, alias);
    return this;
  }

  public Select from(final String name) {
    from = new FromTable(name);
    return this;
  }

  public Select from(final String name, final String alias) {
    from = new FromTable(name, alias);
    return this;
  }

  public boolean isJoinedWith(final String name) {
    return joins.isJoinedWith(name);
  }

  public boolean isJoinedWith(final String name, final String alias) {
    return joins.isJoinedWith(name, alias);
  }

  public boolean isJoinedWith(final Table table) {
    return joins.isJoinedWith(table);
  }

  public Select join(final Join join) {
    joins.add(join);
    return this;
  }

  public Select labelledClause(final String label, final Clause clause) throws DuplicateLabelException {
    if (labelledClauses.containsKey(label)) {
      throw new DuplicateLabelException(label);
    }
    labelledClauses.put(label, clause);
    return clause(clause);
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    builder.sql("SELECT ");

    if (distinct) {
      builder.sql("DISTINCT ");
    }

    if (columns.isEmpty()) {
      builder.sql("*");
    } else {
      final Column firstColumn = columns.get(0);
      builder.append(firstColumn);

      for (int i = 1, size = columns.size(); i < size; i++) {
        builder.sql(", ").append(columns.get(i));
      }
    }

    // From Table
    builder.append(from);

    // Joins
    builder.append(joins);

    // Where
    builder.append(where);

    return builder;
  }
}