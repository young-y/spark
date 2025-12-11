/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service;


import com.glory.http.client.service.wrapper.HttpRequestWrapper;
import org.springframework.http.HttpMethod;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */

public interface ClientService {

	<T, S> T exchange(final HttpRequestWrapper<T, S> wrapper);

	default <T, S> T post(final HttpRequestWrapper<T, S> wrapper){
		wrapper.setMethod(HttpMethod.POST);
		return exchange(wrapper);
	}

	default <T, S> T get(final HttpRequestWrapper<T, S> wrapper){
		wrapper.setMethod(HttpMethod.GET);
		return exchange(wrapper);
	}

	default <T, S> T put(final HttpRequestWrapper<T, S> wrapper){
		wrapper.setMethod(HttpMethod.PUT);
		return exchange(wrapper);
	}

}
