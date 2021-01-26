package com.river.engine.context;

import com.river.engine.formats.types.values.LongValue;
import com.river.engine.formats.types.values.NumberValue;
import com.river.engine.formats.types.values.StringValue;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/19 18:50
 * @since 1.0.0, 2021/1/19 18:50
 **/
@Slf4j
public class SplitMetricContext extends DataContext<Integer> {

    private String[] splitList ;

    public SplitMetricContext(String splitMessage, String splitChar){
        splitList = splitMessage.split(splitChar);
    }

    public SplitMetricContext(String[] splitList){
        this.splitList = splitList;
    }

    public StringValue getStringValue(Integer parameter){
        return new StringValue(splitList[parameter]);
    }

    public LongValue getLongValue(Integer parameter){
        try{
            return new LongValue(Long.parseLong(splitList[parameter]));
        }catch (NumberFormatException e){
            log.debug("Parser String to Long error:", e);
            return new LongValue(0);
        }
    }

    public NumberValue getNumberValue(Integer parameter){
        return new NumberValue(0.0);
    }
}
