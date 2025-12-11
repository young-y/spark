/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.service.route.impl;


import com.glory.http.client.service.router.AbstractRouteService;
import com.glory.spark.content.domain.bo.*;
import com.glory.spark.content.helper.ContentHelper;
import com.glory.spark.content.service.SparkContentService;
import com.glory.spark.content.service.route.SparkContentRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/12/8
 * @descprition :
 *
 */
@Service
public class SparkContentRouteServiceImpl extends AbstractRouteService<SparkContentService> implements SparkContentRouteService {
	private ContentHelper helper;
	/**
	 * @param targetAppName
	 * @return
	 */
	@Override
	protected boolean isLocalRouter(String targetAppName) {
		return helper.isContentApp(targetAppName);
	}

	/**
	 * @return
	 */
	@Override
	public String targetAppName() {
		return helper.getAppName();
	}

	/**
	 * @param contentBo
	 * @return
	 */
	@Override
	public SparkContentBo saveAndUpdate(SparkContentBo contentBo) {
		return select().saveAndUpdate(contentBo);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public SparkContentBo findById(Long listId) {
		return select().findById(listId);
	}

	/**
	 * @param sparkCode
	 * @param status
	 * @return
	 */
	@Override
	public List<SparkContentBo> findBySparkCode(String sparkCode, Integer status) {
		return select().findBySparkCode(sparkCode, status);
	}

	/**
	 * @param taskCode
	 * @param status
	 * @return
	 */
	@Override
	public List<SparkContentBo> findByTaskCode(String taskCode, Integer status) {
		return select().findByTaskCode(taskCode, status);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SparkContentResponse<SparkContentBo> queryContents(SparkContentRequest request) {
		return select().queryContents(request);
	}

	/**
	 * @param trackBo
	 * @return
	 */
	@Override
	public SparkContentTrackBo saveAndUpdate(SparkContentTrackBo trackBo) {
		return select().saveAndUpdate(trackBo);
	}

	/**
	 * @param trackId
	 * @return
	 */
	@Override
	public SparkContentTrackBo findByTrackId(Long trackId) {
		return select().findByTrackId(trackId);
	}

	/**
	 * @param contentId
	 * @param status
	 * @return
	 */
	@Override
	public List<SparkContentTrackBo> findByContentId(Long contentId, Integer status) {
		return select().findByContentId(contentId, status);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SparkContentResponse<SparkContentTrackBo> queryTracks(SparkContentTrackRequest request) {
		return select().queryTracks(request);
	}

	@Autowired
	public void setHelper(ContentHelper helper) {
		this.helper = helper;
	}
}
