/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.notice.integrator;


import com.glory.spark.content.domain.bo.SparkContentBo;
import com.glory.spark.core.component.integrator.IntegratorRunner;
import com.glory.spark.core.domain.SparkResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/11/7
 * @descprition :
 *
 */

//@Component
//@Order(99)
public class NoticeIntegratorRunner implements IntegratorRunner<SparkContentBo> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(SparkResult<SparkContentBo> result) {
        logger.info("Integrator runner 2 .........{}....",result.getFirstElement().getListId());
		result.getContext().getTaskDesc().getDetail().setTargetId(result.getFirstElement().getListId());
//        result.setSuspend(true);
    }
}
