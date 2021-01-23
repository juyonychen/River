package com.river.common.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2020/12/14 18:54
 * @since 1.0.0, 2020/12/14 18:54
 **/
@Data
public class CommonConfig implements Serializable {
    private static final long serialVersionUID = 2610933320891123832L;
    private int parallelism = 2;
}
