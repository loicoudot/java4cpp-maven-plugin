java4cpp-maven-plugin
=====================

Maven plugin for controlling java4cpp execution.

Gets full informations directly on the [java4cpp-core](https://github.com/loicoudot/java4cpp-core/wiki) page project.

## Goals ##

The plugin as one goal `generate` that process a list of jars and generate associated C++ proxies files in the specified directory.
By default that goal is mapped to the phase `process-classes`.

## Configuration ##
       
The list of parameters for the goal `generate`:       
                    
* `outputDirectory` Location of the generated C++ proxies files.
* `clean`           Clean output directory.
* `jarFiles`           A comma separated list of jar files to analyse.
* `useHash`            Sets to true to generate only modified proxies.
* `nbThread`           Number of concurrent proxies generator.
* `mappingsFile`       A comma separated list of mappings files.
* `templatesFile`      A comma separated list of templates files.
* `exportFile`		Sets to generate a file containing all the symbols generated during processing.
* `exportFilter`	Sets a regex to filter the lists of symbols in the export file.
* `importsFile`		A comma separated list of files containing symbols to use instead of generating new ones.
* `importsFilter`	Sets a regex to filter the lists of symbols in the imports files.

java4cpp looks for mappings and templates files inside jars resources and if failed in the file system.

## Sample pom file ##

This is a sample pom.xml file 
```xml
<plugin>
	<groupId>com.github.loicoudot.java4cpp</groupId>
	<artifactId>java4cpp-maven-plugin</artifactId>
	<version>1.0.3</version>
	<executions>
		<execution>
			<phase>install</phase>
			<goals>
				<goal>generate</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<jarFiles>ajartoanalyze.jar</jarFiles>
		<outputDirectory>cpp/java4cpp</outputDirectory>
		<clean>true</clean>
		<mappingsFile>base-mappings.xml</mappingsFile>
		<templatesFile>base-templates.xml;string-templates.xml</templatesFile>
	</configuration>
	<dependencies>
		<dependency>
			<groupId>com.github.loicoudot.java4cpp</groupId>
			<artifactId>java4cpp-templates</artifactId>
			<version>1.0.1</version>
		</dependency>
	</dependencies>
</plugin>
```
Like in the exemple above, you can add dependencies inside the java4cpp-maven-plugin for resolving the templates or mappings definition.

