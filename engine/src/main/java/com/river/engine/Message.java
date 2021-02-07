package com.river.engine;

/**
 * @author Juyony Chen
 * @version 1.0.0, 2021/2/2 19:58
 * @since 1.0.0, 2021/2/2 19:58
 **/
public class Message {

    public static String generator(){
        return "{\"rule\":{\"rule_id\":123123123,\"rule_name\":\"XXXX告警规则\",\"tenant_id\":\"ARES\",\"alert_sql\":{\"WARNING\":\"WARNING\",\"CRITICAL\":\"CRITICAL\"},\"target_sql\":\"123#234#1|2|3#\",\"tags\":{\"WARNING\":1000,\"CRITICAL\":2000},\"update_time\":1606181021123,\"calculate_interval\":60,\"alert_system\":\"APOLLO\",\"alert_target_type\":\"alert_target_type\",\"rule_type\":1,\"metricCodes\":[{\"metricCode\":\"XXXCode1\",\"continueMinutes\":1},{\"metricCode\":\"XXXCode2\",\"continueMinutes\":1}]},\"oper_type\":\"RULE_CREATE\",\"createTime\":\"1606723237746\"}";
    }
}
