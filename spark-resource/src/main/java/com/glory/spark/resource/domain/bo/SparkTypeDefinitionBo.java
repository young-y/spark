/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.domain.bo;


import com.glory.data.jpa.domain.bo.DomainBoWithDynamicFields;
import com.glory.data.jpa.domain.type.BooleanEnum;
import com.glory.spark.resource.domain.type.ActivityStatus;
import jakarta.persistence.Convert;

/**
 * @author : YY
 * @date : 2026/1/9
 * @descprition :
 *
 */

public class SparkTypeDefinitionBo extends DomainBoWithDynamicFields {
	private Long listId;
	private String type;
	private String name;
	private String description;
	@Convert(converter = ActivityStatus.ActivityStatusConverter.class)
	private ActivityStatus status = ActivityStatus.Enabled;
	@Convert(converter = BooleanEnum.BooleanEnumConverter.class)
	private BooleanEnum sync = BooleanEnum.True;
	private String tenant;

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public BooleanEnum getSync() {
		return sync;
	}

	public void setSync(BooleanEnum sync) {
		this.sync = sync;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	/**
	 * @return
	 */
	@Override
	public Long getPrimaryId() {
		return getListId();
	}
}
