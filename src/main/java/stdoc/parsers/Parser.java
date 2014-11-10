package stdoc.parsers;

import stdoc.dom.StandardDoc;

/**
 * Parsers have teh ability to parse a given file using a set of conventions to extract the StandardDoc
 */
public interface Parser {

  /**
   * does the same as the File parser but can act on a raw String.  This could be the first few hundred lines
   * of a file.
   */
  public java.util.List<StandardDoc> parse(String title, Iterable<String> lines);
}
