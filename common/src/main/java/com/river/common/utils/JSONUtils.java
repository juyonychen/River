/*
 *
 *
 *
 *
 */
package com.river.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * This class is mainly used to hold a ObjectMapper instance.
 * And provide some unchecked exception function for simplify usage.
 * <p>
 * It's was mainly inspired by @calvin
 *
 *
 * @version 1.0.0, 2018-05-25 15:17
 * @since 1.0.0, 2018-05-25 15:17
 */
@Slf4j
public final class JSONUtils implements Serializable {
    private static final long serialVersionUID = 2579782969249155940L;

    private static final String PARSE_ERROR_STR = "parse json string error:";
    public static final JSONUtils INSTANCE = new JSONUtils();

    private ObjectMapper mapper;

    private JSONUtils() {
        this((JsonInclude.Include) null);
    }

    private JSONUtils(JsonInclude.Include include) {
        mapper = new ObjectMapper();

        if (include != null) {
            mapper.setSerializationInclusion(include);
        }
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public JSONUtils(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public static JSONUtils nonNullMapper() {
        return new JSONUtils(JsonInclude.Include.NON_NULL);
    }

    public static JSONUtils nonEmptyMapper() {
        return new JSONUtils(JsonInclude.Include.NON_EMPTY);
    }

    public static JSONUtils defaultMapper() {
        return new JSONUtils();
    }

    public String toJson(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            log.warn("write to json string error:" + object, e);
            return null;
        }
    }

    public <T> T fromJson(@Nullable String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.warn(PARSE_ERROR_STR + jsonString, e);
            return null;
        }
    }

    public <T> T fromJson(@Nullable String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            log.warn(PARSE_ERROR_STR + jsonString, e);
            return null;
        }
    }

    public <T> T fromJson(@Nullable String jsonString, TypeReference<T> reference) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, reference);
        } catch (IOException e) {
            log.warn(PARSE_ERROR_STR + jsonString, e);
            return null;
        }
    }

    public JavaType buildCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    public JavaType buildMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }

    public void update(String jsonString, Object object) {
        try {
            mapper.readerForUpdating(object).readValue(jsonString);
        } catch (IOException e) {
            log.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        }
    }

    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    public Map<String, String> toMap(String json) {
        return fromJson(json, new TypeReference<Map<String, String>>() {
        });
    }

    /**
     * remove the wrapped token '{}' of json string
     *
     * @param object object
     * @return json inner content without '{}'
     */
    public String toInnerJson(Object object) {
        String result = toJson(object);
        if (result != null && result.length() > 2) {
            result = result.substring(1, result.length() - 1);
        }
        return result;
    }

    public JSONUtils enableEnumUseToString() {
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        return this;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
