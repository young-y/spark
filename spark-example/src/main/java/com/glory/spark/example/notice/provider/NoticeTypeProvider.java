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
    @Override
    public <T> List<SparkTypeDesc> provide(SparkContext<T> context) {
        return List.of(generate(context.getSparkCode()));
    }

    private SparkTypeDesc generate(String sparkCode){
        //TODO test
        SparkTypeDesc desc = new SparkTypeDesc();
        desc.setSparkCode(sparkCode);
        desc.setType(NoticeConfig.NOTICE_TYPE);
        return desc;
    }

    @Override
    public List<String> supportTypes() {
        return List.of(NoticeConfig.NOTICE_TYPE);
    }
}
