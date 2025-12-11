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
import com.glory.spark.core.compensate.model.CompensateItem;
import com.glory.spark.core.compensate.model.CompensateResponse;
import com.glory.spark.core.compensate.service.remote.CompensateProcessRemoteService;
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
public class CompensateProcessRemoteImpl extends AbstractClientService implements CompensateProcessRemoteService {
	@Value("${spark.http.compensate.process:/compensate/item/process}")
	private String doProcessCompensateUri;
	/**
	 * @param item
	 * @return
	 */
	@Override
	public CompensateResponse process(CompensateItem item) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(item.getAppName())
				.setUri(doProcessCompensateUri)
				.setHostKey(SPARK_HTTP_HOST);
		HttpRequestWrapper<CompensateResponse,CompensateItem> wrapper = HttpRequestWrapper.create(item,CompensateResponse.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

}
