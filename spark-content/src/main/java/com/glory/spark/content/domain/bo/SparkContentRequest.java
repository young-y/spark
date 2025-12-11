/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.domain.bo;


import com.glory.data.jpa.domain.page.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : YY
 * @date : 2025/12/8
 * @descprition :
 *
 */

public class SparkContentRequest extends PageRequest {
	private Long listId;
	private String sparkCode;
	private String taskCode;
	private String type;
	private Integer status;
	private List<Integer> statuses;
	private Integer source;
	private String serialId;
	private LocalDateTime expireDate;
	private Integer module;
	private List<Integer> modules;
	private String tenant;
	private String templateCode;

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public String getSparkCode() {
		return sparkCode;
	}

	public void setSparkCode(String sparkCode) {
		this.sparkCode = sparkCode;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Integer> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Integer> statuses) {
		this.statuses = statuses;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getModule() {
		return module;
	}

	public void setModule(Integer module) {
		this.module = module;
	}

	public List<Integer> getModules() {
		return modules;
	}

	public void setModules(List<Integer> modules) {
		this.modules = modules;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}
