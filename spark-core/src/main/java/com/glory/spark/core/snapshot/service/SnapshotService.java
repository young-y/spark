/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.service;


import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotItemBo;
import com.glory.spark.core.snapshot.domain.rest.SnapshotRequest;
import com.glory.spark.core.snapshot.domain.rest.SnapshotResponse;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/17
 * @descprition :
 *
 */

public interface SnapshotService {

	SnapshotBo findById(@Nonnull Long snapshotId,boolean includeDetail);

	SnapshotBo saveAndUpdate(@Nonnull SnapshotBo bo);

	SnapshotResponse<SnapshotBo> querySnapshots(@Nonnull SnapshotRequest request);

	SnapshotDetailBo findByDetailId(@Nonnull Long detailId);

	SnapshotResponse<SnapshotDetailBo> findBySnapshotId(@Nonnull Long snapshotId);

	SnapshotResponse<SnapshotDetailBo> findByTaskCodeAndStatus(@Nonnull String taskCode, @Nonnull TaskStatus status);

//	SnapshotResponse<SnapshotDetailBo> findBySnapshotIdAndStatus(@Nonnull Long snapshotId, @Nonnull List<TaskStatus> statuses);

	SnapshotDetailBo saveAndUpdate(@Nonnull SnapshotDetailBo detailBo);

	SnapshotResponse<SnapshotDetailBo> queryDetails(@Nonnull SnapshotRequest request);

	SnapshotResponse<SnapshotItemBo> querySnapshotDetails(@Nonnull SnapshotRequest request);

}
