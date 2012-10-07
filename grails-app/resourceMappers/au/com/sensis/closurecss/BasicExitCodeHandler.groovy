package au.com.sensis.closurecss;

import com.google.common.css.ExitCodeHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BasicExitCodeHandler implements ExitCodeHandler {
    private static final Log log = LogFactory.getLog(BasicExitCodeHandler.class);

    public void processExitCode(int exitCode) {
        log.debug("Exit code returned: %d"+exitCode);
    }
}
