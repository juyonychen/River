package com.river.engine.context;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/19 18:50
 * @since 1.0.0, 2021/1/19 18:50
 **/
@Slf4j
public class SplitMetricContext extends DataContext<String> {

    private String[] splitList ;

    public SplitMetricContext(String splitMessage, String splitChar){
        splitList = splitMessage.split(splitChar);
    }

    public SplitMetricContext(String[] splitList){
        this.splitList = splitList;
    }

    @Override
    public String getStringValue(String parameter){
        int parameterInt = Integer.parseInt(parameter);
        if(parameterInt >=splitList.length){
            return "";
        }else{
            return splitList[parameterInt];
        }
    }

}
