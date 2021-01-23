package com.river.engine.runtime.partial;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/1/19 18:42
 * @since 1.0.0, 2021/1/19 18:42
 **/
public class PartSplitInfo extends AbstractPartInfo {

    private String paramter;

    public PartSplitInfo(String paramter){
        this.paramter = paramter;
    }

    @Override
    public String toString() {
        return "Split by "  +"(\""+ paramter+"\")";
    }
}
