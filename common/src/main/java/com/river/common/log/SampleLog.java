
package com.river.common.log;

import org.slf4j.Logger;

import java.io.Serializable;

/**
 * A {@link Logger} wrapper for print a sample action.
 * The real process is flooded, we couldn't log every thing.
 *
 *
 * @version 1.0.0, 2018-01-24 12:02
 * @since 1.0.0, 2018-01-23 17:20
 */
public interface SampleLog extends Logger, Serializable {

    /**
     * Refresh the log stator
     */
    void refresh();
}
