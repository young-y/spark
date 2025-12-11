/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.data.jpa.domain.PrimaryPropertySupport;
import com.glory.data.jpa.domain.PropertiesSyncAware;
import com.glory.data.jpa.domain.UniquePropertiesAware;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : YY
 * @date : 2025/11/11
 * @descprition :
 *
 */

public abstract class DomainBo implements Serializable, PropertiesSyncAware , PrimaryPropertySupport , UniquePropertiesAware {

    private long createBy;
    private long modifiedBy;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
	@JsonIgnore
	protected final transient Set<String> ignoredProperties = new HashSet<>(8);
	{
		ignoredProperties.add("createBy");
		ignoredProperties.add("modifiedBy");
		ignoredProperties.add("createTime");
		ignoredProperties.add("modifiedTime");
	}

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

	@JsonIgnore
	public abstract Long getPrimaryId();
	@Override
	@JsonIgnore
	public Set<String> getIgnorePropertiesInSync() {
		return ignoredProperties;
	}

}
