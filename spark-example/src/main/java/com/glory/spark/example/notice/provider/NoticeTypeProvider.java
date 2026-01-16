/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.notice.provider;


import com.glory.spark.core.component.provider.TypeProvider;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkTypeDesc;
import com.glory.spark.example.notice.NoticeConfig;
import com.glory.spark.resource.domain.bo.ResourceRequest;
import com.glory.spark.resource.domain.bo.SparkTypeDescBo;
import com.glory.spark.resource.service.route.ResourcesRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/3
 * @descprition :
 *
 */
@Component
public class NoticeTypeProvider implements TypeProvider {
	@Autowired
	private ResourcesRouteService resourcesRouteService;
    @Override
    public <T> List<SparkTypeDesc> provide(SparkContext<T> context) {
		ResourceRequest request = new ResourceRequest();
		request.setCode(context.getSparkCode());
		List<SparkTypeDescBo> sparkTypes = resourcesRouteService.findSparkTypes(request);
		return sparkTypes.stream().map(type->{
			SparkTypeDesc desc = new SparkTypeDesc();
			BeanUtils.copyProperties(type,desc);
			desc.setContext(context);
			return desc;
		}).toList();
    }

}
