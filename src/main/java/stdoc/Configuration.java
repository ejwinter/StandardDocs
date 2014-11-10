package stdoc;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.util.JSONPObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by winte on 10/30/14.
 */
public class Configuration {
  private static final ObjectMapper configMapper = new ObjectMapper();

  private List<ParserMapping> parsers = Lists.newArrayList();

  public Configuration(){}

  public static Configuration getDefaults(){
    try {
      String defaultJson = Resources.toString(Resources.getResource("defaultConfiguration.json"), Charsets.UTF_8);
      return configMapper.readValue(defaultJson, Configuration.class);
    }catch(IOException e){
      throw new IllegalArgumentException("The configuration file is not in the proper format.");
    }
  }

  public static Configuration fromJson (File configurationFile) {
    try {
      ObjectReader updater = configMapper.readerForUpdating(getDefaults());
      return updater.readValue(configurationFile);
    } catch (IOException e) {
      throw new IllegalArgumentException("The configuration file is not in the proper format.");
    }
  }

  public static class ParserMapping{
    private String parser;
    private String[] patterns;

    public String getParser() {
      return parser;
    }

    public void setParser(String parser) {
      this.parser = parser;
    }

    public String[] getPatterns() {
      return patterns;
    }

    public void setPatterns(String[] patterns) {
      this.patterns = patterns;
    }
  }
}
