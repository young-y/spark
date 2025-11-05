/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
/**
 * @author : YY
 * @date : 2025/11/5
 * @descprition :
 *
 */
public final class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper mapper;

    private static ObjectMapper mapperIgnoreUndefined;

    private static ObjectMapper mapperWithUpperCamelCaseIgnoreUndefined;

    private static ObjectMapper mapperWithLowerCamelCaseIgnoreUndefined;

    private static ObjectMapper writerMapper;

    private static ObjectMapper writeMapperPrintNull;

    @Autowired
    private void setObjectMapper(ObjectMapper objectMapper) {
        mapper = objectMapper;
    }

    public static ObjectMapper getMapper() {
        if (null == mapper){
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    public static ObjectMapper getWriterMapper() {
        if (writerMapper == null) {
            synchronized (JsonUtils.class) { initWriterMapper();}
        }

        return writerMapper;
    }

    public static ObjectMapper getWriteMapperPrintNull() {
        if (writeMapperPrintNull == null) {
            synchronized (JsonUtils.class) { initWriteMapperPrintNull();}
        }
        return writeMapperPrintNull;
    }

    public static ObjectMapper getMapperIgnoreUndefined() {
        if (mapperIgnoreUndefined == null) {
            synchronized (JsonUtils.class) { initMapperIgnoreUndefined();}
        }
        return mapperIgnoreUndefined;
    }

    public static ObjectMapper getMapperWithUpperCamelCaseIgnoreUndefined() {
        if (mapperWithUpperCamelCaseIgnoreUndefined == null) {
            synchronized (JsonUtils.class) { initMapperWithUpperCamelCaseIgnoreUndefined();}
        }

        return mapperWithUpperCamelCaseIgnoreUndefined;
    }

    private static void initMapperWithUpperCamelCaseIgnoreUndefined() {
        mapperWithUpperCamelCaseIgnoreUndefined = mapper.copy();
        mapperWithUpperCamelCaseIgnoreUndefined.getDeserializationConfig().withFeatures(JsonParser.Feature.IGNORE_UNDEFINED);
        mapperWithUpperCamelCaseIgnoreUndefined.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperWithUpperCamelCaseIgnoreUndefined.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
    }

    public static ObjectMapper getMapperWithLowerCamelCaseIgnoreUndefined() {
        if (mapperWithLowerCamelCaseIgnoreUndefined == null) {
            synchronized (JsonUtils.class) { initMapperWithLowerCamelCaseIgnoreUndefined();}
        }

        return mapperWithLowerCamelCaseIgnoreUndefined;
    }

    private static void initMapperWithLowerCamelCaseIgnoreUndefined() {
        mapperWithLowerCamelCaseIgnoreUndefined = mapper.copy();
        mapperWithLowerCamelCaseIgnoreUndefined.getDeserializationConfig().withFeatures(JsonParser.Feature.IGNORE_UNDEFINED);
        mapperWithLowerCamelCaseIgnoreUndefined.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperWithLowerCamelCaseIgnoreUndefined.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        mapperWithLowerCamelCaseIgnoreUndefined.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }

    private static void initMapperIgnoreUndefined() {
        mapperIgnoreUndefined = mapper.copy();
        mapperIgnoreUndefined.getDeserializationConfig().withFeatures(JsonParser.Feature.IGNORE_UNDEFINED);
        mapperIgnoreUndefined.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static void initWriteMapperPrintNull() {
        writeMapperPrintNull = getWriterMapper().copy();
        writeMapperPrintNull.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }

    private static void initWriterMapper() {
        writerMapper = getMapper().copy();
        writerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        writerMapper.registerModule(simpleModule);

    }

    public static String toJson(Object object) {
        return toJson(object, true, false);
    }

    public static String toJson(Object object, boolean trimEmptyJson, boolean withNullValue) {
        try {
            if (object == null) {
                return null;
            }
            ObjectMapper mapper = getWriterMapper();

            if (withNullValue) {
                mapper = getWriteMapperPrintNull();
            }

            String jsonString;
            jsonString = mapper.writeValueAsString(object);
            if (trimEmptyJson && ("[]".equals(jsonString) || "{}".equals(jsonString))) {
                jsonString = null;
            }
            return jsonString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return fromJson(getMapper(), json, clazz);
    }

    public static <T> T fromJson(ObjectMapper objectMapper, String json, Class<T> clazz) {
        try {
            if (json == null) {
                return null;
            }

            T object = objectMapper.readValue(json, clazz);

            return object;
        } catch (Exception e) {
            throw new RuntimeException(json,e);
        }
    }

    public static <T> T fromJson(ObjectMapper objectMapper, URL jsonURL, Class<T> clazz) {
        try {
            if (jsonURL == null) {
                return null;
            }

            T object = objectMapper.readValue(jsonURL, clazz);

            return object;
        } catch (Exception e) {
            throw new RuntimeException(jsonURL.toString(),e);
        }
    }

    public static <T> List<T> fromJsonAsList(String json, Class<T> elementClazz) {
        return fromJsonAsList(getMapper(), json, elementClazz);
    }

    public static <T> List<T> fromJsonAsList(ObjectMapper objectMapper, String json, Class<T> elementClazz) {
        return (List<T>) fromJsonAsCollection(objectMapper, json, List.class, elementClazz);
    }

    public static <T> List<T> fromJsonAsList(ObjectMapper objectMapper, URL jsonURL, Class<T> elementClazz) {
        if (jsonURL == null) {
            return null;
        }
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, elementClazz);

            return objectMapper.readValue(jsonURL, type);
        } catch (Exception e) {
            throw new RuntimeException(jsonURL.toString(),e);
        }
    }

    public static <T> Collection<T> fromJsonAsCollection(String json, Class<? extends Collection> collectionClazz, Class<T> elementClazz) {
        return fromJsonAsCollection(getMapper(), json, collectionClazz, elementClazz);
    }

    public static <T> Collection<T> fromJsonAsCollection(ObjectMapper objectMapper, String json, Class<? extends Collection> collectionClazz, Class<T> elementClazz) {
        if (json == null) {
            return null;
        }
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(collectionClazz, elementClazz);

            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(json);
        }
    }

    public static <K, V> Map<K, V> fromJsonAsMap(String json, Class<K> keyClazz, Class<V> valueClazz) {
        return fromJsonAsMap(getMapper(), json, keyClazz, valueClazz);
    }

    public static <K, V> Map<K, V> fromJsonAsMap(ObjectMapper objectMapper, URL jsonURL, Class<K> keyClazz, Class<V> valueClazz) {
        if (jsonURL == null) {
            return null;
        }
        try {
            JavaType type = objectMapper.getTypeFactory().constructMapType(Map.class, keyClazz, valueClazz);

            return objectMapper.readValue(jsonURL, type);
        } catch (Exception e) {
            throw new RuntimeException(jsonURL.toString(),e);
        }
    }

    public static <K, V> Map<K, V> fromJsonAsMap(ObjectMapper objectMapper, String json, Class<K> keyClazz, Class<V> valueClazz) {
        return fromJsonAsMap(objectMapper, json, Map.class, keyClazz, valueClazz);
    }

    public static <K, V> Map<K, V> fromJsonAsMap(ObjectMapper objectMapper, String json, Class<? extends Map> mapClazz, Class<K> keyClazz,
                                                 Class<V> valueClazz) {
        if (json == null) {
            return null;
        }
        try {
            JavaType type = objectMapper.getTypeFactory().constructMapType(mapClazz, keyClazz, valueClazz);

            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(json,e);
        }
    }

    public static String firstUpperCase(final String string) {
        if (string == null) {
            return string;
        }
        final char[] ch = string.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
