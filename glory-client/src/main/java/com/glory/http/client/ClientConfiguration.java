/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client;


import com.glory.http.client.service.enricher.AuthentificationEnricher;
import com.glory.http.client.service.enricher.AuthorizationSupplier;
import com.glory.http.client.service.enricher.HttpRequestEnricher;
import com.glory.http.client.service.mocker.ClientMocker;
import com.glory.http.client.service.wrapper.AuthentificationWrapper;
import com.glory.http.client.service.wrapper.HttpRequestWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */
@ComponentScan
@Configuration
public class ClientConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public ClientMocker clientMocker() {

		return new ClientMocker() {
			@Override
			public <T> T mock(Object[] argus, Class<T> responseType) {
				return BeanUtils.instantiateClass(responseType);
			}
		};
	}

	@Bean
	public HttpRequestEnricher httpRequestEnricher() {
		return new AuthentificationEnricher();
	}

	@Bean
	@ConditionalOnMissingBean
	public AuthorizationSupplier authorizationSupplier() {
		return new AuthorizationSupplier() {
			@Override
			public <T, S> AuthentificationWrapper apply(HttpRequestWrapper<T, S> wrapper) {
				return wrapper.getAuthentification();
			}
		};
	}


}
