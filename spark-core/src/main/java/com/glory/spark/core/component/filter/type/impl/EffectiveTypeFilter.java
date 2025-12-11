/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.filter.type.impl;


import com.glory.spark.core.component.filter.type.TypeFilter;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.helper.SparkHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.glory.spark.core.context.SparkConstant.FILTER_PREFIX;

/**
 * @author : YY
 * @date : 2025/11/6
 * @descprition :
 *
 */
@Component
@Order(10)
@ConditionalOnProperty(prefix = FILTER_PREFIX, name = "effective", havingValue = "true",matchIfMissing = true)
public class EffectiveTypeFilter implements TypeFilter {

    private SparkHelper helper;
    @Override
    public <T> boolean filter(SparkContext<T> context) {
        if (helper.isValidateCompensate()){
            return context.getTypeDesc().isEffective(context.getProcessDate());
        }
        return SourceFrom.Compensate == context.getSource() || context.getTypeDesc().isEffective(context.getProcessDate());
    }

    @Autowired
    public void setHelper(SparkHelper helper) {
        this.helper = helper;
    }
}
