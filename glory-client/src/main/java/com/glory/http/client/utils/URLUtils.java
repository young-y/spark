/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.utils;


import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */

public final class URLUtils {

	/***
	 * uri must format ,eg:/product/{productCode}-{productVersion}/load/{advert}?test= {kkk}
	 * uri variable must be format {XXX}
	 * if variable is ${XXX} ,this is  Not necessary
	 * @param uri
	 * @param uriVariables
	 * @return
	 */
	public static String build(String uri, Map<String,?> uriVariables){
		if(StringUtils.hasLength(uri) &&  !CollectionUtils.isEmpty(uriVariables)){
			if (uri.contains("{")){
				for (Map.Entry<String,?> entry: uriVariables.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					Assert.isTrue(null!= value,key+" for value is empty.");
					String pattern = "{"+key+"}";
					uri=uri.replace(pattern,value.toString());
				}
				uri=removeRedundancy(uri);
				if (uri.contains("$")){
					uri = uri.replace("$","");
				}
			}else if (uri.endsWith("?")){
				for (Map.Entry<String,?> entry: uriVariables.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					Assert.isTrue(null!= value,key+" for value is empty.");
					StringBuilder sb = new StringBuilder();
					if (!uri.endsWith("?")){
						sb.append("&");
					}
					sb.append(key).append("=").append(value.toString());
					uri=uri+sb.toString();
				}
			}else if (uri.endsWith("/")){
				Object var = getFirstVariables(uriVariables);
				if (!Objects.isNull(var)){
					uri = uri+var.toString();
				}
			}
			if (uri.contains("{") || uri.contains("}") || uri.endsWith("?")){
				throw  new RuntimeException(uri+" some variable not replace.");
			}
		}
		if (uri.endsWith("?")){
			uri = uri.replace("?","");
		}
		return uri;
	}

	private static String removeRedundancy(final String uri) {
		if (!uri.contains("${")) {
			return uri;
		}
		String str = uri;
		if (str.lastIndexOf("${") > 1) {
			String s1 = str.substring(0, str.lastIndexOf("$"));
			if (s1.endsWith("/")) {
				s1 = s1.substring(0, s1.length() - 1);
			}
			final String s2 = str.substring(str.indexOf("}", str.lastIndexOf("${")) + 1);
			str = s1 + s2;
		}
		return removeRedundancy(str);
	}

	private static Object getFirstVariables(Map<String,?> uriVariables){
		if (null != uriVariables && !uriVariables.isEmpty()){
			Optional<?> obj = uriVariables.values().stream().findFirst();
			if (obj.isPresent()){
				return obj.get();
			}
		}
		return null;
	}

	public static String buildPath(String host,String uri){
		return buildPath(host,uri,null);
	}

	public static String buildPath(String host, String uri, Map<String,?> uriVariables){
		StringBuilder builder = new StringBuilder();
		if (StringUtils.hasLength(host)){
			if (host.endsWith("/")){
				host=host.substring(0,host.length()-1);
			}
			builder.append(host);
		}
		if (StringUtils.hasLength(uri)){
			if (null != uriVariables && !uriVariables.isEmpty()){
				uri = build(uri,uriVariables);
			}
			if (!uri.startsWith("/")){
				builder.append("/");
			}
			builder.append(uri);
		}
		return builder.toString();
	}

	public static String buildKey(String prefix,String name){
		if (StringUtils.hasLength(prefix)){
			if (!prefix.endsWith(".")){
				prefix = prefix+".";
			}
			return prefix+name;
		}
		return name;
	}

	public static String buildHost(String host,String contextPath){
		if (StringUtils.hasLength(contextPath)){
			if (!host.endsWith("/")){
				host = host+"/";
			}
			if (contextPath.startsWith("/")){
				contextPath = contextPath.substring(1);
			}
			return host+contextPath;
		}
		return host;
	}
}
