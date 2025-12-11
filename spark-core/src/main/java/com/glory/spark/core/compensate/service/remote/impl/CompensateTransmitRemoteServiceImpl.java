/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.compensate.service.remote.impl;


import com.glory.http.client.service.impl.AbstractClientService;
import com.glory.http.client.service.wrapper.HttpRequestWrapper;
import com.glory.http.client.service.wrapper.UrlWrapper;
import com.glory.spark.core.compensate.model.CompensateRequest;
import com.glory.spark.core.compensate.model.CompensateResponse;
import com.glory.spark.core.compensate.service.remote.CompensateTransmitRemoteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.glory.spark.core.context.SparkConstant.SPARK_HTTP_HOST;

/**
 * @author : YY
 * @date : 2025/12/3
 * @descprition :
 *
 */
@Component
public class CompensateTransmitRemoteServiceImpl extends AbstractClientService implements CompensateTransmitRemoteService {
	@Value("${spark.http.compensate.transmit:/compensate/transmit}")
	private String doTransmitLauncherUri;
	/**
	 * @param request
	 * @return
	 */
	@Override
	public CompensateResponse transmit(CompensateRequest request) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(request.getSnapshot().getAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(doTransmitLauncherUri);
		HttpRequestWrapper<CompensateResponse,CompensateRequest> wrapper = HttpRequestWrapper.create(request,CompensateResponse.class);
		wrapper.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}
}
