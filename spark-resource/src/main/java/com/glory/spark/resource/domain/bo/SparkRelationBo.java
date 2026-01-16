/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.domain.bo;


import com.glory.data.jpa.domain.bo.DomainBoWithDynamicFields;

/**
 * @author : YY
 * @date : 2026/1/9
 * @descprition :
 *
 */

public class SparkRelationBo extends DomainBoWithDynamicFields {
	private Long listId;
	private Long sparkId;
	private Long typeId;
	private String tenant;

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public Long getSparkId() {
		return sparkId;
	}

	public void setSparkId(Long sparkId) {
		this.sparkId = sparkId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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
