/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.service.route.impl;


import com.glory.http.client.service.router.AbstractRouteService;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.helper.SparkHelper;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotItemBo;
import com.glory.spark.core.snapshot.domain.rest.SnapshotRequest;
import com.glory.spark.core.snapshot.domain.rest.SnapshotResponse;
import com.glory.spark.core.snapshot.service.SnapshotService;
import com.glory.spark.core.snapshot.service.route.SnapshotRouteService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : YY
 * @date : 2025/11/24
 * @descprition :
 *
 */
@Service
public class SnapshotRouteServiceImpl extends AbstractRouteService<SnapshotService> implements SnapshotRouteService {
	private SparkHelper helper;

	/**
	 * @param snapshotId
	 * @param includeDetail
	 * @return
	 */
	@Override
	public SnapshotBo findById(@Nonnull Long snapshotId, boolean includeDetail) {
		return select().findById(snapshotId, includeDetail);
	}

	/**
	 * @param bo
	 * @return
	 */
	@Override
	public SnapshotBo saveAndUpdate(@Nonnull SnapshotBo bo) {
		return select().saveAndUpdate(bo);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotBo> querySnapshots(@Nonnull SnapshotRequest request) {
		return select().querySnapshots(request);
	}

	/**
	 * @param detailId
	 * @return
	 */
	@Override
	public SnapshotDetailBo findByDetailId(@Nonnull Long detailId) {
		return select().findByDetailId(detailId);
	}

	/**
	 * @param snapshotId
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotDetailBo> findBySnapshotId(@Nonnull Long snapshotId) {
		return select().findBySnapshotId(snapshotId);
	}

	/**
	 * @param taskCode
	 * @param status
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotDetailBo> findByTaskCodeAndStatus(@Nonnull String taskCode, @Nonnull TaskStatus status) {
		return select().findByTaskCodeAndStatus(taskCode, status);
	}

	/**
	 * @param detailBo
	 * @return
	 */
	@Override
	public SnapshotDetailBo saveAndUpdate(@Nonnull SnapshotDetailBo detailBo) {
		return select().saveAndUpdate(detailBo);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotDetailBo> queryDetails(@Nonnull SnapshotRequest request) {
		return select().queryDetails(request);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotItemBo> querySnapshotDetails(@Nonnull SnapshotRequest request) {
		return select().querySnapshotDetails(request);
	}

	/**
	 * @param targetAppName
	 * @return
	 */
	@Override
	protected boolean isLocalRouter(String targetAppName) {
		return helper.isCurrentApp(targetAppName);
	}


	/**
	 * @return
	 */
	@Override
	public String targetAppName() {
		return helper.getMasterAppName();
	}

	@Autowired
	public void setHelper(SparkHelper helper) {
		this.helper = helper;
	}
}
