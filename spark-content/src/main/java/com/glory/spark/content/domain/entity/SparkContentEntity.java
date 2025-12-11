/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.data.jpa.converter.TypedListStringConverter;
import com.glory.data.jpa.domain.entity.DomainEntityWithDynamicFields;
import com.glory.foundation.jackson.type.converter.WithTypeMapConverter;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author : YY
 * @date : 2025/12/5
 * @descprition :
 *
 */
@Entity
@Table(name = "T_SPARK_CONTENT")
public class SparkContentEntity extends DomainEntityWithDynamicFields {
	@Id
	@Column(name = "LIST_ID",nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long listId;
	@Column(name = "SPARK_CODE" ,nullable = false)
	private String sparkCode;
	@Column(name = "TYPE",nullable = false)
	private String type;
	@Column(name = "TASK_CODE" ,nullable = false)
	private String taskCode;
	@Column(name = "TENANT")
	private String tenant;
	@Column(name = "SERIAL_ID")
	private String serialId;//the batch number
	@Column(name = "TEMPLATE_CODE")
	private String templateCode;//Content Template Corresponding to the Content
	@Column(name = "CATEGORY")
	private int category;//the business category,eg:Email,SMS...
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "MODULE")
	private int module;
	@Column(name = "SOURCE")
	private int source;//eg:online,batch,compensate...
	@Column(name = "STATUS")
	private int status;//default 0:init,other specific re-planning
	@Column(name = "EXPIRE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime expireDate;//if has effective date
	@Column(name = "RECEIVERS")
	@Convert(converter = TypedListStringConverter.class)
	private List<String> receivers;
	@Column(name = "TRACE_INDEX")
	private int traceIndex;
	@Column(name = "CONTENT")
	@Convert(converter = WithTypeMapConverter.class)
	private final Map<String,Object> content = new HashMap<>();

	@Transient
	private transient List<SparkContentTrackEntity> tracks;
	{
		ignoredProperties.add("tracks");
	}
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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

	public int getModule() {
		return module;
	}

	public void setModule(int module) {
		this.module = module;
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

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public List<String> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}

	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String,Object> content){
		Optional.ofNullable(content).ifPresent(this.content::putAll);
	}

	public int getTraceIndex() {
		return traceIndex;
	}

	public void setTraceIndex(int traceIndex) {
		this.traceIndex = traceIndex;
	}

	public List<SparkContentTrackEntity> getTracks() {
		return tracks;
	}

	public void setTracks(@Nonnull List<SparkContentTrackEntity> tracks) {
		if (null == this.tracks){
			this.tracks = new ArrayList<>();
		}
		this.tracks.addAll(tracks);
	}

	@JsonIgnore
	public SparkContentEntity addTrack(@Nonnull SparkContentTrackEntity track){
		if (null == this.tracks){
			this.tracks = new ArrayList<>();
		}
		this.tracks.add(track);
		return this;
	}
}
