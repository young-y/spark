/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.service.remote.impl;


import com.glory.http.client.service.impl.AbstractClientService;
import com.glory.http.client.service.wrapper.HttpRequestWrapper;
import com.glory.http.client.service.wrapper.UrlWrapper;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.helper.SparkHelper;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotItemBo;
import com.glory.spark.core.snapshot.domain.rest.SnapshotRequest;
import com.glory.spark.core.snapshot.domain.rest.SnapshotResponse;
import com.glory.spark.core.snapshot.service.remote.SnapshotRemoteService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.glory.spark.core.context.SparkConstant.SPARK_HTTP_HOST;

/**
 * @author : YY
 * @date : 2025/11/24
 * @descprition :
 *
 */
@Service
@SuppressWarnings("rawtypes")
public class SnapshotRemoteServiceImpl extends AbstractClientService implements SnapshotRemoteService {

	@Value("${spark.http.snapshot.save:/snapshot/save}")
	private String saveSnapshotUri;
	@Value("${spark.http.snapshot.find-by-id:/snapshot/{snapshotId}/{includeDetail}}")
	private String findBySnapshotIdUri;
	@Value("${spark.http.snapshot.query:/snapshot/query}")
	private String querySnapshotsUri;
	@Value("${spark.http.snapshot.detail.save:/snapshot/detail/save}")
	private String saveSnapshotDetailUri;
	@Value("${spark.http.snapshot.detail.find-by-id:/snapshot/detail/{detailId}}")
	private String findByDetailIdUri;
	@Value("${spark.http.snapshot.detail.find-snapshot-id:/snapshot/detail/all/{snapshotId}}")
	private String findDetailsBySnapshotIdUri;
	@Value("${spark.http.snapshot.detail.query:/snapshot/detail/query}")
	private String queryDetailsUri;
	@Value("${spark.http.snapshot.detail.queryItems:/snapshot/item/query}")
	private String querySnapshotItemsUri;
	@Value("${spark.http.snapshot.detail.task-status:/snapshot/detail/{taskCode}/{status}}")
	private String findByTaskCodeAndStatusUri;
	private SparkHelper helper;

/**
	 * @param bo
	 * @return
	 */
	@Override
	public SnapshotBo saveAndUpdate(SnapshotBo bo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getMasterAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(saveSnapshotUri);
		HttpRequestWrapper<SnapshotBo, SnapshotBo> wrapper = HttpRequestWrapper.create(bo, SnapshotBo.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	@Override
	public SnapshotBo findById(@Nonnull Long snapshotId, boolean includeDetails) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getMasterAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findBySnapshotIdUri)
				.addUriVariable("snapshotId", snapshotId)
				.addUriVariable("includeDetails", includeDetails);
		HttpRequestWrapper<SnapshotBo, Object> wrapper = HttpRequestWrapper.create(null, SnapshotBo.class)
				.setUrlWrapper(urlWrapper);
		return get(wrapper);
	}

	@Override
	public SnapshotResponse<SnapshotBo> querySnapshots(@Nonnull SnapshotRequest request) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getMasterAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(querySnapshotsUri);
		HttpRequestWrapper<SnapshotResponse, SnapshotRequest> wrapper = HttpRequestWrapper.create(request, SnapshotResponse.class)
				.setUrlWrapper(urlWrapper);
		wrapper.addHeader("Content-Type","application/json");
		return post(wrapper);
	}

	/**
	 * @param taskCode
	 * @param status
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotDetailBo> findByTaskCodeAndStatus(@Nonnull String taskCode, @Nonnull TaskStatus status) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getMasterAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findByTaskCodeAndStatusUri)
				.addUriVariable("taskCode", taskCode)
				.addUriVariable("status", status.getValue());
		HttpRequestWrapper<SnapshotResponse, Object> wrapper = HttpRequestWrapper.create(null, SnapshotResponse.class)
				.setUrlWrapper(urlWrapper);
		return get(wrapper);
	}

	/**
	 * @param detailBo
	 * @return
	 */
	@Override
	public SnapshotDetailBo saveAndUpdate(@Nonnull SnapshotDetailBo detailBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getMasterAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(saveSnapshotDetailUri);
		HttpRequestWrapper<SnapshotDetailBo, SnapshotDetailBo> wrapper = HttpRequestWrapper.create(detailBo, SnapshotDetailBo.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	@Override
	public SnapshotDetailBo findByDetailId(@Nonnull Long detailId) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getMasterAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findByDetailIdUri)
				.addUriVariable("detailId", detailId);
		HttpRequestWrapper<SnapshotDetailBo, Object> wrapper = HttpRequestWrapper.create(null, SnapshotDetailBo.class)
				.setUrlWrapper(urlWrapper);
		return get(wrapper);
	}

	@Override
	public SnapshotResponse<SnapshotDetailBo> findBySnapshotId(@Nonnull Long snapshotId) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getMasterAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findDetailsBySnapshotIdUri)
				.addUriVariable("snapshotId", snapshotId);
		HttpRequestWrapper<SnapshotResponse, Object> wrapper = HttpRequestWrapper.create(null, SnapshotResponse.class)
				.setUrlWrapper(urlWrapper);
		return get(wrapper);
	}

	@Override
	public SnapshotResponse<SnapshotDetailBo> queryDetails(@Nonnull SnapshotRequest request) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getMasterAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(queryDetailsUri);
		HttpRequestWrapper<SnapshotResponse, SnapshotRequest> wrapper = HttpRequestWrapper.create(request, SnapshotResponse.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	@Override
	public SnapshotResponse<SnapshotItemBo> querySnapshotDetails(@Nonnull SnapshotRequest request) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getMasterAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(querySnapshotItemsUri);
		HttpRequestWrapper<SnapshotResponse, SnapshotRequest> wrapper = HttpRequestWrapper.create(request, SnapshotResponse.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	@Autowired
	public void setHelper(SparkHelper helper) {
		this.helper = helper;
	}
}
