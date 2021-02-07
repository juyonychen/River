package com.river.engine.formats.dataformat;


import com.river.common.dto.MetaDataInfo;
import com.river.common.dto.enums.FormatType;

import java.util.List;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/5 10:47
 * @since 1.0.0, 2021/2/5 10:47
 **/
public class KafkaFormat extends AbstractFormat implements Format<String>{


    private String splitChar;
    private FormatType formatType;

    public KafkaFormat(String splitChar){
        this.splitChar = splitChar;
        this.formatType = FormatType.JSON;
    }

    public KafkaFormat(String splitChar, FormatType formatType){
        this.splitChar = splitChar;
        this.formatType = formatType;
    }

    @Override
    public String output(Map<String, String> message, List<MetaDataInfo> metaDatas){

        switch (formatType){
            case JSON:
                return super.jsonOutput(message, metaDatas);
            case SPLIT:
                return super.splitOutput(message, metaDatas, "", splitChar);

             default: return super.jsonOutput(message, metaDatas);
        }
    }
}
