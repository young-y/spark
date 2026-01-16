/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.domain.entity;


import com.glory.data.jpa.domain.entity.DomainEntityWithDynamicFields;
import com.glory.data.jpa.domain.type.BooleanEnum;
import com.glory.spark.resource.domain.type.ActivityStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author : YY
 * @date : 2026/1/9
 * @descprition :
 *
 */
@Entity
@Table(name = "T_SPARK_TASK_DEF")
public class TaskCodeDefinitionEntity extends DomainEntityWithDynamicFields {

	@Id
	@Column(name = "LIST_ID",nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long listId;
	@Column(name = "CODE",nullable = false)
	private String code;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "STATUS")
	@Convert(converter = ActivityStatus.ActivityStatusConverter.class)
	private ActivityStatus status = ActivityStatus.Enabled;
	@Column(name = "TEMPLATE_CODE")
	private String templateCode;
	@Column(name = "CATEGORY")
	private String category;
	@Column(name = "SYNC")
	@Convert(converter = BooleanEnum.BooleanEnumConverter.class)
	private BooleanEnum sync = BooleanEnum.True;
	@Column(name = "EXCEPTION_STRATEGY")
	private String exceptionStrategy;
	@Column(name = "START_EFFECTIVE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime startEffectiveTime;
	@Column(name = "END_EFFECTIVE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime endEffectiveTime;
	@Column(name = "TENANT",nullable = false)
	private String tenant;

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

	public BooleanEnum getSync() {
		return sync;
	}

	public void setSync(BooleanEnum sync) {
		this.sync = sync;
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

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
}
