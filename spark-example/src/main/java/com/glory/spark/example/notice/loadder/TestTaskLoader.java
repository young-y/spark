/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.notice.loadder;


import com.glory.spark.core.component.loader.TaskLoader;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.spark.core.domain.SparkTypeDesc;
import com.glory.spark.example.notice.NoticeConfig;
import com.glory.spark.resource.domain.bo.ResourceRequest;
import com.glory.spark.resource.domain.bo.SparkTaskDescBo;
import com.glory.spark.resource.service.route.ResourcesRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/5
 * @descprition :
 *
 */
@Component
public class TestTaskLoader implements TaskLoader {
	@Autowired
	private ResourcesRouteService resourcesRouteService;
    @Override
    public <T> List<SparkTaskDesc> load(SparkContext<T> context) {
		ResourceRequest request = new ResourceRequest();
		request.setType(context.getTypeDesc().getType());
		List<SparkTaskDescBo> taskCodes = resourcesRouteService.findTaskCodes(request);
		return taskCodes.stream().map(taskCode -> {
			SparkTaskDesc desc = new SparkTaskDesc();
			BeanUtils.copyProperties(taskCode, desc);
			desc.setSparkCode(context.getSparkCode());
			desc.setContext(context);
			return desc;
		}).toList();
    }

}
