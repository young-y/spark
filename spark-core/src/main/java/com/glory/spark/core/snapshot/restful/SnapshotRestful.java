/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.restful;


import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.snapshot.service.route.SnapshotRouteService;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotItemBo;
import com.glory.spark.core.snapshot.domain.rest.SnapshotRequest;
import com.glory.spark.core.snapshot.domain.rest.SnapshotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author : YY
 * @date : 2025/11/17
 * @descprition :
 *
 */
@RestController
@RequestMapping("snapshot")
public class SnapshotRestful {
	@Autowired
	private SnapshotRouteService service;

	@GetMapping("/{snapshotId}/{includeDetail}")
	public SnapshotBo findById(@PathVariable("snapshotId") Long snapshotId, @PathVariable("includeDetail") boolean includeDetail){
		return service.findById(snapshotId,includeDetail);
	}

	@PostMapping("/save")
	public SnapshotBo saveAndUpdate(@RequestBody SnapshotBo bo){
		return service.saveAndUpdate(bo);
	}

	@PostMapping("/query")
	public SnapshotResponse<SnapshotBo> querySnapshots(@RequestBody SnapshotRequest request){
		return service.querySnapshots(request);
	}

	@GetMapping("/detail/{detailId}")
	public SnapshotDetailBo findByDetailId(@PathVariable("detailId") Long detailId){
		return service.findByDetailId(detailId);
	}

	@GetMapping("/detail/all/{snapshotId}")
	public SnapshotResponse<SnapshotDetailBo> findBySnapshotId(@PathVariable("snapshotId") Long snapshotId){
		return service.findBySnapshotId(snapshotId);
	}

	@GetMapping("/detail/{taskCode}/{status}")
	public SnapshotResponse<SnapshotDetailBo> findByTaskCodeAndStatus(@PathVariable("taskCode") String taskCode, @PathVariable("status") Integer status){
		return service.findByTaskCodeAndStatus(taskCode, TaskStatus.fromPersistableValue(status));
	}

	@PostMapping("/detail/save")
	public SnapshotDetailBo saveAndUpdate(@RequestBody SnapshotDetailBo detailBo){
		if (!StringUtils.hasLength(detailBo.getTraceId())){
			detailBo.setTraceId(UUID.randomUUID().toString());
		}
		return service.saveAndUpdate(detailBo);
	}

	@PostMapping("/detail/query")
	public SnapshotResponse<SnapshotDetailBo> queryDetails(@RequestBody SnapshotRequest request){
		return service.queryDetails(request);
	}

	@PostMapping("/item/query")
	public SnapshotResponse<SnapshotItemBo> querySnapshotDetails(@RequestBody SnapshotRequest request){
		return service.querySnapshotDetails(request);
	}

}
