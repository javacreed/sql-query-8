package com.javacreed.api.sql.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Clauses<C extends Clauses<?>> implements ToSql {

  private final String type;

  private final List<Clause> clauses = new ArrayList<>();

  public Clauses(final String type) {
    this.type = type;
  }

  public C clause(final Clause clause) {
    clauses.add(clause);
    return CastUtils.cast(this);
  }

  public C clauses(final Clause... clauses) {
    for (final Clause clause : clauses) {
      clause(clause);
    }

    return CastUtils.cast(this);
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    if (false == clauses.isEmpty()) {
      builder.prefixNextWrite(" " + type + " ");

      final Iterator<Clause> iterator = clauses.iterator();
      for (final int l = builder.length(); iterator.hasNext() && l == builder.length();) {
        builder.append(iterator.next());
      }

      while (iterator.hasNext()) {
        builder.prefixNextWrite(" AND ").append(iterator.next());
      }
      builder.clearPrefixNextWrite();
    }

    return builder;
  }
}