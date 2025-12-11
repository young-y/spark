/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.repository;


import com.glory.spark.content.domain.entity.SparkContentTrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/12/5
 * @descprition :
 *
 */
@Repository
public interface SparkContentTrackRepository extends JpaRepository<SparkContentTrackEntity,Long>, JpaSpecificationExecutor<SparkContentTrackEntity> {
	List<SparkContentTrackEntity> findByContentIdAndStatus(Long contentId,Integer status);
}
