package com.river.engine.formats.dataformat;

import com.river.common.dto.MetaDataInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/5 11:48
 * @since 1.0.0, 2021/2/5 11:48
 **/
public class JsonFormat extends AbstractFormat implements Format<String> {


    @Override
    public String output(Map<String, String> message, List<MetaDataInfo> metaDatas) {
        return super.jsonOutput(message, metaDatas);
    }
}
