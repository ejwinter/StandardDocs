package stdoc.dom;

import com.google.common.collect.Sets;

import java.util.SortedSet;

/**
 * This is the root fo the StandardDoc object model.  Everything that goes into a standard doc will go into here.
 */
public class StandardDoc {

  /** the title of the script or function being documented */
  private String titleOfDocumented;

  /** the description section is that before the first @annotation */
  private String description;

  /** all the details / @annotations about the script or function */
  private SortedSet<GeneralDetail> details = Sets.newTreeSet();

  /** if the parser could identify a usage section it is represented here */
  private String usage;


  public StandardDoc(String titleOfDocumented) {
    this.titleOfDocumented = titleOfDocumented;
  }

  public String getTitleOfDocumented() {
    return titleOfDocumented;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SortedSet<GeneralDetail> getDetails() {
    return details;
  }

  public void setDetails(SortedSet<GeneralDetail> details) {
    this.details = details;
  }

  public String getUsage() {
    return usage;
  }

  public void setUsage(String usage) {
    this.usage = usage;
  }
}
