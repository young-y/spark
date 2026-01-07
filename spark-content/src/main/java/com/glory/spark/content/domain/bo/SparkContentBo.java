/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.domain.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.data.jpa.domain.bo.DomainBoWithDynamicFields;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Transient;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author : YY
 * @date : 2025/12/5
 * @descprition :
 *
 */

public class SparkContentBo extends DomainBoWithDynamicFields {
	private Long listId;
	private String sparkCode;
	private String type;
	private String taskCode;
	private String tenant;
	private String serialId;//the batch number
	private String templateCode;//Content Template Corresponding to the Content
	private int category;//the business category,eg:Email,SMS...
	private String name;
	private String description;
	private int module;
	private int source;//eg:online,batch,compensate...
	private int status;//default 0:init,other specific re-planning
	private LocalDateTime expireDate;//if that need effective date
	private List<String> receivers;
	private int trackIndex;
	private final Map<String,Object> content = new HashMap<>();

	@Transient
	private List<SparkContentTrackBo> tracks;

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

	@JsonIgnore
	public SparkContentBo addContent(@Nonnull String key,@Nonnull Object value){
		this.content.put(key, value);
		return this;
	}

	public int getTrackIndex() {
		return trackIndex;
	}

	public void setTrackIndex(int trackIndex) {
		this.trackIndex = trackIndex;
	}

	public List<SparkContentTrackBo> getTracks() {
		return tracks;
	}

	public void setTracks(List<SparkContentTrackBo> tracks) {
		if (null != tracks){
			if (null == this.tracks){
				this.tracks = new ArrayList<>();
			}
			this.tracks.addAll(tracks);
		}
	}

	@JsonIgnore
	public SparkContentBo addTrack(@Nonnull SparkContentTrackBo trackBo){
		if (null == this.tracks){
			this.tracks = new ArrayList<>();
		}
		this.tracks.add(trackBo);
		return this;
	}

	@Override
	public Long getPrimaryId() {
		return getListId();
	}
}
