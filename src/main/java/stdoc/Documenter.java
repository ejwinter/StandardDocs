package stdoc;

import com.martiansoftware.jsap.*;

import java.io.File;

public class Documenter{
  public static void main(String[] args){
    JSAPResult argResult = new ArgsParser().parse(args);

    File projectDirectory = argResult.getFile("projectDirectory");

    File configurationFile = argResult.getFile("configuration", new File(projectDirectory, "stdoc.json"));

    Configuration configuration = (configurationFile.isFile() ? Configuration.fromJson(configurationFile) : Configuration.getDefaults());





  }

  private static class ArgsParser{
    private JSAP jsap = new JSAP();

    public ArgsParser() {

      try {
        jsap.registerParameter(new FlaggedOption("configuration")
                .setShortFlag('c')
                .setLongFlag("configuration")
                .setHelp("This is the path to a configuration file that will be used.  Defaults to stdoc.json found in the project directory.")
        );

        jsap.registerParameter(new UnflaggedOption("projectDirectory")
                .setRequired(true)
                .setHelp("This is the directory that contains the project we will scan for document files.")
        );


      }catch(JSAPException e){
        throw new IllegalStateException(e);
      }
    }

    public JSAPResult parse(String[] args){
      return jsap.parse(args);
    }
  }
}