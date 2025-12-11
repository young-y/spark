/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.notice.storage;


import com.glory.foundation.formater.FormatHelper;
import com.glory.spark.content.domain.bo.SparkContentBo;
import com.glory.spark.content.service.route.SparkContentRouteService;
import com.glory.spark.core.component.storage.StorageHandler;
import com.glory.spark.core.domain.SparkResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/27
 * @descprition :
 *
 */

@Component
public class TestStorageHandler implements StorageHandler<SparkContentBo> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SparkContentRouteService routeService;

	/**
	 * @param result
	 * @param
	 */
	@Override
	public void stored(SparkResult<SparkContentBo> result) {
		Optional.ofNullable(result.getElements()).ifPresent(elements -> {
			elements.forEach(e -> {
				SparkContentBo bo = routeService.saveAndUpdate(e);
				BeanUtils.copyProperties(bo,e);
			});
		});
		logger.info(">> storage {}", FormatHelper.toJson(result.getElements()));
	}

	@Override
	public List<String> supportSparkCodes() {
		return List.of("testSpark");
	}
}
