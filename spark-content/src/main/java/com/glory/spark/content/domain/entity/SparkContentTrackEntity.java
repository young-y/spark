/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.domain.entity;


import com.glory.data.jpa.domain.entity.DomainEntityWithDynamicFields;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author : YY
 * @date : 2025/12/5
 * @descprition :
 *
 */
@Entity
@Table(name = "T_SPARK_CONTENT_TRACE")
public class SparkContentTrackEntity extends DomainEntityWithDynamicFields {
	@Id
	@Column(name = "TRACK_ID",nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trackId;
	@Column(name = "CONTENT_ID" ,nullable = false)
	private Long contentId;
	@Column(name = "SERIAL_ID")
	private String serialId;
	@Column(name = "TENANT")
	private String tenant;
	@Column(name = "CATEGORY" ,nullable = false)
	private int category;
	@Column(name = "SOURCE" ,nullable = false)
	private int source;
	@Column(name = "STATUS" ,nullable = false)
	private int status;
	@Column(name = "FILE")
	private String file;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "TRACE_TIME",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime trackTime;
	@Column(name = "INDEX")
	private int index;

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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTrackTime() {
		return trackTime;
	}

	public void setTrackTime(LocalDateTime trackTime) {
		this.trackTime = trackTime;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
