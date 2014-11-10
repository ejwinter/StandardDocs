package stdoc;

import com.google.common.collect.Maps;
import stdoc.dom.StandardDoc;
import stdoc.parsers.Parser;

import java.util.Map;

/**
 * Created by winte on 11/1/14.
 */
public class StandardDocProcessor {

  private final Map<String, Parser> parsers = Maps.newHashMap();

  public StandardDoc process(String filename, Iterable<String> fileContents){
    return null;
  }
}
