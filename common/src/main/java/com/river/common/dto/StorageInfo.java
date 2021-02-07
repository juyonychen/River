package com.river.common.dto;

import com.river.common.dto.enums.StorageType;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/2 19:55
 * @since 1.0.0, 2021/2/2 19:55
 **/
@Data
public class StorageInfo  implements Serializable {

    private StorageType storageTypes;
    /**
     *  Map<key, Value>
     */
    private Map<String, String> storageInfo;
}
