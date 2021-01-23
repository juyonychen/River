
package com.river.common.config;

import lombok.Data;

import java.io.Serializable;

/**
 * The http loading source for management
 */
@Data
public class HttpApiConfig implements Serializable {
    private static final long serialVersionUID = 2610933320891024832L;

    private String url;

    private String ruleType;
}
