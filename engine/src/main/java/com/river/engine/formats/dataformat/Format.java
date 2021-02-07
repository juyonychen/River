package com.river.engine.formats.dataformat;

import com.river.common.dto.MetaDataInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/5 10:40
 * @since 1.0.0, 2021/2/5 10:40
 **/
public interface Format<T> {



    /**
     *
     * @param message
     * @param metaDatas
     * @return
     */
    T output(Map<String, String> message, List<MetaDataInfo> metaDatas);

}
