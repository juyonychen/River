package com.river.common.dto;

import com.river.common.dto.enums.DataType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/2 17:12
 * @since 1.0.0, 2021/2/2 17:12
 **/
@Data
@AllArgsConstructor
public class MetaDataInfo {

    private String columnName;

    private DataType dataType;
}
