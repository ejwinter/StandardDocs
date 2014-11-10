package stdoc.dom;

/**
 * A way of storing details about the script/code being documented.
 *
 * @input:file myFile.txt this is the text of what this file is used for
 *
 *
 * @author Eric Winter
 *
 */
public class GeneralDetail implements Comparable<GeneralDetail>{

  public enum DetailType{
    /** the things that specified at invocation that affect the execution and what the effect is. */
    Input("@input"),
    /** a result of this script.  Any files or other details that are updated. */
    Output("@output"),
    /** what things are expected in the environment?  Is a tool need on the path?  Is there a library expected. */
    Environment("@env"),
    /** what other scripts/functions does this one hand off to and under what circumstances */
    Delegate("@delegate"),
    /** how is logging handled.  Is there a log file created?  What is the path to the log file?*/
    Log("@log"),
    /** anything else they may want to call out that we haven't anticipated.*/
    Other("@other");

    private final String markup;

    DetailType(String markup) {
      this.markup = markup;
    }

    public static DetailType findByMarkup(String markup){
      for(DetailType type : values()){
        if(type.markup.equalsIgnoreCase(markup)){
          return type;
        }
      }
      return Other;
    }
  }

  /** the subtype e.g. @input:file would be an input of type file */
  private String subType;

  /** a unique title of the input or output, some may not have titles */
  private String title;
  private String text;

  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public int compareTo(GeneralDetail o) {
    int byClass = this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
    if(byClass != 0) {
      return byClass;
    }

    int bySubtype = 0;
    if(this.subType == null){
      if(o.subType != null) {
        bySubtype = -1;
      }
    }else{
      if(o.subType == null){
        bySubtype = 1;
      }else{
        bySubtype = this.subType.compareTo(o.subType);
      }
    }

    if(bySubtype != 0){
      return bySubtype;
    }

    return this.title.compareTo(o.title);

  }
}
