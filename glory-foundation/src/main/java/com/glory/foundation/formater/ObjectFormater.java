/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation.formater;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author : YY
 * @date : 2025/11/25
 * @descprition :
 *
 */
@FunctionalInterface
public interface ObjectFormater {

	ObjectMapper provide();

	default String toJson(Object obj) {
		return toJson(obj, true);
	}

	default String toJson(Object obj, boolean trimEmptyJson) {
		String json = null;
		if (Objects.nonNull(obj)) {
			try {
				ObjectMapper mapper = provide();
				json = mapper.writeValueAsString(obj);
				if (trimEmptyJson && ("{}".equals(json) || "[]".equals(json))) {
					return null;
				}
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
		return json;
	}

	default <T> T fromJson(String json, Class<T> clazz) {
		return fromJson(provide(), json, clazz);
	}

	default <T> T fromJson(@Nonnull ObjectMapper objectMapper, String json, Class<T> clazz) {
		JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
		return fromAsJavaType(objectMapper,json,javaType);
	}

	default <T> T fromJson(@Nonnull ObjectMapper objectMapper, URL jsonURL, Class<T> clazz) {
		JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
		return fromAsJavaType(objectMapper,jsonURL,javaType);
	}

	default <T> List<T> fromJsonAsList(String json, Class<T> elementClazz) {
		return fromJsonAsList(provide(), json, elementClazz);
	}

	default <T> List<T> fromJsonAsList(@Nonnull ObjectMapper objectMapper, String json, Class<T> elementClazz) {
		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, elementClazz);
		return fromAsJavaType(objectMapper,json,type);
	}

	default <T> List<T> fromJsonAsList(@Nonnull ObjectMapper objectMapper, URL jsonURL, Class<T> elementClazz){
		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, elementClazz);
		return fromAsJavaType(objectMapper,jsonURL,type);
	}

	default <T> Set<T> fromJsonAsSet(String json, Class<T> elementClazz) {
		return fromJsonAsSet(provide(), json, elementClazz);
	}

	default <T> Set<T> fromJsonAsSet(@Nonnull ObjectMapper objectMapper, String json, Class<T> elementClazz) {
		JavaType type = objectMapper.getTypeFactory().constructCollectionType(Set.class, elementClazz);
		return fromAsJavaType(objectMapper,json,type);
	}

	default <T> Set<T> fromJsonAsSet(@Nonnull ObjectMapper objectMapper, URL jsonURL, Class<T> elementClazz){
		JavaType type = objectMapper.getTypeFactory().constructCollectionType(Set.class, elementClazz);
		return fromAsJavaType(objectMapper,jsonURL,type);
	}

	default <K, V> Map<K, V> fromJsonAsMap(String json, Class<K> keyClazz, Class<V> valueClazz){
		return fromJsonAsMap(provide(),json,keyClazz,valueClazz);
	}

	default <K, V> Map<K, V> fromJsonAsMap(@Nonnull ObjectMapper objectMapper, String json, Class<K> keyClazz, Class<V> valueClazz){
		JavaType mapType = objectMapper.getTypeFactory().constructMapType(Map.class, keyClazz, valueClazz);
		return fromAsJavaType(objectMapper,json,mapType);
	}

	default <K, V> Map<K, V> fromJsonAsMap(@Nonnull ObjectMapper objectMapper, URL jsonURL, Class<K> keyClazz, Class<V> valueClazz){
		JavaType mapType = objectMapper.getTypeFactory().constructMapType(Map.class, keyClazz, valueClazz);
		return fromAsJavaType(objectMapper,jsonURL,mapType);
	}

	default <T> T fromAsJavaType( String json, JavaType type) {
		return fromAsJavaType(provide(),json,type);
	}

	default <T> T fromAsJavaType(@Nonnull ObjectMapper mapper, String json, JavaType type) {
		if (StringUtils.hasLength(json)){
			try {
				return mapper.readValue(json, type);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
		return null;
	}

	default <T> T fromAsJavaType(URL jsonUrl, JavaType type) {
		return fromAsJavaType(provide(),jsonUrl,type);
	}

	default <T> T fromAsJavaType(@Nonnull ObjectMapper mapper, URL jsonUrl, JavaType type) {
		if (null != jsonUrl){
			try {
				return mapper.readValue(jsonUrl, type);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
		return null;
	}
}
