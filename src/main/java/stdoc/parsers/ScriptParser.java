package stdoc.parsers;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import stdoc.dom.GeneralDetail;
import stdoc.dom.StandardDoc;

import java.util.List;
import java.util.SortedSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A generic parser for scripts.  These could include BASH, R, Perl, Python and most things with
 * a # comment signifier
 */
public class ScriptParser implements Parser{
  private static Joiner SPACE_JOINER = Joiner.on(' ').skipNulls();

  @Override
  public List<StandardDoc> parse(String filename, Iterable<String> toParse) {

    final ImmutableList<String> lines = ImmutableList.copyOf(toParse);

    final List<Range<Integer>> standardDocLineNumbers = getDocRanges(lines);

    ImmutableList.Builder<StandardDoc> docs = ImmutableList.builder();
    for(Range<Integer> docRange : standardDocLineNumbers){
      Optional<String> docSubtitle = extractSubtitle(lines.get(docRange.lowerEndpoint()));
      String docTitle = (!docSubtitle.isPresent() ? filename : filename+"#"+docSubtitle.get());
      StandardDoc doc = new StandardDoc(docTitle);
      doc.setDescription(extractDescription(lines, docRange));
      doc.setDetails(extractDetails(lines, docRange));
      doc.setUsage(findUsage(lines, docRange, standardDocLineNumbers));
      docs.add(doc);
    }
    return docs.build();
  }

  private String findUsage(ImmutableList<String> lines, Range<Integer> docRange, List<Range<Integer>> standardDocLineNumbers) {
    return null;
  }

  private SortedSet<GeneralDetail> extractDetails(ImmutableList<String> lines, Range<Integer> docRange) {
    return null;
  }

  private String extractDescription(ImmutableList<String> lines, Range<Integer> docRange) {
    List<String> descriptionLines = Lists.newArrayList();
    for(int i = docRange.lowerEndpoint()+1; i <= docRange.upperEndpoint()-1; i++){
      String line = lines.get(i).replaceFirst("#+","").trim();

      if(line.matches("^@.*$")){
        break;
      }else if(!Strings.isNullOrEmpty(line)){
        descriptionLines.add(line);
      }
    }
    return SPACE_JOINER.join(descriptionLines);
  }

  private static final Pattern STDOC_LINE_PATTERN = Pattern.compile("^#*@StandardDocs(.*)");
  private Optional<String> extractSubtitle(String standardDocLine) {

    Matcher matcher = STDOC_LINE_PATTERN.matcher(standardDocLine);
    if(!matcher.matches()){
      throw new StdDocParserException("The standard doc line doesn't match the expected pattern.");
    }

    if(matcher.groupCount() == 2){
      return Optional.of(matcher.group(1));
    }else{
      return Optional.absent();
    }
  }

  private List<Range<Integer>> getDocRanges(ImmutableList<String> lines) {
    final List<Range<Integer>> standardDocLineNumbers = Lists.newArrayListWithExpectedSize(1);

    Optional<Integer> start = Optional.absent();
    for(int lineCounter = 0; lineCounter < lines.size(); lineCounter++){
      String line = lines.get(lineCounter).toLowerCase();
      if(!start.isPresent() && line.contains("@standarddocs")){
        start = Optional.of(lineCounter);
      }else if(start.isPresent() && line.contains("@enddocs")) {
        standardDocLineNumbers.add(Range.closed(start.get(), lineCounter));
        start = Optional.absent();
      }
    }
    return standardDocLineNumbers;
  }


}
