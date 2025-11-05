/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.base;


import com.glory.spark.core.context.SparkContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/10/28
 * @descprition :
 *
 */

public interface SparkSupport extends Selector{

    default List<String> supportTypes(){
        return List.of();
    }

    @Override
    default boolean match(SparkContext<?> context){
        return match(supportTypes(), context.getType());
    }

    default boolean match(List<String> conditions, String key){
        if (!CollectionUtils.isEmpty(conditions) && StringUtils.hasLength(key)){
            return conditions.contains(key);
        }
        return true;
    }
}
