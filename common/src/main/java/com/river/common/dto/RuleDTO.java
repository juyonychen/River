package com.river.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * River: RuleDTO
 *
 * @version 1.0.0, 2020-11-30 14:09
 * @since 1.0.0, 2020-11-30 14:09
 */
@Data
@Accessors(chain = true)
public class RuleDTO implements Serializable {

    private static final long serialVersionUID = -6091739549041101043L;

    private String rule_id;
    private String rule_name;
    private List<String> parserSqls;
    /**
     * Map<SinkType, Map<key, Value>>
     */
    private Map<String, Map<String, String>> sinkConfigs;


}
