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
     * Location of the C++ proxies files.
     * 
     * @parameter property="${project.build.directory}"
     * @required
     */
    private String outputDirectory;

    /**
     * Clean output directory.
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
     * @paramter default-value="true"
     */
    private boolean useHash;

    /**
     * Number of concurrent proxies generation.
     * 
     * @parameter default-value="2"
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