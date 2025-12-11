/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.compensate.service.local.impl;


import com.glory.foundation.formater.FormatHelper;
import com.glory.spark.core.compensate.model.CompensateRequest;
import com.glory.spark.core.compensate.model.CompensateResponse;
import com.glory.spark.core.compensate.service.local.CompensateTransmitLocalService;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.type.ConditionMode;
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.launcher.SparkLauncher;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author : YY
 * @date : 2025/12/3
 * @descprition :
 * compensate operation
 */
@Component
public class CompensateTransmitLocalServiceImpl implements CompensateTransmitLocalService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private SparkLauncher launcher;
	/** do compensate operation
	 * @param request
	 * @return
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	@Override
	public CompensateResponse transmit(CompensateRequest request) {
		Assert.notNull(request.getSnapshot(),"CompensateItem is null.");
		SnapshotBo bo = request.getSnapshot();
		Assert.isTrue(!CollectionUtils.isEmpty(bo.getDetails()),"Snapshot details is empty.");
		SparkContext context = new SparkContext();
		context.setSparkCode(bo.getSparkCode());
		context.setSnapshotInfo(bo);//when the mode of compensate,not generate a new snapshot
		int mode = bo.getConditionMode();//the parameters for SparkCode or type or taskCode when triggering spark
		if (ConditionMode.Type.isMode(mode)){//type mode 2
			context.setType(bo.getDetails().getFirst().getType());
		}
		if (ConditionMode.TaskCode.isMode(mode)){//taskCode mode 4
			context.setTaskCode(bo.getDetails().getFirst().getTaskCode());
		}
		context.setContext(getObjectByJson(bo.getContextType(),bo.getContext()));
		context.setRefObj(getObjectByJson(bo.getRefType(),bo.getRefObject()));
		context.setSource(SourceFrom.Compensate);
		context.setRetryStrategy(request.getStrategy());//compensate mode
		context.setProperties(bo.getDynamicFields());
		context.setProcessDate(bo.getProcessDate());
		if (request.isSync()){
			launcher.transmit(context);
		}else {
			launcher.asyncTransmit(context);
		}
		return CompensateResponse.success();
	}

	private Object getObjectByJson(String classType, String json){
		if (StringUtils.hasLength(classType) && StringUtils.hasLength(json)){
			try{
				Class<?> clz = Class.forName(classType);
				return FormatHelper.fromJson(json, clz);
			}catch (Throwable t){
				logger.warn("Format snapshot context object exception:",t);
			}
		}
		return null;
	}

	@Autowired
	public void setLauncher(SparkLauncher launcher) {
		this.launcher = launcher;
	}
}
