package com.github.loicoudot.java4cpp;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Goal which generates C++ proxies files
 * 
 * @goal generate
 * 
 * @phase process-classes
 */
public class GenerateMojo extends AbstractMojo {
    /**
     * Location of the generated C++ proxies files.
     * 
     * @parameter property="${project.build.directory}"
     * @required
     */
    private String outputDirectory;

    /**
     * Clean output directory. Warning, deletes all files other than proxies in
     * the output directory.
     * 
     * @parameter default-value="false"
     */
    private boolean clean;

    /**
     * A comma separated list of jar files to analyse.
     * 
     * @parameter
     */
    private String jarFiles;

    /**
     * Sets to true to generate only modified proxies.
     * 
     * @parameter default-value="true"
     */
    private boolean useHash;

    /**
     * Number of concurrent proxies generator. Experimental.
     * 
     * @parameter default-value="1"
     */
    private int nbThread;

    /**
     * A comma separated list of mappings files.
     * 
     * @parameter
     */
    private String mappingsFile;

    /**
     * A comma separated list of templates files.
     * 
     * @parameter
     */
    private String templatesFile;

    /**
     * Sets to generate a file containing all the symbols generated during
     * processing.
     * 
     * @parameter
     */
    private String exportFile;

    /**
     * Sets a regex to filter the lists of symbols in the export file
     * 
     * @parameter
     */
    private String exportFilter;

    /**
     * A comma separated list of files containing symbols to use instead of
     * generating new ones.
     * 
     * @parameter
     */
    private String importsFile;

    /**
     * Sets a regex to filter the lists of symbols in the imports files
     * 
     * @parameter
     */
    private String importFilter;

    @Override
    public void execute() throws MojoExecutionException {

        try {
            Settings settings = new Settings();
            settings.setClean(clean);
            settings.setTargetPath(outputDirectory);
            settings.setJarFiles(jarFiles);
            settings.setUseHash(useHash);
            settings.setNbThread(nbThread);
            settings.setMappingsFile(mappingsFile);
            settings.setTemplatesFile(templatesFile);
            settings.setExportFile(exportFile);
            settings.setExportFilter(exportFilter);
            settings.setImportsFile(importsFile);
            settings.setImportFilter(importFilter);

            Context context = new Context(settings);

            Core java4cppCore = new Core();
            java4cppCore.execute(context);
        } catch (Exception e) {
            getLog().error("java4cpp error: ", e);
            throw new org.apache.maven.plugin.MojoExecutionException("java4cpp error: ", e);
        }
        getLog().info("java4cpp success");
    }
}
