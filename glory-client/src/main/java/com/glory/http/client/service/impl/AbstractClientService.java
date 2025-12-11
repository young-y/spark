/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.http.client.service.impl;


import com.glory.foundation.formater.FormatHelper;
import com.glory.http.client.service.ClientService;
import com.glory.http.client.service.enricher.HttpRequestEnricher;
import com.glory.http.client.service.wrapper.HttpRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/21
 * @descprition :
 *
 */

public abstract class AbstractClientService implements ClientService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected List<HttpRequestEnricher> enrichers;
	@Value("${glory.client.http.log:true}")
	private boolean logging;
	@Autowired
	private Environment env;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public <T, S> T exchange(HttpRequestWrapper<T, S> wrapper) {
		StopWatch watch = new StopWatch();
		watch.start();
		T response = null;
		enrichHttpRequest(wrapper);
		HttpEntity<S> entity = wrapper.getHttpEntity();
		RestTemplate restTemplate = getRestTemplate(wrapper);
		Assert.notNull(restTemplate, "Client restTemplate is null");
		String url = wrapper.getUrlWrapper().formatUrl(env);
		logging(true, url, wrapper);
		ResponseEntity<T> responseEntity = restTemplate.exchange(url, wrapper.getMethod(), entity, ParameterizedTypeReference.forType(wrapper.getResponseClass()),wrapper.getUrlWrapper().getUriVariables());
		if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.hasBody()) {
			wrapper.setResult(responseEntity.getBody());
			response = responseEntity.getBody();
		}
		logging(false, url, wrapper);
		watch.stop();
		logger.debug("Request[{}] cost time:{}", url, watch.getTotalTimeMillis());
		return response;
	}

	protected <T, S> RestTemplate getRestTemplate(HttpRequestWrapper<T, S> wrapper) {
		return null==wrapper.getRestTemplate()?restTemplate:wrapper.getRestTemplate();
	}

	protected <T, S> void logging(boolean isReq, String url, HttpRequestWrapper<T, S> wrapper) {
		String prefix = isReq ? "Request" : "Response";
		Object content = isReq ? wrapper.getRequest() : wrapper.getResult();
		if (logging && wrapper.isLogging()) {
			logger.info("{}:url[{}] body[{}]", prefix, url, FormatHelper.toJson(content));
		} else {
			logger.trace("{}:url[{}] body[{}]", prefix, url, FormatHelper.toJson(content));
		}
	}

	protected <T, S> void enrichHttpRequest(HttpRequestWrapper<T, S> wrapper) {
		enrichers.forEach(e -> {
			e.enrich(wrapper);
		});
	}

	@Autowired
	public void setEnrichers(List<HttpRequestEnricher> enrichers) {
		this.enrichers = enrichers;
	}

}
