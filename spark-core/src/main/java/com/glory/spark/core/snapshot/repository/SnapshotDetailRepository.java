/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.repository;


import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.snapshot.domain.entity.SnapshotDetailEntity;
import com.glory.spark.core.snapshot.domain.entity.SnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List; /**
 * @author : YY
 * @date : 2025/11/14
 * @descprition :
 *
 */
@Repository
public interface SnapshotDetailRepository extends JpaRepository<SnapshotDetailEntity, Long> , JpaSpecificationExecutor<SnapshotDetailEntity> {

	List<SnapshotDetailEntity> findBySnapshotId(Long snapshotId);

	List<SnapshotDetailEntity> findByTaskCodeAndStatus(String taskCode, TaskStatus status);

//	List<SnapshotDetailEntity> findBySnapshotIdAndInStatuses(Long snapshotId, List<TaskStatus> statuses);
}
