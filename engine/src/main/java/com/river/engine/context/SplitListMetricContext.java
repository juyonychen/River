package com.river.engine.context;

import com.river.common.constant.SysConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/19 18:50
 * @since 1.0.0, 2021/1/19 18:50
 **/
@Slf4j
public class SplitListMetricContext extends DataContext<String> {

    private String[] splitList;

    public SplitListMetricContext(String splitMessage, String splitChar){
        splitList = splitMessage.split(splitChar);
    }

    public SplitListMetricContext(String[] splitList){
        this.splitList = splitList;
    }

    @Override
    public String getStringValue(String parameter){
        StringBuffer cache = new StringBuffer();

        if(splitList.length < 1){
            return "";
        }else{
            Arrays.asList(splitList).forEach(str -> {
                arrayIdToString(cache, str);
            });
        }
        return cache.substring(0, cache.length()-1);
    }


    private StringBuffer arrayIdToString(StringBuffer sBuffer, String message) {
        return sBuffer.append(message).append(SysConstants.LIST_CHAR);
    }
}
