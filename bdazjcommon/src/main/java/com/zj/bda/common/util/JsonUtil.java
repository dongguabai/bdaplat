package com.zj.bda.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Dongguabai on 2018-06-10.
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Class STRING_CLASS = String.class;

    /**
     * 将 Java 对象转为 JSON 字符串
     */
    public static <T> String toJSON(T obj) {
        if (obj == null) {
            return null;
        }
        String jsonStr;
        try {
            jsonStr = obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Java 转 JSON 出错！", e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * 将 JSON 字符串转为 Java 对象
     */
    public static <T> T fromJSON(String json, Class<T> type) {
        if (CusObjectUtil.isContainsNull(json, type)) {
            return null;
        }
        T obj;
        try {
            obj = type.equals(STRING_CLASS) ? (T) json : objectMapper.readValue(json, type);
        } catch (Exception e) {
            log.error("JSON 转 Java 出错！", e);
            throw new RuntimeException(e);
        }
        return obj;
    }

}
