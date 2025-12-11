/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.compensate.service.local.impl;


import com.glory.spark.core.compensate.model.CompensateItem;
import com.glory.spark.core.compensate.model.CompensateItemRequest;
import com.glory.spark.core.compensate.model.CompensateRequest;
import com.glory.spark.core.compensate.model.CompensateResponse;
import com.glory.spark.core.compensate.service.local.CompensateProcessLocalService;
import com.glory.spark.core.domain.type.RetryStrategy;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.compensate.service.route.CompensateTransmitRouteService;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import com.glory.spark.core.snapshot.domain.rest.SnapshotRequest;
import com.glory.spark.core.snapshot.domain.rest.SnapshotResponse;
import com.glory.spark.core.snapshot.service.local.SnapshotLocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/12/3
 * @descprition :
 *
 */

@Component
public class CompensateProcessLocalServiceImpl implements CompensateProcessLocalService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private SnapshotLocalService snapshotLocalService;
	private CompensateTransmitRouteService routeService;
	/**
	 * @param item
	 * @return
	 */
	@Override
	public CompensateResponse process(CompensateItem item) {
		Assert.notNull(item.getListId(),"ListId is null.");
		Assert.notNull(item.getStrategy(),"RetryStrategy is null.");
		CompensateResponse response = CompensateResponse.success();
		if (item.getStrategy() == RetryStrategy.Retry){
			SnapshotDetailBo detailBo = snapshotLocalService.findByDetailId(item.getListId());
			Assert.notNull(detailBo,"Retry strategy can't find detail:"+item.getListId());
			SnapshotBo snapshotBo = snapshotLocalService.findById(detailBo.getSnapshotId(), false);
			snapshotBo.addDetail(detailBo);
			CompensateRequest request = new CompensateRequest();
			request.setSnapshot(snapshotBo);
			request.setSync(item.isSync());
			request.setStrategy(item.getStrategy());
			return routeService.transmit(request);
		}else{
			SnapshotBo snapshotBo = snapshotLocalService.findById(item.getListId(), false);
			Assert.notNull(snapshotBo,"Can't find snapshot by :"+item.getListId());
			List<SnapshotDetailBo> queryDetails = getSnapshotDetails(item);
			if (!CollectionUtils.isEmpty(queryDetails)){
				snapshotBo.setDetails(queryDetails);
				CompensateRequest request = new CompensateRequest();
				request.setSnapshot(snapshotBo);
				request.setSync(item.isSync());
				request.setStrategy(item.getStrategy());
				return routeService.transmit(request);
			}
			logger.info(">> Can't found details that match condition[{}]",item.getListId());
		}
		return response;
	}

	private List<SnapshotDetailBo> getSnapshotDetails(CompensateItem item) {
		SnapshotRequest request = new SnapshotRequest();
		request.setSnapshotId(item.getListId());
		Optional.ofNullable(item.getType()).ifPresent(request::setType);
		List<Integer> statuses = new ArrayList<>();
		statuses.add(TaskStatus.Fail.getValue());
		if (item.getStrategy() == RetryStrategy.All){
			statuses.add(TaskStatus.Init.getValue());
			statuses.add(TaskStatus.Success.getValue());
		}
		request.setStatuses(statuses);
		SnapshotResponse<SnapshotDetailBo> queryDetails = snapshotLocalService.queryDetails(request);
		return queryDetails.getElements();
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public CompensateResponse process(CompensateItemRequest request) {
		Optional.ofNullable(request.getItems()).ifPresent(items->{
			items.forEach(this::process);
		});
		return CompensateResponse.success();
	}

	@Autowired
	public void setRouteService(CompensateTransmitRouteService routeService) {
		this.routeService = routeService;
	}

	@Autowired
	public void setSnapshotLocalService(SnapshotLocalService snapshotLocalService) {
		this.snapshotLocalService = snapshotLocalService;
	}
}
