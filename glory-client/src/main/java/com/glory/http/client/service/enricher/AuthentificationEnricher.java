/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.enricher;


import com.glory.http.client.service.wrapper.AuthentificationWrapper;
import com.glory.http.client.service.wrapper.HttpRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/22
 * @descprition :
 *
 */

public class AuthentificationEnricher implements HttpRequestEnricher {

	private List<AuthorizationSupplier> suppliers;

	/**
	 * @param wrapper
	 * @param <T>
	 * @param <S>
	 */
	@Override
	public <T, S> void enrich(HttpRequestWrapper<T, S> wrapper) {
		suppliers.forEach(s->{
			AuthentificationWrapper auth = s.apply(wrapper);
			if (null != auth && StringUtils.hasLength(auth.getToken())){
				wrapper.addHeader("Authorization", auth.getFullToken());
			}
		});
	}

	@Autowired
	public void setSuppliers(List<AuthorizationSupplier> suppliers) {
		this.suppliers = suppliers;
	}
}
