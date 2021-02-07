package com.river.engine.formats.dataformat;

import com.river.common.dto.MetaDataInfo;
import com.river.common.utils.JSONUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/5 10:48
 * @since 1.0.0, 2021/2/5 10:48
 **/
public abstract class AbstractFormat implements Format<String> {

    /**
     * out put for Json format;
     * @param message
     * @param metaDatas
     * @return
     */
    public String jsonOutput(Map<String, String> message, List<MetaDataInfo> metaDatas) {
        Map<String, Object> out = new HashMap<>();
        metaDatas.stream().forEach(metaDataInfo -> {
            String columnName = metaDataInfo.getColumnName();
            switch (metaDataInfo.getDataType()){
                case INT:
                    out.put(columnName, Integer.parseInt(message.get(columnName)));
                    break;
                case LONG:
                    out.put(columnName, Long.parseLong(message.get(columnName)));
                    break;
                case STRING:
                    out.put(columnName, message.get(columnName));
                    break;
                default:
                    out.put(columnName, message.get(columnName));
            }

        });
        return JSONUtils.INSTANCE.toJson(out);
    }


    /**
     * out put for split format;
     * @param message
     * @param metaDatas
     * @return
     */
    public String splitOutput(Map<String, String> message, List<MetaDataInfo> metaDatas, String columnFix, String splitChar) {
        StringBuilder sBuiler = new StringBuilder();
        metaDatas.stream().forEach(metaDataInfo -> {
            String columnName = metaDataInfo.getColumnName();
            switch (metaDataInfo.getDataType()){
                case INT:
                    sBuiler.append(Integer.parseInt(message.get(columnName)));
                    break;
                case LONG:
                    sBuiler.append(Long.parseLong(message.get(columnName)));
                    break;
                case STRING:
                    sBuiler.append(columnFix).append(message.get(columnName)).append(columnFix).append(splitChar);
                    break;
                default:
                    sBuiler.append(columnFix).append(message.get(columnName)).append(columnFix).append(splitChar);
            }

        });

        return JSONUtils.INSTANCE.toJson(sBuiler.substring(0, sBuiler.length()-1));
    }

}
