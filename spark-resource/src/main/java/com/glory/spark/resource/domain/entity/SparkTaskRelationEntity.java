/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.domain.entity;


import com.glory.data.jpa.domain.entity.DomainEntityWithDynamicFields;
import jakarta.persistence.*;

/**
 * @author : YY
 * @date : 2026/1/9
 * @descprition :
 *
 */
@Entity
@Table(name = "T_SPARK_TASK_RELATION")
public class SparkTaskRelationEntity extends DomainEntityWithDynamicFields {
	@Id
	@Column(name = "LIST_ID",nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long listId;
	@Column(name = "TYPE_ID",nullable = false)
	private Long typeId;
	@Column(name = "TASK_ID",nullable = false)
	private Long taskId;
	@Column(name = "TENANT",nullable = false)
	private String tenant;

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
}
