package au.com.sensis.closurecss
import com.google.common.collect.Sets
import com.google.common.css.compiler.ast.ErrorManager
import com.google.common.css.compiler.ast.GssError
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

public class LoggingErrorManager implements ErrorManager {
    private static final Log log = LogFactory.getLog(LoggingErrorManager.class);
    private final SortedSet<GssError> errors = Sets.newTreeSet();
    private final SortedSet<GssError> warnings = Sets.newTreeSet();

    public void report(GssError error) {
        errors.add(error);
    }

    public void reportWarning(GssError warning) {
        warnings.add(warning);
    }

    public void generateReport() {
        for(GssError error : errors) {
            log.error(error.format());
        }
        for(GssError warning : warnings) {
            log.error(warning.format());
        }
        log.info(String.format("Closure stylesheets - %d error(s), %d warning(s)", errors.size(), warnings.size()));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
