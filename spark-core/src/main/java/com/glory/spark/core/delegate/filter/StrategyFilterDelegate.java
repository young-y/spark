/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.filter;


import com.glory.spark.core.component.filter.strategy.StrategyFilter;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.delegate.base.AbstractDelegate;
import com.glory.spark.core.domain.type.SourceFrom;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author : YY
 * @date : 2025/11/6
 * @descprition :
 *
 */
@Component
public class StrategyFilterDelegate extends AbstractDelegate<StrategyFilter> {

    public <T>boolean filter(SparkContext<T> context){
        if (SourceFrom.Compensate == context.getSource()){
            Assert.isTrue(null != context.getRetryStrategy(),"The compensate RetryStrategy is null.");
            return delegate(context).filter(context);
        }
        return true;
    }
}
