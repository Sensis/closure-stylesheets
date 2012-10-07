package au.com.sensis.closurecss;

import com.google.common.css.ExitCodeHandler;
import com.google.common.css.JobDescription;
import com.google.common.css.compiler.ast.ErrorManager;
import com.google.common.css.compiler.commandline.DefaultCommandLineCompiler;

public class StylesheetMinifier extends DefaultCommandLineCompiler {

    public StylesheetMinifier(JobDescription job, ExitCodeHandler exitCodeHandler, ErrorManager errorManager) {
        super(job, exitCodeHandler, errorManager);
    }
}
