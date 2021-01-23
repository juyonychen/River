package com.river.common.constant;

import lombok.experimental.UtilityClass;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/11/30 10:06
 * @since 1.0.0, 2020/11/30 10:06
 **/
@UtilityClass
public class SysConstants {

    /**
     *
     */
    public static final String EVENT_TIME = "eventTime";

    public static final String PROCESSTIME = "processTime";

    // 1:alert 0:non-alert default:1
    public static final String IS_ALERT = "isAlert";

    public static final String ALERT_VALUE = "1";

    public static final String APP_ID = "appId";

    public static final String DEFAULT_SYS = "NAN_SYS";

    public static final String TRIGGER = "TRIGGER";

    public static final String RULE_ID = "ruleId";

    public static final String ALERT_SEVERITY = "alert_severity";

    public static final String WAF = "waf";
    public static final String CDN = "cdn";
    public static final String NGINX = "nginx";
    public static final String RSF = "rsf";
    public static final String PLACE_HOLDER = "${address_type}";
    public static final String SPLIT_CHAR = "|";

}
