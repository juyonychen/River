package com.river.engine.context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.river.common.constant.SysConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/19 18:50
 * @since 1.0.0, 2021/1/19 18:50
 **/
@Slf4j
public class JsonListMetricContext extends DataContext<String> {

    private JSONArray metricInfo;

    public JsonListMetricContext(String jsonMessage){

        this.metricInfo = JSONArray.parseArray(jsonMessage);
    }

    @Override
    public String getStringValue(String parameter){
        StringBuffer sBuffer = new StringBuffer();
        metricInfo.stream().forEach(jsonObject->{
            arrayIdToString((JSONObject) jsonObject, sBuffer, parameter);
        });
        return sBuffer.substring(0, sBuffer.length()-1);
    }

    private StringBuffer arrayIdToString(JSONObject jsonobejct, StringBuffer sBuffer, String parameter) {
        return sBuffer.append(null == jsonobejct.getString(parameter)? "": jsonobejct.getString(parameter))
                .append(SysConstants.LIST_CHAR);
    }


}
