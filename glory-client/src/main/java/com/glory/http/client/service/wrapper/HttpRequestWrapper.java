/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.wrapper;


import jakarta.annotation.Nonnull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */

public class HttpRequestWrapper<T, S> {

	private HttpMethod method = HttpMethod.POST;
	private HttpHeaders headers;
	private UrlWrapper urlWrapper;
	private AuthentificationWrapper authentification;
	private S request;  //HttpRequest object
	private Class<T> responseClass;// response type
	private T result;
	private boolean logging = Boolean.TRUE;//write log or not
	private HttpEntity<S> httpEntity;
	private RestTemplate restTemplate;
	private final Map<String, Object> properties = new LinkedHashMap<>(8);

	public HttpMethod getMethod() {
		return method;
	}

	public HttpRequestWrapper<T, S> setMethod(@Nonnull HttpMethod method) {
		this.method = method;
		return this;
	}

	public HttpHeaders getHeaders() {
		if (null == headers) {
			headers = new HttpHeaders();
		}
		return headers;
	}

	public HttpRequestWrapper<T, S> addHeader(String key, String value) {
		getHeaders().set(key, value);
		return this;
	}

	public HttpRequestWrapper<T, S> setHeaders(@Nonnull HttpHeaders headers) {
		getHeaders().addAll(headers);
		return this;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public HttpRequestWrapper<T,S> setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		return this;
	}

	public S getRequest() {
		return request;
	}

	public HttpRequestWrapper<T, S> setRequest(final S request) {
		this.request = request;
		return this;
	}

	public Class<T> getResponseClass() {
		return responseClass;
	}

	public HttpRequestWrapper<T, S> setResponseClass(final Class<T> responseClass) {
		this.responseClass = responseClass;
		return this;
	}

	public boolean isLogging() {
		return logging;
	}

	public HttpRequestWrapper<T, S> setLogging(final boolean logging) {
		this.logging = logging;
		return this;
	}

	public HttpEntity<S> getHttpEntity() {
		if (null == httpEntity) {
			if (null != request) {
				httpEntity = new HttpEntity<>(request, getHeaders());
			} else {
				httpEntity = new HttpEntity<>(getHeaders());
			}
		}
		return httpEntity;
	}

	public HttpRequestWrapper<T,S> setHttpEntity(final HttpEntity<S> httpEntity) {
		this.httpEntity = httpEntity;
		return this;
	}

	public UrlWrapper getUrlWrapper() {
		return urlWrapper=(null == urlWrapper?new UrlWrapper():urlWrapper);
	}

	public HttpRequestWrapper<T,S> setUrlWrapper(UrlWrapper urlWrapper) {
		this.urlWrapper = urlWrapper;
		return this;
	}

	public AuthentificationWrapper getAuthentification() {
		return authentification;
	}

	public HttpRequestWrapper<T,S> setAuthentification(AuthentificationWrapper authentification) {
		this.authentification = authentification;
		return this;
	}

	public T getResult() {
		return result;
	}

	public HttpRequestWrapper<T,S> setResult(T result) {
		this.result = result;
		return this;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public  HttpRequestWrapper<T, S> addProperty(@Nonnull String key,@Nonnull Object value){
		this.properties.put(key, value);
		return  this;
	}

	public void setProperties(@Nonnull Map<String, Object> properties) {
		this.properties.putAll(properties);
	}

	public static <T, S> HttpRequestWrapper<T, S> create(S request,Class<T> clz){
		return  new HttpRequestWrapper<T,S>().setRequest(request).setResponseClass(clz);
	}

	public static <T, S> HttpRequestWrapper<T, S> create(String url, S request, Class<T> responseClass) {
		return create(UrlWrapper.createByUrl(url), request, responseClass);
	}

	public static <T, S> HttpRequestWrapper<T, S> create(String host, String uri, S request, Class<T> responseClass) {
		return create(UrlWrapper.create(host, uri), request, responseClass);
	}

	public static <T, S> HttpRequestWrapper<T, S> create(UrlWrapper url, S request, Class<T> responseClass) {
		Assert.notNull(url, "url is null.");
		HttpRequestWrapper<T, S> wrapper = new HttpRequestWrapper<>();
		wrapper.urlWrapper = url;
		wrapper.request = request;
		wrapper.responseClass = responseClass;
		return wrapper;
	}
}
