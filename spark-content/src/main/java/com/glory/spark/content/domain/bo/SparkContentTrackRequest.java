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

public class SparkContentTrackRequest extends PageRequest {
	private Long trackId;
	private Long contentId;
	private String serialId;
	private String tenant;
	private Integer category;
	private Integer source;
	private Integer status;
	private List<Integer> statuses;
	private LocalDateTime trackTime;

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
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

	public LocalDateTime getTrackTime() {
		return trackTime;
	}

	public void setTrackTime(LocalDateTime trackTime) {
		this.trackTime = trackTime;
	}
}
