/*
 *
 *
 *
 *
 *
 */
package com.river.engine.context;


public abstract class DataContext<T> {

    /**
     * @param parameter
     * @return
     */
    public abstract String getStringValue(T parameter);

}
