/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.service;


import com.glory.spark.content.domain.bo.*;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/12/5
 * @descprition :
 *
 */

public interface SparkContentService {

	SparkContentBo saveAndUpdate(SparkContentBo contentBo);

	SparkContentBo findById(Long listId);

	List<SparkContentBo> findBySparkCode(String sparkCode,Integer status);

	List<SparkContentBo> findByTaskCode(String taskCode,Integer status);

	SparkContentResponse<SparkContentBo> queryContents(SparkContentRequest request);

	SparkContentTrackBo saveAndUpdate(SparkContentTrackBo trackBo);

	SparkContentTrackBo findByTrackId(Long trackId);

	List<SparkContentTrackBo> findByContentId(Long contentId,Integer status);

	SparkContentResponse<SparkContentTrackBo> queryTracks(SparkContentTrackRequest request);

}
