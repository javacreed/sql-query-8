package com.javacreed.api.sql.query;

import java.util.ArrayList;
import java.util.List;

public class Joins implements ToSql {

  private final List<Join> joins = new ArrayList<>();

  public Joins add(final Join join) {
    joins.add(join);
    return this;
  }

  public boolean isJoinedWith(final String name) {
    for (final Join join : joins) {
      if (join instanceof JoinTable) {
        final JoinTable joinTable = (JoinTable) join;
        if (name.equalsIgnoreCase(joinTable.getTableName())) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean isJoinedWith(final String name, final String alias) {
    for (final Join join : joins) {
      if (join instanceof JoinTable) {
        final JoinTable joinTable = (JoinTable) join;
        if (name.equalsIgnoreCase(joinTable.getTableName()) && alias.equalsIgnoreCase(joinTable.getTableAlias())) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean isJoinedWith(final Table table) {
    if (table.getName() != null) {
      if (table.getAlias() != null) {
        return isJoinedWith(table.getName(), table.getAlias());
      }

      return isJoinedWith(table.getName());
    }

    return false;
  }

  @Override
  public QueryBuilder toSql(final QueryBuilder builder) {
    for (final Join join : joins) {
      builder.append(join);
    }
    return builder;
  }

}
