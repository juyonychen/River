/*
 * Copyright © 2013-2018 Suning.com Co., Ltd.
 *
 * This file is part of euphoria project.
 * It can not be copied and/or distributed without the express
 * permission of cloudytrace group.
 */
package com.river.engine.runtime.partial;


import lombok.Data;
import lombok.NonNull;

/**
 */
@Data
public class PartFromInfo extends AbstractPartInfo {
    private static final long serialVersionUID = 4646812324844939963L;

    @NonNull
    private String fromType;

    private String parameter;


    public PartFromInfo(String fromType, String parameter){
        this.fromType = fromType;
        this.parameter = parameter;
    }

    public PartFromInfo(String fromType){
        this.fromType = fromType;
    }

    @Override
    public String toString() {
        if(null == parameter){
            return  "FROM " + fromType ;
        }
        return "FROM " + fromType +"(\""+ parameter+"\")";
    }

}
