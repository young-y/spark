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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : YY
 * @date : 2025/11/25
 * @descprition :
 *
 */

@Component
public class FormatHelper {
	
	private static ObjectFormater formater;
	private static DateFormater dateFormater;

	public static String toJson(Object obj) {
		return formater.toJson(obj);
	}

	public static String toJson(Object obj, boolean trimEmptyJson) {
		return formater.toJson(obj,trimEmptyJson);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		return formater.fromJson(json, clazz);
	}

	public static <T> T fromJson(@Nonnull ObjectMapper objectMapper, String json, Class<T> clazz) {
		return  formater.fromJson(objectMapper,json,clazz);
	}

	public static <T> T fromJson(@Nonnull ObjectMapper objectMapper, URL jsonURL, Class<T> clazz) {
		return  formater.fromJson(objectMapper,jsonURL,clazz);
	}

	public static <T> List<T> fromJsonAsList(String json, Class<T> elementClazz) {
		return  formater.fromJsonAsList( json, elementClazz);
	}

	public static <T> List<T> fromJsonAsList(@Nonnull ObjectMapper objectMapper, String json, Class<T> elementClazz) {
		return formater.fromJsonAsList(objectMapper,json,elementClazz);
	}

	public static <T> List<T> fromJsonAsList(@Nonnull ObjectMapper objectMapper, URL jsonURL, Class<T> elementClazz){
		return formater.fromJsonAsList(objectMapper,jsonURL,elementClazz);
	}

	public static <T> Set<T> fromJsonAsSet(String json, Class<T> elementClazz) {
		return  formater.fromJsonAsSet( json, elementClazz);
	}

	public static <T> Set<T> fromJsonAsSet(@Nonnull ObjectMapper objectMapper, String json, Class<T> elementClazz) {
		return formater.fromJsonAsSet(objectMapper,json,elementClazz);
	}

	public static <T> Set<T> fromJsonAsSet(@Nonnull ObjectMapper objectMapper, URL jsonURL, Class<T> elementClazz){
		return formater.fromJsonAsSet(objectMapper,jsonURL,elementClazz);
	}

	public static <K, V> Map<K, V> fromJsonAsMap(String json, Class<K> keyClazz, Class<V> valueClazz){
		return formater.fromJsonAsMap(json,keyClazz,valueClazz);
	}

	public static <K, V> Map<K, V> fromJsonAsMap(@Nonnull ObjectMapper objectMapper, String json, Class<K> keyClazz, Class<V> valueClazz){
		return formater.fromJsonAsMap(objectMapper,json,keyClazz,valueClazz);
	}

	public static <K, V> Map<K, V> fromJsonAsMap(@Nonnull ObjectMapper objectMapper, URL jsonURL, Class<K> keyClazz, Class<V> valueClazz){
		return formater.fromJsonAsMap(objectMapper,jsonURL,keyClazz,valueClazz);
	}

	public static <T> T fromAsJavaType( String json, JavaType type) {
		return formater.fromAsJavaType(json,type);
	}
	public static <T> T fromAsJavaType(@Nonnull ObjectMapper mapper, String json, JavaType type) {
		return formater.fromAsJavaType(mapper,json,type);
	}

	public static <T> T fromAsJavaType(URL jsonUrl, JavaType type) {
		return formater.fromAsJavaType(jsonUrl,type);
	}
	public static <T> T fromAsJavaType(@Nonnull ObjectMapper mapper, URL jsonUrl, JavaType type) {
		return formater.fromAsJavaType(mapper,jsonUrl,type);
	}

	public static Date parse(@Nonnull String dateStr){
		return dateFormater.parse(dateStr);
	}

	public static Date parse(@Nonnull String dateStr,String patten){
		return dateFormater.parse(dateStr,patten);
	}

	public static Date parse(@Nonnull String dateStr, DateFormat format){
		return dateFormater.parse(dateStr, format);
	}

	public static LocalDateTime parseLocal(@Nonnull String str){
		return dateFormater.parseLocal(str);
	}

	public static LocalDateTime parseLocal(@Nonnull String str,String patten){
		return dateFormater.parseLocal(str,patten);
	}

	public static LocalDateTime parseLocal(@Nonnull String str, DateTimeFormatter formatter){
		return dateFormater.parseLocal(str,formatter);
	}

	public static LocalDate parseLocalDate(@Nonnull String str){
		return dateFormater.parseLocalDate(str);
	}

	public static LocalDate parseLocalDate(@Nonnull String str,String patten){
		return dateFormater.parseLocalDate(str,patten);
	}

	public static LocalDate parseLocalDate(@Nonnull String str, DateTimeFormatter formatter){
		return dateFormater.parseLocalDate(str,formatter);
	}

	public static LocalTime parseLocalTime(@Nonnull String str){
		return dateFormater.parseLocalTime(str);
	}

	public static LocalTime parseLocalTime(@Nonnull String str,String patten){
		return dateFormater.parseLocalTime(str,patten);
	}

	public static LocalTime parseLocalTime(@Nonnull String str, DateTimeFormatter formatter){
		return dateFormater.parseLocalTime(str,formatter);
	}

	public static DateTimeFormatter getFormatter(String patten){
		return dateFormater.getFormatter(patten);
	}

	public static DateFormat getFormat(String patten){
		return dateFormater.getFormat(patten);
	}



	@Autowired
	public  void setFormater(ObjectFormater formater) {
		FormatHelper.formater = formater;
	}

	@Autowired
	public void setDateFormater(DateFormater dateFormater) {
		FormatHelper.dateFormater = dateFormater;
	}
}
