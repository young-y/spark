/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.notice.controller;


import com.glory.spark.core.component.controller.impl.AbstractTypeController;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkResult;
import com.glory.spark.example.notice.NoticeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/10/30
 * @descprition :
 *
 */
@Component
public class NoticeTypeController extends AbstractTypeController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public <T, E> SparkResult<E> process(SparkContext<T> context) {

        return null;
    }


    @Override
    public List<String> supportTypes() {
        return List.of(NoticeConfig.NOTICE_TYPE);
    }

}
