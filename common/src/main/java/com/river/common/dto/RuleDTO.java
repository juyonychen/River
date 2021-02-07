package com.river.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * River: RuleDTO
 *
 * @version 1.0.0, 2020-11-30 14:09
 * @since 1.0.0, 2020-11-30 14:09
 */
@Data
public class RuleDTO implements Serializable {

    private static final long serialVersionUID = -6091739549041101043L;

    private String rule_id;

    private String rule_name;

    private List<String> parserSqls;

    private List<StorageInfo> storageInfoList;



}
