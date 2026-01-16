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

/**
 * @author : YY
 * @date : 2026/1/9
 * @descprition :
 *
 */
@Entity
@Table(name = "T_SPARK_TYPE_DEF")
public class SparkTypeDefinitionEntity extends DomainEntityWithDynamicFields {
	@Id
	@Column(name = "LIST_ID",nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long listId;
	@Column(name = "TYPE",nullable = false)
	private String type;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "STATUS")
	@Convert(converter = ActivityStatus.ActivityStatusConverter.class)
	private ActivityStatus status = ActivityStatus.Enabled;
	@Column(name = "SYNC")
	@Convert(converter = BooleanEnum.BooleanEnumConverter.class)
	private BooleanEnum sync = BooleanEnum.True;
	@Column(name = "TENANT",nullable = false)
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
}
