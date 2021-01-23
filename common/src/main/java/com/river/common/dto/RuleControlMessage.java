package com.river.common.dto;

import com.river.common.dto.enums.RuleOperation;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/11/30 14:34
 * @since 1.0.0, 2020/11/30 14:34
 **/
@Data
@Accessors(chain = true)
public class RuleControlMessage implements Serializable {
    private static final long serialVersionUID = -4584017414416756178L;

    /**
     * The rule content
     */
    private RuleDTO rule;

    /**
     * The UPDATE/DELETE/ADD
     */
    private RuleOperation oper_type;


    private long createTime;

}
