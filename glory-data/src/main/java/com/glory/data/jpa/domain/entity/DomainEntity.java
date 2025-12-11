/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.entity;


import com.glory.data.jpa.domain.audit.AuditHelper;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */
@MappedSuperclass
public abstract class DomainEntity implements DomainAware, AuditSupport {

	@Column(name = "create_by", nullable = false)
	private long createBy;
	@Column(name = "modified_by", nullable = false)
	private long modifiedBy;
	@Column(name = "create_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createTime;
	@Column(name = "modified_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifiedTime;

	@Transient
	protected transient Set<String> ignoredProperties = new HashSet<>(8);
	{
		ignoredProperties.add("createBy");
		ignoredProperties.add("modifiedBy");
		ignoredProperties.add("createTime");
		ignoredProperties.add("modifiedTime");
	}

	@Override
	@Transient
	public Set<String> getIgnorePropertiesInSync() {
		return ignoredProperties;
	}

	@PrePersist
	public void prePersist() {
		AuditHelper.update(this);
	}

	@PreUpdate
	public void preUpdate() {
		AuditHelper.update(this);
	}

	@Override
	public Long getCreateBy() {
		return createBy;
	}

	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	@Override
	public Long getModifiedBy() {
		return modifiedBy;
	}

	@Override
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	@Override
	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}
