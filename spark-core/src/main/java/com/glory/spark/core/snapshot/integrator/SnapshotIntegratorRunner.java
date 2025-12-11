/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.integrator;


import com.glory.data.jpa.domain.bo.DomainBo;
import com.glory.spark.core.component.integrator.IntegratorRunner;
import com.glory.spark.core.domain.SparkResult;
import com.glory.spark.core.domain.SparkTaskDesc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : YY
 * @date : 2025/12/10
 * @descprition :
 *
 */
@Component
@Order(999)
public class SnapshotIntegratorRunner implements IntegratorRunner<DomainBo> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * @param result
	 */
	@Override
	public void run(SparkResult<DomainBo> result) {
		SparkTaskDesc taskDesc = result.getContext().getTaskDesc();
		Optional.ofNullable(taskDesc.getDetail()).ifPresent(detailBo -> {
			List<DomainBo> elements = result.getElements();
			logger.debug(">> snapshot integrator runner task[{}] ",taskDesc.identity());
			if (!CollectionUtils.isEmpty(elements)){
				detailBo.setRelatedId(elements.getFirst().getPrimaryId());
				if (elements.size() >1){
					Set<Long> elementIds = elements.stream().map(DomainBo::getPrimaryId).collect(Collectors.toSet());
					detailBo.setFieldValue("relatedIds",elementIds);
				}
			}
		});


	}
}
