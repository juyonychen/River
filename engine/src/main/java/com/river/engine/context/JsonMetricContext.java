package com.river.engine.context;

import com.alibaba.fastjson.JSON;
import com.river.engine.formats.types.values.LongValue;
import com.river.engine.formats.types.values.NumberValue;
import com.river.engine.formats.types.values.StringValue;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/19 18:50
 * @since 1.0.0, 2021/1/19 18:50
 **/
@Slf4j
public class JsonMetricContext extends DataContext<String> {

    private Map<String, Object>metricInfo;

    public JsonMetricContext(String jsonMessage){
        this.metricInfo = (Map) JSON.parse(jsonMessage);
    }

    public JsonMetricContext(Map<String, Object> jsonMessage){
        this.metricInfo = jsonMessage;
    }


    public StringValue getStringValue(String parameter){
        return new StringValue(metricInfo.get(parameter).toString());
    }

    public LongValue getLongValue(String parameter){
        try{
            return new LongValue(Integer.parseInt(metricInfo.get(parameter).toString()));
        }catch (NumberFormatException e){
            log.debug("Parser String to Long error:", e);
            return new LongValue(0);
        }
    }

    public  NumberValue getNumberValue(String parameter){
        return new NumberValue(0.0);
    }


}
