/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.repository;


import com.glory.spark.resource.domain.bo.SparkTaskRelationBo;
import com.glory.spark.resource.domain.entity.SparkTaskRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : YY
 * @date : 2026/1/12
 * @descprition :
 *
 */
@Repository
public interface SparkTaskRelationRepository extends JpaRepository<SparkTaskRelationEntity, Long>, JpaSpecificationExecutor<SparkTaskRelationEntity> {
	List<SparkTaskRelationEntity> findByTypeId(Long typeId);

	List<SparkTaskRelationEntity> findByTaskId(Long taskId);
}
