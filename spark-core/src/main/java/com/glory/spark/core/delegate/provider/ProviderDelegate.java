/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.provider;


import com.glory.spark.core.component.provider.TypeProvider;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.delegate.base.AbstractDelegate;
import com.glory.spark.core.domain.SparkTypeDesc;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/3
 * @descprition :
 *
 */

@Component
public class ProviderDelegate extends AbstractDelegate<TypeProvider> {

    public <T>List<SparkTypeDesc> provide(SparkContext<T> context){
        for (TypeProvider provider:delegates(context)){
            List<SparkTypeDesc> list = provider.provide(context);
            if (!CollectionUtils.isEmpty(list)){
                return list;
            }
        }
        return List.of();
    }
}
