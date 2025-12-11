/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.listener;


import com.glory.foundation.context.AppContext;
import com.glory.foundation.formater.FormatHelper;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.helper.SparkHelper;
import com.glory.spark.core.snapshot.service.route.SnapshotRouteService;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/4
 * @descprition :
 *
 */

public class DefaultSnapshotListener implements SnapshotListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private SparkHelper helper;
	private SnapshotRouteService service;
    @Override
    public <T> void capture(SparkContext<T> context) {
        SourceFrom source = context.getSource();
        if (SourceFrom.Compensate != source){
            SnapshotBo info = new SnapshotBo();
			Optional.ofNullable(context.getContext()).ifPresent(obj->{
				info.setContext(FormatHelper.toJson(obj));
				info.setContextType(getObjectClassName(obj));
			});
            info.setSparkCode(context.getSparkCode());
            info.setProcessDate(context.getProcessDate());
			Optional.ofNullable(context.getRefObj()).ifPresent(obj->{
				info.setRefObject(FormatHelper.toJson(obj));
				info.setRefType(getObjectClassName(obj));
			});
			info.setConditionMode(context.getConditionMode());
			Optional.ofNullable(context.getSerialId()).ifPresent(info::setSerialId);
			info.setAppName(helper.getAppName());
            info.setContextPath(helper.getContextPath());
			info.setTenant(AppContext.getTenantCode());
            info.setDynamicFields(context.getProperties());
            info.setSource(source);
			SnapshotBo bo = service.saveAndUpdate(info);
			context.setSnapshotInfo(bo);
		}
    }

    private String getObjectClassName(Object obj){
        if (null != obj){
            return obj.getClass().getName();
        }
        return null;
    }

    @Override
    public <T> void captureTask(SparkContext<T> context) {
        SnapshotBo bo = context.getSnapshotInfo();
        SourceFrom source = context.getSource();
        SparkTaskDesc taskDesc = context.getTaskDesc();
        if (SourceFrom.Compensate == source){
            SnapshotDetailBo detail = bo.findDetail(taskDesc.getType(), taskDesc.getTaskCode());
            SnapshotDetailBo newDetail = createSnapshotDetail(bo, taskDesc);
            newDetail.setLastRetryTime(LocalDateTime.now());
            newDetail.setRelatedId(detail.getDetailId());
			newDetail.setSource(source);
			newDetail.setStrategy(context.getRetryStrategy());
            detail.setStatus(TaskStatus.Compensate);
            newDetail.setRetryIndex(detail.getRetryIndex()+1);
            bo.addDetail(newDetail);
        }else {
            Optional.ofNullable(bo).ifPresent(o->{
                SnapshotDetailBo detail = createSnapshotDetail(bo, taskDesc);
                o.addDetail(detail);
            });
        }
    }

    private  SnapshotDetailBo createSnapshotDetail(SnapshotBo bo, SparkTaskDesc taskDesc) {
        SnapshotDetailBo detail = new SnapshotDetailBo();
		detail.setSnapshotId(bo.getSnapshotId());
        detail.setSource(bo.getSource());
        detail.setSparkCode(taskDesc.getSparkCode());
        detail.setTaskCode(taskDesc.getTaskCode());
        detail.setDynamicFields(taskDesc.getProperties());
        detail.setType(taskDesc.getType());
        detail.setStatus(TaskStatus.Init);
		detail.setTraceId(AppContext.getTraceId());
		detail.setTenant(AppContext.getTenantCode());
		taskDesc.setDetail(detail);
        return detail;
    }

	@Override
	public void update(SnapshotBo info) {
		Optional.ofNullable(info).ifPresent(bo->{
			Assert.isTrue(bo.getStatus() != TaskStatus.Init,String.format("Spark[%s] status is Init.",bo.getSparkCode()));
			SnapshotBo ns =service.saveAndUpdate(bo);
			logger.info(">>snapshot id :{}",ns.getSnapshotId());
			bo.setSnapshotId(ns.getSnapshotId());
		});
	}

	@Override
    public void updateTask(SparkTaskDesc taskDesc) {
		if (!taskDesc.isSync()){
			Optional.ofNullable(taskDesc.getDetail()).ifPresent(t->{
				SnapshotDetailBo nbo=service.saveAndUpdate(t);
				t.setDetailId(nbo.getDetailId());
			});
		}
    }

    @Autowired
    public void setHelper(SparkHelper helper) {
        this.helper = helper;
    }

	@Autowired
	public void setService(SnapshotRouteService service) {
		this.service = service;
	}
}
