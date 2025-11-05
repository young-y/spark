/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.impl;


import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.spark.core.domain.snapshot.SnapshotDetail;
import com.glory.spark.core.domain.snapshot.SnapshotInfo;
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.helper.SparkHelper;
import com.glory.spark.core.snapshot.SnapshotListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/4
 * @descprition :
 *
 */

public class DefaultSnapshotListener implements SnapshotListener {

    private SparkHelper helper;
    @Override
    public <T> void capture(SparkContext<T> context) {
        SourceFrom source = context.getSource();
        if (SourceFrom.Compensate != source){
            SnapshotInfo info = new SnapshotInfo();
            info.setContext(context.getContext());
            info.setContextType(getObjectClassName(context.getContext()));
            info.setSparkCode(context.getSparkCode());
            info.setProcessDate(context.getProcessDate());
            info.setRefObject(context.getRefObj());
            info.setRefType(getObjectClassName(context.getRefObj()));
            info.setSerialId(helper.nextSerialId());
            info.setAppName(helper.getAppName());
            info.setContextPath(helper.getContextPath());
            info.setProperties(context.getProperties());
            info.setSource(source);
            context.setSnapshotInfo(info);
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
        SnapshotInfo info = context.getSnapshotInfo();
        SourceFrom source = context.getSource();
        SparkTaskDesc taskDesc = context.getTaskDesc();
        if (SourceFrom.Compensate == source){
            SnapshotDetail detail = info.findDetail(taskDesc.getType(), taskDesc.getTaskCode());
            SnapshotDetail newDetail = getSnapshotDetail(source, taskDesc);
            newDetail.setLastRetryTime(LocalDateTime.now());
            newDetail.setRelatedId(detail.getDetailId());
            detail.setStatus(TaskStatus.Compensate);
            newDetail.setRetryIndex(detail.getRetryIndex()+1);
            info.addDetail(newDetail);
        }else {
            Optional.ofNullable(info).ifPresent(o->{
                SnapshotDetail detail = getSnapshotDetail(source, taskDesc);
                info.addDetail(detail);
            });
        }
    }

    private static SnapshotDetail getSnapshotDetail(SourceFrom source, SparkTaskDesc taskDesc) {
        SnapshotDetail detail = new SnapshotDetail();
        detail.setSource(source);
        detail.setSparkCode(taskDesc.getSparkCode());
        detail.setTaskCode(taskDesc.getTaskCode());
        detail.setProperties(taskDesc.getProperties());
        detail.setType(taskDesc.getType());
        detail.setStatus(TaskStatus.Init);
        return detail;
    }

    @Override
    public void updateSnapshot(SnapshotInfo info, Exception e) {
        if (null != info && !CollectionUtils.isEmpty(info.getDetails())){
            if (null == e || info.getDetails().stream().allMatch(t->TaskStatus.Fail != t.getStatus())){
                info.setStatus(TaskStatus.Success);
            }else {
                info.setStatus(TaskStatus.Fail);
            }
            //TODO save
        }
    }

    @Override
    public void updateSnapshotTask(SnapshotDetail taskInfo, Exception e) {
        if (TaskStatus.Compensate !=taskInfo.getStatus()){
            if (null == e){
                taskInfo.setStatus(TaskStatus.Success);
            }else {
                taskInfo.setStatus(TaskStatus.Fail);
                taskInfo.setMessage(e.getMessage());
            }
        }
    }

    @Autowired
    public void setHelper(SparkHelper helper) {
        this.helper = helper;
    }
}
