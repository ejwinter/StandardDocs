package stdoc.parsers

import com.google.common.base.Charsets
import com.google.common.io.Resources
import stdoc.dom.StandardDoc

/**
 * Created by winte on 11/1/14.
 */
class ScriptParserTest extends spock.lang.Specification {
  ScriptParser parser = new ScriptParser();

  def "parse a good shell standard doc"(){
    when:
      List<StandardDoc> doc = parser.parse("testShell", Resources.readLines(Resources.getResource("project1/testShell.sh"), Charsets.US_ASCII))

    then:
      doc.get(0).getDescription() == "This is my description. It appears on two lines."
  }

  def "subtitles can be extracted from standard doc line"(){
    expect:
      subtitle == parser.extractSubtitle(line)

    where:
      subtitle              | line
      'the subtitle'        | '###@StandardDocs the substitle   '
  }


}
