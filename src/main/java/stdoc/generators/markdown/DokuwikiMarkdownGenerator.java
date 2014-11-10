package stdoc.generators.markdown;

import com.google.common.base.Optional;
import stdoc.dom.StandardDoc;
import stdoc.generators.DocumentGenerator;

import java.io.File;
import java.util.List;

/**
 * Creates the markdown compatible with Dokuwiki syntax
 */
public class DokuwikiMarkdownGenerator implements DocumentGenerator{

  /**
   *
   *
   *
   * @param list all of the StandardDocs ordered as you would like them included
   * @param toWriteTo if you want to write the contents to a file specify that here
   * @return the documentation as a single String representation.
   */
  @Override
  public String generateDocument(List<StandardDoc> list, Optional<File> toWriteTo) {
    return null;
  }
}
