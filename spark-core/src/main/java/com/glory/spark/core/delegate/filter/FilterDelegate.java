/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.filter;


import com.glory.spark.core.component.filter.SparkFilter;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.delegate.base.AbstractDelegate;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author : YY
 * @date : 2025/11/3
 * @descprition :
 *
 */
@Component
public class FilterDelegate extends AbstractDelegate<SparkFilter> {

    public <T>boolean filter(SparkContext<T> context){
        return delegates(context).stream().allMatch(f->f.filter(context));
    }

    public <T> boolean filter(SparkContext<T> context, @Nonnull Predicate<List<SparkFilter>> filters ){
        return filters.test(delegates(context));
    }
}
