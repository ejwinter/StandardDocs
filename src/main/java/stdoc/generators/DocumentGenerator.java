package stdoc.generators;

import com.google.common.base.Optional;
import stdoc.dom.StandardDoc;

import java.io.File;
import java.util.List;

/**
 * Generate the documentation from a set of StandardDocs
 */
public interface DocumentGenerator {

  /**
   * Create the document and optionally write it out to a file.
   *
   * @param standardDocs the docs sorted as they will be rendered
   * @param toWriteTo specify the file if you want to write the document to a file
   * @return a string representation of the documentation.  If the type is a binrary (doc, pdf) this will
   * not match the contents of the file.
   */
  public String generateDocument(List<StandardDoc> standardDocs, Optional<File> toWriteTo);
}
