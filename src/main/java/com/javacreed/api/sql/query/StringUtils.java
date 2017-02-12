package com.javacreed.api.sql.query;

import java.util.Collection;
import java.util.Formatter;

public class StringUtils {

  public static String defaultIfBlank(final String string, final String defaultValue) {
    return StringUtils.isBlank(string) ? defaultValue : string;
  }

  public static String defaultIfNull(final String string) {
    return StringUtils.defaultIfNull(string, "");
  }

  public static String defaultIfNull(final String string, final String defaultValue) {
    return string == null ? defaultValue : string;
  }

  /**
   * <p>
   * Compares two CharSequences, returning {@code true} if they represent equal sequences of characters.
   * </p>
   *
   * <p>
   * {@code null}s are handled without exceptions. Two {@code null} references are considered to be equal. The
   * comparison is case sensitive.
   * </p>
   *
   * <pre>
   * StringUtils.equals(null, null)   = true
   * StringUtils.equals(null, "abc")  = false
   * StringUtils.equals("abc", null)  = false
   * StringUtils.equals("abc", "abc") = true
   * StringUtils.equals("abc", "ABC") = false
   * </pre>
   *
   * @see Object#equals(Object)
   * @param cs1
   *          the first CharSequence, may be {@code null}
   * @param cs2
   *          the second CharSequence, may be {@code null}
   * @return {@code true} if the CharSequences are equal (case-sensitive), or both {@code null}
   * @since 3.0 Changed signature from equals(String, String) to equals(CharSequence, CharSequence)
   */
  public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
    if (cs1 == cs2) {
      return true;
    }
    if (cs1 == null || cs2 == null) {
      return false;
    }
    if (cs1.length() != cs2.length()) {
      return false;
    }
    if (cs1 instanceof String && cs2 instanceof String) {
      return cs1.equals(cs2);
    }
    return StringUtils.regionMatches(cs1, false, 0, cs2, 0, cs1.length());
  }

  /**
   * It's a wrapper method of {@link #glue(String, Object...)}. It's equals to invoke
   *
   * <pre>
   * return glue(glue, itemsToGlue.toArray(new Object[itemsToGlue.size()]));
   * </pre>
   *
   * Read documentation about{@link #glue(String, Object...)} method for more details.
   *
   * @param glue
   *          string used for gluing
   * @param itemsToGlue
   *          items to glue
   * @return a string that have the string {@code glue} between a pair of itmes contained in collection
   *         {@code itemsToGlue}
   */
  public static String glue(final String glue, final Collection<?> itemsToGlue) {
    if (itemsToGlue == null) {
      return null;
    }

    return StringUtils.glue(glue, itemsToGlue.toArray(new Object[itemsToGlue.size()]));
  }

  /**
   * The method return a string where are listed the items in {@code itemsToGlue}. {@code glue} is used as separator
   * between the all items. E.g. glueDiffLast(" + ", "1", "2", "3", "4") return the string "1 + 2 + 3 + 4"
   *
   * @param glue
   *          String value used for gluing the items
   * @param itemsToGlue
   *          The items list
   * @return a string where are listed the items in {@cod itemsToglue}
   */
  public static String glue(final String glue, final String... itemsToGlue) {
    if (itemsToGlue == null) {
      return null;
    }

    if (itemsToGlue.length == 0) {
      return "";
    }

    if (itemsToGlue.length == 1) {
      return itemsToGlue[0];
    }

    final StringBuilder builder = new StringBuilder();
    builder.append(itemsToGlue[0]);

    for (int i = 1, size = itemsToGlue.length; i < size; i++) {
      builder.append(glue).append(itemsToGlue[i]);
    }

    return builder.toString();
  }

  /**
   * It's a wrapper method of {@link #glueDiffLast(String, String[], Object[])}. It's equals to invoke
   *
   * <pre>
   * return glue(glue, formats, itemsToGlue.toArray(new Object[itemsToGlue.size()]));
   * </pre>
   *
   * It returns {@code null} if {@code itemsToGlue} is {@code null}.
   *
   * See {@link #glue(String, String[], Object[])} for more details.
   *
   * @param glue
   *          String value used for gluing the items
   * @param itemsToGlue
   *          The items list
   * @param formats
   *          a list of valid format strings
   * @return a string where are listed the items in {@cod itemsToglue}
   */
  public static String glue(final String glue, final String[] formats, final Collection<?> itemsToGlue) {
    if (itemsToGlue == null) {
      return null;
    }

    return StringUtils.glue(glue, formats, itemsToGlue.toArray(new Object[itemsToGlue.size()]));
  }

  /**
   * The method return a string where are listed the {@code Object} items in {@code itemsToGlue}. {@code glue} is used
   * as separator between the all items, and items in {@code formats} are used for formatting the string value of each
   * object. Each item in {@code formats} must be a valid format string. For more details see {@link Formatter}
   * documentation.
   *
   * @param glue
   *          String value used for gluing the items
   * @param itemsToGlue
   *          The items list
   * @param formats
   *          a list of valid format strings
   * @return a string where are listed the items in {@cod itemsToglue}
   */
  public static String glue(final String glue, final String[] formats, final Object[] itemsToGlue) {
    final String[] strings = new String[itemsToGlue.length];
    for (int i = 0; i < strings.length; i++) {
      strings[i] = String.format(formats[i], itemsToGlue[i]);
    }

    return StringUtils.glue(glue, strings);
  }

  /**
   *
   * @param glue
   * @param itemsToGlue
   * @return
   */
  public static <T> String glue(final String glue, @SuppressWarnings("unchecked") final T... itemsToGlue) {
    final String[] strings = new String[itemsToGlue.length];
    for (int i = 0; i < strings.length; i++) {
      strings[i] = StringUtils.valueOf(itemsToGlue[i]);
    }

    return StringUtils.glue(glue, strings);
  }

  /**
   *
   * @param glue
   * @param lastGlue
   * @param itemsToGlue
   * @return
   */
  public static String glueDiffLast(final String glue, final String lastGlue, final Collection<?> itemsToGlue) {
    if (itemsToGlue == null) {
      return null;
    }

    return StringUtils.glueDiffLast(glue, lastGlue, itemsToGlue.toArray());
  }

  /**
   *
   * @param glue
   * @param lastGlue
   * @param itemsToGlue
   * @return
   */
  public static String glueDiffLast(final String glue, final String lastGlue, final Object... itemsToGlue) {
    final String[] strings = new String[itemsToGlue.length];
    for (int i = 0; i < strings.length; i++) {
      strings[i] = StringUtils.valueOf(itemsToGlue[i]);
    }

    return StringUtils.glueDiffLast(glue, lastGlue, strings);
  }

  /**
   * The method return a string where are listed the items in {@code itemsToGlue}. {@code glue} is used as separator
   * between the all items minus the last. As the last separator will be used {@code lastGlue}. E.g. glueDiffLast(" + ",
   * " = ", "1", "2", "3", "4", "10") return the string "1 + 2 + 3 + 4 = 10"
   *
   * @param glue
   *          String value used for gluing the items
   * @param lastGlue
   *          String value used for gluing the last item to previous
   * @param itemsToGlue
   *          The items list
   * @return
   */
  public static String glueDiffLast(final String glue, final String lastGlue, final String... itemsToGlue) {
    if (itemsToGlue == null) {
      return null;
    }

    if (itemsToGlue.length == 0) {
      return "";
    }

    if (itemsToGlue.length == 1) {
      return String.valueOf(itemsToGlue[0]);
    }

    final int length = itemsToGlue.length - 1;
    final Object[] firstItemsToGlue = new Object[length];
    System.arraycopy(itemsToGlue, 0, firstItemsToGlue, 0, length);

    return StringUtils.glue(glue, firstItemsToGlue) + lastGlue + itemsToGlue[length];
  }

  /**
   * It's a wrapper method of {@link #glue(String, Object...)}. It's equals to
   *
   * <pre>
   * dim = (endIndex - startIndex) + 1;
   * String[] newSplittedString = new String[dim];
   * System.arraycopy(itemsToGlue, startIndex, newSplittedString, 0, dim);
   *
   * return glue(glue, newSplittedString);
   * </pre>
   *
   * Read documentation about{@link #glue(String, Object...)} method for more details.
   *
   * @param glue
   *          string used for gluing
   * @param itemsToGlue
   *          items to glue
   * @return a string that have the string {@code glue} between a pair of itmes contained in collection
   *         {@code itemsToGlue}
   */
  public static String glueFromTo(String glue, final int startIndex, int endIndex, final String... itemsToGlue)
      throws IndexOutOfBoundsException {
    if (itemsToGlue == null) {
      return null;
    }

    if (glue == null) {
      glue = "";
    }

    if (startIndex > endIndex || startIndex < 0 || startIndex > itemsToGlue.length) {
      throw new IndexOutOfBoundsException(
          "The start index is invalid. It is greater than the number of occurrences of delim char.");
    }

    if (endIndex >= itemsToGlue.length) {
      endIndex = itemsToGlue.length - 1;
    }

    final int dim = endIndex - startIndex + 1;
    final String[] newSplittedString = new String[dim];
    System.arraycopy(itemsToGlue, startIndex, newSplittedString, 0, dim);

    return StringUtils.glue(glue, newSplittedString);
  }

  public static boolean hasLength(final CharSequence str) {
    return str != null && str.length() > 0;
  }

  public static boolean hasLength(final String str) {
    return StringUtils.hasLength((CharSequence) str);
  }

  public static boolean isBlank(final CharSequence string) {
    if (string != null) {
      final int length = string.length();
      if (length > 0) {
        for (int i = 0; i < length; i++) {
          if (Character.isWhitespace(string.charAt(i)) == false) {
            return false;
          }
        }
      }
    }

    return true;
  }

  public static boolean isNotBlank(final String string) {
    return false == StringUtils.isBlank(string);
  }

  /**
   * Green implementation of regionMatches.
   *
   * @param cs
   *          the {@code CharSequence} to be processed
   * @param ignoreCase
   *          whether or not to be case insensitive
   * @param thisStart
   *          the index to start on the {@code cs} CharSequence
   * @param substring
   *          the {@code CharSequence} to be looked for
   * @param start
   *          the index to start on the {@code substring} CharSequence
   * @param length
   *          character length of the region
   * @return whether the region matched
   */
  public static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
      final CharSequence substring, final int start, final int length) {
    if (cs instanceof String && substring instanceof String) {
      return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
    }
    int index1 = thisStart;
    int index2 = start;
    int tmpLen = length;

    // Extract these first so we detect NPEs the same as the java.lang.String version
    final int srcLen = cs.length() - thisStart;
    final int otherLen = substring.length() - start;

    // Check for invalid parameters
    if (thisStart < 0 || start < 0 || length < 0) {
      return false;
    }

    // Check that the regions are long enough
    if (srcLen < length || otherLen < length) {
      return false;
    }

    while (tmpLen-- > 0) {
      final char c1 = cs.charAt(index1++);
      final char c2 = substring.charAt(index2++);

      if (c1 == c2) {
        continue;
      }

      if (!ignoreCase) {
        return false;
      }

      // The same check as in String.regionMatches():
      if (Character.toUpperCase(c1) != Character.toUpperCase(c2)
          && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
        return false;
      }
    }

    return true;
  }

  public static <T extends CharSequence> T requireNonBlank(final T t, final String message) {
    if (StringUtils.isBlank(t)) {
      throw new IllegalArgumentException(message);
    }

    return t;
  }

  public static String trim(final String string) {
    return string == null ? null : string.trim();
  }

  public static String trimAllWhitespace(final String str) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }
    final int len = str.length();
    final StringBuilder sb = new StringBuilder(str.length());
    for (int i = 0; i < len; i++) {
      final char c = str.charAt(i);
      if (!Character.isWhitespace(c)) {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static String valueOf(final Object object) {
    if (object == null) {
      return null;
    }

    return object.toString();
  }

  private StringUtils() {}
}
