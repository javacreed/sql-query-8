package com.javacreed.api.sql.query;

public class DuplicateLabelException extends QueryBuilderException {

  private static final long serialVersionUID = 1168728340380991553L;

  private static String formatMessage(final String label) {
    return "Duplicate label: '" + label + "' found";
  }

  private final String label;

  public DuplicateLabelException(final String label) {
    super(DuplicateLabelException.formatMessage(label));
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

}
