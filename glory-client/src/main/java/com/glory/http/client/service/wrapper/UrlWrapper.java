/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.wrapper;


import com.glory.http.client.utils.URLUtils;
import jakarta.annotation.Nonnull;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */

public class UrlWrapper {
	private String apiName;
	private String targetAppName;
	private String contextPath;
	private String uri;
	private String uriKey;
	private String host;
	private String hostKey;
	private String url;
	private final Map<String, Object> uriVariables = new LinkedHashMap<>(8);//url  path variable

	public Map<String, Object> getUriVariables() {
		return uriVariables;
	}

	public UrlWrapper addUriVariable(@Nonnull String name, @Nonnull Object value) {
		uriVariables.put(name, value);
		return this;
	}

	public void setUriVariables(@Nonnull Map<String, Object> uriVariables) {
		this.uriVariables.putAll(uriVariables);
	}

	public String getApiName() {
		return apiName;
	}

	public UrlWrapper setApiName(String apiName) {
		this.apiName = apiName;
		return this;
	}

	public String getTargetAppName() {
		return targetAppName;
	}

	public UrlWrapper setTargetAppName(String targetAppName) {
		this.targetAppName = targetAppName;
		return this;
	}

	public String getContextPath() {
		return contextPath;
	}

	public UrlWrapper setContextPath(String contextPath) {
		this.contextPath = contextPath;
		return this;
	}

	public String getUri() {
		return uri;
	}

	public UrlWrapper setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public String getUriKey() {
		return uriKey;
	}

	public UrlWrapper setUriKey(String uriKey) {
		this.uriKey = uriKey;
		return this;
	}

	public String getHost() {
		return host;
	}

	public UrlWrapper setHost(String host) {
		this.host = host;
		return this;
	}

	public String getHostKey() {
		return hostKey;
	}

	public UrlWrapper setHostKey(String hostKey) {
		this.hostKey = hostKey;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public UrlWrapper setUrl(String url) {
		this.url = url;
		return this;
	}

	public String formatUrl(Environment environment) {
		if (StringUtils.hasLength(url)) {
			return URLUtils.build(url, uriVariables);
		} else {
			if (null != environment) {
				if (StringUtils.hasLength(targetAppName)){
					host = environment.getProperty(URLUtils.buildKey(hostKey,targetAppName),targetAppName);
				}
				if (!StringUtils.hasLength(host)){
					Optional.ofNullable(hostKey).ifPresent(key -> {
						host = environment.getProperty(key);
					});
				}
				Optional.ofNullable(contextPath).ifPresent(p->{
					host = URLUtils.buildHost(host,contextPath);
				});
				Optional.ofNullable(uriKey).ifPresent(key -> {
					uri = environment.getProperty(key);
				});
			}
			Assert.hasLength(host, "host is null.");
			Assert.hasLength(uri, "uri is null.");
			return URLUtils.buildPath(host, uri, uriVariables);
		}
	}

	public boolean isKey() {
		return StringUtils.hasLength(hostKey) || StringUtils.hasLength(uriKey);
	}

	public static UrlWrapper create() {
		return new UrlWrapper();
	}
	public static UrlWrapper create(String host, String uri) {
		return new UrlWrapper().setHost(host).setUri(uri);
	}

	public static UrlWrapper createByKey(String hostKey, String uriKey) {
		return new UrlWrapper().setHostKey(hostKey).setUriKey(uriKey);
	}

	public static UrlWrapper createByUrl(String url) {
		return new UrlWrapper().setUrl(url);
	}

	public static UrlWrapper createByHost(String host) {
		return  UrlWrapper.create().setHost(host);
	}

	public static UrlWrapper createByAppName(String appName) {
		return  UrlWrapper.create().setTargetAppName(appName);
	}
}
