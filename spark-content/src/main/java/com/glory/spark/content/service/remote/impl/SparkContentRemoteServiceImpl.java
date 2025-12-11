/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.service.remote.impl;


import com.glory.http.client.service.impl.AbstractClientService;
import com.glory.http.client.service.wrapper.HttpRequestWrapper;
import com.glory.http.client.service.wrapper.UrlWrapper;
import com.glory.spark.content.domain.bo.*;
import com.glory.spark.content.helper.ContentHelper;
import com.glory.spark.content.service.remote.SparkContentRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/12/8
 * @descprition :
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class SparkContentRemoteServiceImpl extends AbstractClientService implements SparkContentRemoteService {
	@Value("${spark.http.content.save:/content/save}")
	private String saveContent;
	@Value("${spark.http.content.find:/content/find/{listId}}")
	private String findContent;
	@Value("${spark.http.content.find-spark-status:/content/findBySparkCode/{sparkCode}/{status}}")
	private String findSparkAndStatus;
	@Value("${spark.http.content.find-task-status:/content/findByTaskCode/{taskCode}/{status}}")
	private String findTaskAndStatus;
	@Value("${spark.http.content.query:/content/query}")
	private String queryContents;
	@Value("${spark.http.track.save:/content/track/save}")
	private String saveTrack;
	@Value("${spark.http.track.find:/content/track/find/{trackId}}")
	private String findTrack;
	@Value("${spark.http.track.find-content-id:/content/track/find/{contentId}/{status}}")
	private String findTracksByContentId;
	@Value("${spark.http.track.query:/content/track/query}")
	private String queryTracks;
	private static  final String SPARK_HTTP_HOST = "spark.http.host.";
	@Autowired
	private ContentHelper helper;
	/**
	 * @param contentBo
	 * @return
	 */
	@Override
	public SparkContentBo saveAndUpdate(SparkContentBo contentBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getContentAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(saveContent);
		HttpRequestWrapper<SparkContentBo,SparkContentBo> wrapper = HttpRequestWrapper.create(contentBo,SparkContentBo.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public SparkContentBo findById(Long listId) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getContentAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findContent)
				.addUriVariable("listId",listId);
		HttpRequestWrapper<SparkContentBo,Object> wrapper = HttpRequestWrapper.create(null,SparkContentBo.class)
				.setUrlWrapper(urlWrapper);
		return get(wrapper);
	}

	/**
	 * @param sparkCode
	 * @param status
	 * @return
	 */
	@Override
	public List<SparkContentBo> findBySparkCode(String sparkCode, Integer status) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getContentAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findSparkAndStatus)
				.addUriVariable("sparkCode",sparkCode)
				.addUriVariable("status",status);
		HttpRequestWrapper<List,Object> wrapper = HttpRequestWrapper.create(null,List.class)
				.setUrlWrapper(urlWrapper);
		return get(wrapper);
	}

	/**
	 * @param taskCode
	 * @param status
	 * @return
	 */
	@Override
	public List<SparkContentBo> findByTaskCode(String taskCode, Integer status) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getContentAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findTaskAndStatus)
				.addUriVariable("taskCode",taskCode)
				.addUriVariable("status",status);
		HttpRequestWrapper<List,Object> wrapper = HttpRequestWrapper.create(null,List.class)
				.setUrlWrapper(urlWrapper);
		return get(wrapper);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SparkContentResponse<SparkContentBo> queryContents(SparkContentRequest request) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getContentAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(queryContents);
		HttpRequestWrapper<SparkContentResponse,SparkContentRequest> wrapper = HttpRequestWrapper.create(request,SparkContentResponse.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	/**
	 * @param trackBo
	 * @return
	 */
	@Override
	public SparkContentTrackBo saveAndUpdate(SparkContentTrackBo trackBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getContentAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(saveTrack);
		HttpRequestWrapper<SparkContentTrackBo,SparkContentTrackBo> wrapper = HttpRequestWrapper.create(trackBo,SparkContentTrackBo.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	/**
	 * @param trackId
	 * @return
	 */
	@Override
	public SparkContentTrackBo findByTrackId(Long trackId) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getContentAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findTrack)
				.addUriVariable("trackId",trackId);
		HttpRequestWrapper<SparkContentTrackBo,Object> wrapper = HttpRequestWrapper.create(null,SparkContentTrackBo.class)
				.setUrlWrapper(urlWrapper);
		return get(wrapper);
	}

	/**
	 * @param contentId
	 * @param status
	 * @return
	 */
	@Override
	public List<SparkContentTrackBo> findByContentId(Long contentId, Integer status) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getContentAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findTracksByContentId)
				.addUriVariable("contentId",contentId)
				.addUriVariable("status",status);
		HttpRequestWrapper<List,Object> wrapper = HttpRequestWrapper.create(null,List.class)
				.setUrlWrapper(urlWrapper);
		return get(wrapper);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SparkContentResponse<SparkContentTrackBo> queryTracks(SparkContentTrackRequest request) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getContentAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(queryTracks);
		HttpRequestWrapper<SparkContentResponse,SparkContentTrackRequest> wrapper = HttpRequestWrapper.create(request,SparkContentResponse.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}
}
