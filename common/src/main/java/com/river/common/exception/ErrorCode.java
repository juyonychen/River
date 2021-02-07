
package com.river.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Business exception grouping and description
 * <p>
 * <strong>Error Code Mapping:</strong>
 * <ul>
 * <li>1000xx Raw log parse exception</li>
 * <li>2000xx Alert engine exception</li>
 * <li>3000xx Alert configuration exception</li>
 * <li>4000xx Alert source exception</li>
 * <li>9000xx Common exception, not business exception</li>
 * </ul>
 *
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 1000xx exception
    PARSED_LOG_FIELD_NOT_EXISTED(100001, "日誌中不存在用於計算的域"),
    LOG_NOT_MATCH_FIELD_PATTERN(100002, "日誌不匹配定義的格式"),
    FAILURE_IN_FIELD_CAST(100003, "日誌域類型轉換失敗"),
    PARSE_FIELD_FAILED_TO_DEFAULT(100004, "日誌解析失敗，使用默認值"),
    LOG_PARSER_TYPE_NOT_EXISTED(100005, "日志解析配置中对应的 type 不存在"),
    LOG_CONFIG_VALIDATION_FAILURE(100006, "日志解析配置校验失败"),
    GENERATE_CUSTOM_TYPE_CLASS_ERROR(100007, "自定义类型无法使用"),
    LOG_STR_LENGTH_NOT_MATCH_THE_FORMAT(100010, "分割的日志数组长度与解析格式不匹配"),
    UNEXPECTED_FIELD_PARSE_EXCEPTION(100099, "日誌解析未知異常"),
    UNEXPECTED_ENUM_EXCEPTION(100011, "不支持的枚举类型"),

    // 2000xx exception
    SQL_FORMAT_NOT_VALID(200002, "SQL DSL 格式不正確"),
    FUNCTION_KEY_NOT_EXISTED_IN_CALCULATE(200003, "不存在該计算函数"),
    NUMERIC_NOT_VALID(200004, "科学计算异常"),
    NULL_VALUE_CALCULATION(200006, "空值用于数学运算"),
    COMBINATOR_CALCULATE_ERROR(200013, "组合子计算拷贝实例不允许继续计算"),
    NO_SUPPORTED_VALUE_TYPE(200014, "该类型无法转换为 Value 类型用于 Engine 计算"),

    // 3000xx exception
    CONFIG_PROFILE_NOT_EXISTED(300001, "配置 Profile 不存在"),
    CONFIG_FILE_LOADING_ERROR(300002, "配置文件不存在"),
    CONFIG_CLASS_CONVERT(300003, "配置文件與配置類不匹配"),
    CONFIG_CLASS_NOT_VALID(300004, "配置類實例不合法"),

    // 4000xx exception
    UN_SUPPORTED_SOURCE_CATEGORY(400001, "该流类型不支持"),

    // 5000xx exception for SDK
    NET_REQUEST_ERROR(500001, "网络请求服务器响应失败"),
    NET_REQUEST_SUSPEND(500002, "网络请求超时或者请求中断"),
    SDK_INITIALIZATION_ERROR(500003, "SDK 初始化失敗"),

    // 6000xx exception for Management
    NOT_SUCH_MESSAGE_TYPE(600001, "該訊息型別不支援"),
    RULE_NOT_EXISTED(600002, "該规则不存在"),
    NOTIFY_NOT_EXISTED(600003, "該通知配置不存在"),
    EVENT_NOT_EXISTED(600004, "該告警事件不存在"),
    TEMPLATE_RENDER_ERROR(600005, "通知模版渲染异常"),
    WEB_HOOK_ERROR(600006, "WebHook方式通知异常"),

    // 9000xx exception
    JACKSON_PARSE_EXCEPTION(900001, "Jackson 读取结息JSON文本异常"),
    SNOWFLAKE_ID_GENERATION(900002, "Snowflake UUID 生成失败"),
    UNEXPECTED_EXCEPTION(999999, "未知系統異常");

    private int code;

    private String desc;
}
