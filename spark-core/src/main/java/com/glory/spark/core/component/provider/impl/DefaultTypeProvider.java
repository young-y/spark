/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.provider.impl;


import com.glory.spark.core.component.provider.SparkTypeGenerator;
import com.glory.spark.core.component.provider.TypeProvider;
import com.glory.spark.core.context.SparkConstant;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkTypeDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List; /**
 * @author : YY
 * @date : 2025/11/8
 * @descprition :
 *
 */

@Component
@Order(9999999)
@ConditionalOnProperty(prefix = SparkConstant.PROPERTY_PREFIX,name = "filter.single.enabled",havingValue = "true",matchIfMissing = false)
public class DefaultTypeProvider implements TypeProvider {

    private SparkTypeGenerator generator;
    @Override
    public <T> List<SparkTypeDesc> provide(SparkContext<T> context) {
        return generator.generate(context);
    }

    @Autowired
    public void setGenerator(SparkTypeGenerator generator) {
        this.generator = generator;
    }
}
