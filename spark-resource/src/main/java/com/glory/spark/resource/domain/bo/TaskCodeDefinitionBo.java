/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.domain.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.data.jpa.domain.bo.DomainBoWithDynamicFields;
import com.glory.data.jpa.domain.type.BooleanEnum;
import com.glory.spark.resource.domain.type.ActivityStatus;
import jakarta.persistence.Convert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : YY
 * @date : 2026/1/9
 * @descprition :
 *
 */

public class TaskCodeDefinitionBo extends DomainBoWithDynamicFields {

	private Long listId;
	private String code;
	private String name;
	private String description;
	@Convert(converter = ActivityStatus.ActivityStatusConverter.class)
	private ActivityStatus status = ActivityStatus.Enabled;
	private String templateCode;
	private String category;
	private String tenant;
	@Convert(converter = BooleanEnum.BooleanEnumConverter.class)
	private BooleanEnum sync = BooleanEnum.True;
	private String exceptionStrategy;
	private LocalDateTime startEffectiveTime;
	private LocalDateTime endEffectiveTime;
	private List<SparkTypeDefinitionBo> sparkTypes;

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ActivityStatus getStatus() {
		return status;
	}

	public void setStatus(ActivityStatus status) {
		this.status = status;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public void setSync(BooleanEnum sync) {
		this.sync = sync;
	}

	public BooleanEnum getSync() {
		return sync;
	}

	public String getExceptionStrategy() {
		return exceptionStrategy;
	}

	public void setExceptionStrategy(String exceptionStrategy) {
		this.exceptionStrategy = exceptionStrategy;
	}

	public LocalDateTime getStartEffectiveTime() {
		return startEffectiveTime;
	}

	public void setStartEffectiveTime(LocalDateTime startEffectiveTime) {
		this.startEffectiveTime = startEffectiveTime;
	}

	public LocalDateTime getEndEffectiveTime() {
		return endEffectiveTime;
	}

	public void setEndEffectiveTime(LocalDateTime endEffectiveTime) {
		this.endEffectiveTime = endEffectiveTime;
	}

	public List<SparkTypeDefinitionBo> getSparkTypes() {
		return sparkTypes;
	}

	public void setSparkTypes(List<SparkTypeDefinitionBo> sparkTypes) {
		if (null == this.sparkTypes){
			this.sparkTypes = sparkTypes;
		}else {
			this.sparkTypes.addAll(sparkTypes);
		}
	}

	@JsonIgnore
	public void addSparkType(SparkTypeDefinitionBo sparkType){
		if (null == this.sparkTypes){
			this.sparkTypes = new ArrayList<>();
		}
		this.sparkTypes.add(sparkType);
	}

	/**
	 * @return
	 */
	@Override
	public Long getPrimaryId() {
		return getListId();
	}

}
