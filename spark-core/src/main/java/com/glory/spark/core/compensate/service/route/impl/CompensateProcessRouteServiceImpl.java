/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.compensate.service.route.impl;


import com.glory.http.client.service.router.AbstractRouteService;
import com.glory.spark.core.compensate.model.CompensateItem;
import com.glory.spark.core.compensate.model.CompensateResponse;
import com.glory.spark.core.compensate.service.CompensateProcessService;
import com.glory.spark.core.compensate.service.route.CompensateProcessRouteService;
import com.glory.spark.core.helper.SparkHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/12/3
 * @descprition :
 *
 */
@Component
public class CompensateProcessRouteServiceImpl extends AbstractRouteService<CompensateProcessService> implements CompensateProcessRouteService {
	private SparkHelper helper;
	/**
	 * @param item
	 * @return
	 */
	@Override
	public CompensateResponse process(CompensateItem item) {
		return select(item.getAppName()).process(item);
	}

	/**
	 * @param targetAppName
	 * @return
	 */
	@Override
	protected boolean isLocalRouter(String targetAppName) {
		return helper.isCurrentApp(targetAppName);
	}

	/**
	 * @return
	 */
	@Override
	public String targetAppName() {
		return helper.getMasterAppName();
	}

	@Autowired
	public void setHelper(SparkHelper helper) {
		this.helper = helper;
	}
}
