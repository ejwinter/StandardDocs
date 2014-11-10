package stdoc;

import com.google.common.collect.ImmutableSet;

import java.io.File;
import java.util.Collections;

/**
 * This will scan a directory for scripts that it can process and run each through
 */
public class StandardDocScanner {

  private final ImmutableSet<String> filePatterns;

  public StandardDocScanner(Iterable<String> filePatterns) {
    this.filePatterns = ImmutableSet.copyOf(filePatterns);
  }

  public Iterable<File> findFiles(File sourceDirectory){
    return Collections.emptyList();
  }
}
