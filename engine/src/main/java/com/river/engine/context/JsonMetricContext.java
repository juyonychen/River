package com.river.engine.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/19 18:50
 * @since 1.0.0, 2021/1/19 18:50
 **/
@Slf4j
public class JsonMetricContext extends DataContext<String> {

    private Map<String, String>metricInfo;

    public JsonMetricContext(String jsonMessage){
        this.metricInfo = (Map) JSON.parse(jsonMessage);
    }

    public JsonMetricContext(Map<String, String> jsonMessage){
        this.metricInfo = jsonMessage;
    }


    @Override
    public String getStringValue(String parameter){
        Object value = metricInfo.get(parameter);
        if(value instanceof JSONObject){
            return value.toString();
        }
        return value.toString();

    }


}
