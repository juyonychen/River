package com.river.engine.context;

import com.river.common.exception.EnumNotFoundException;
import com.river.engine.runtime.partial.PartFromInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/2 15:10
 * @since 1.0.0, 2021/2/2 15:10
 **/
@Slf4j
public class DataContextFactory {


    public static DataContext generatorDataContex(PartFromInfo fromInfo,
                                                  String message,
                                                  String splitInfo,
                                                  Map<String, String> context){

        DataContext dataContext ;
        String calculateMessage;
        if(null == fromInfo.getParameter() || "".equals(fromInfo.getParameter())){
            calculateMessage = message;
        }else{
            calculateMessage = context.get(fromInfo.getParameter());
        }

        switch (fromInfo.getFromType()) {
            case JSON:
                dataContext = new JsonMetricContext(calculateMessage);
                break;
            case JSONLIST:
                dataContext = new JsonListMetricContext(calculateMessage);
                break;
            case SPLIT:
                dataContext = new SplitMetricContext(calculateMessage, splitInfo);
                break;
            case SPLITLIST:
                dataContext = new SplitListMetricContext(calculateMessage, splitInfo);
                break;
            default:
                throw new EnumNotFoundException("Unsupport Enum :{}", fromInfo.getFromType());
        }

        return dataContext;
    }

}
