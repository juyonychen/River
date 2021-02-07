package com.river.engine.runtime.processor;

import com.river.common.constant.SysConstants;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/5 9:12
 * @since 1.0.0, 2021/2/5 9:12
 **/
public class Test {
    public static void main(String[] args) {
        String v = "1"+SysConstants.LIST_CHAR+"2";
        System.out.println(v);

        String[] a = v.split(String.valueOf(SysConstants.LIST_CHAR));

        System.out.println(a[0]);
    }
}
