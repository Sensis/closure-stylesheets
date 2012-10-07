package au.com.sensis.closurecss

import com.google.common.base.Charsets
import com.google.common.css.JobDescription
import com.google.common.css.JobDescriptionBuilder
import com.google.common.css.SourceCode
import com.google.common.io.Files

class ClosureCssMinifyResourceMapper {
    static defaultExcludes = ['**/*.min.css']
    static defaultIncludes = ['**/*.css']

    def phase = MapperPhase.COMPRESSION

    def map(resource, config) {
        // check enabled
        if(config?.disable) {
            if (log.isDebugEnabled()) {
                log.debug "Closure CSS minification disabled"
            }

            return false
        }

        // minify input file
        JobDescription jobDescription = createJobDescription(resource.processedFile)
        StylesheetMinifier minifier = new StylesheetMinifier(jobDescription, new BasicExitCodeHandler(), new LoggingErrorManager());
        String minified = minifier.compile()

        // output minified file
        String minifiedFileName = resource.processedFile.name.replaceAll("css\$", "min.css")
        File minifiedFile = new File(resource.processedFile.parent, minifiedFileName)
        Files.write(minified, minifiedFile, Charsets.UTF_8);

        // update resources
        resource.processedFile = minifiedFile
        resource.updateActualUrlFromProcessedFile()
    }

    private JobDescription createJobDescription(File file) {
        JobDescriptionBuilder builder = new JobDescriptionBuilder()

        builder.setOutputFormat(JobDescription.OutputFormat.COMPRESSED)
        String fileContents;
        try {
            fileContents = Files.toString(file, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        builder.addInput(new SourceCode(file.canonicalPath, fileContents));

        builder.getJobDescription()
    }
}