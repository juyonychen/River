package com.river.engine.formats.dataformat;

import com.river.common.dto.MetaDataInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/5 11:54
 * @since 1.0.0, 2021/2/5 11:54
 **/
public class CKFormat extends AbstractFormat implements Format<String> {

    private static final String CK_FIX = "'";
    private static final String CK_INTERVAL = ",";

    @Override
    public String output(Map<String, String> message, List<MetaDataInfo> metaDatas) {
        return super.splitOutput(message, metaDatas, CK_FIX, CK_INTERVAL);
    }
}
