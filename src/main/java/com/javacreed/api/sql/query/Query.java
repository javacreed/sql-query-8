package com.javacreed.api.sql.query;

import java.util.List;

public interface Query {
  List<Object> getParameters();

  String getSql();
}