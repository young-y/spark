/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.domain.entity;


import com.glory.data.jpa.domain.entity.DomainEntityWithDynamicFields;
import com.glory.spark.core.domain.type.SourceFrom;
import com.glory.spark.core.domain.type.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/11
 * @descprition :
 *
 */
@Entity
@Table(name = "T_SPARK_SNAPSHOT")
public class SnapshotEntity extends DomainEntityWithDynamicFields {
	@Id
	@Column(name = "SNAPSHOT_ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long snapshotId;
	@Column(name = "SPARK_CODE", nullable = false)
	private String sparkCode;
	@Column(name = "SERIAL_ID", nullable = false)
	private String serialId;
	@Column(name = "CONDITION_MODE")
	private int conditionMode;
	@Column(name = "App_NAME", nullable = false)
	private String appName;
	@Column(name = "CONTEXT_PATH")
	private String contextPath;
	@Column(name = "TENANT")
	private String tenant;
	@Column(name = "PROCESS_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime processDate;
	@Column(name = "SOURCE", nullable = false)
	@Convert(converter = SourceFrom.SourceFromConverter.class)
	private SourceFrom source = SourceFrom.Online;
	@Column(name = "STATUS", nullable = false)
	@Convert(converter = TaskStatus.TaskStatusConverter.class)
	private TaskStatus status = TaskStatus.Init;
	@Column(name = "MESSAGE")
	private String message;
	@Column(name = "CONTEXT_DATA")
	private String context;
	@Column(name = "CONTEXT_TYPE")
	private String contextType;
	@Column(name = "REF_OBJECT_DATA")
	private String refObject;
	@Column(name = "REF_TYPE")
	private String refType;

	@OneToMany
	@JoinColumn(name = "SNAPSHOT_ID")
	@Transient
	private List<SnapshotDetailEntity> details;

	{
		ignoredProperties.add("details");
	}

	public Long getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(Long snapshotId) {
		this.snapshotId = snapshotId;
	}

	public String getSparkCode() {
		return sparkCode;
	}

	public void setSparkCode(String sparkCode) {
		this.sparkCode = sparkCode;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public LocalDateTime getProcessDate() {
		return processDate;
	}

	public void setProcessDate(LocalDateTime processDate) {
		this.processDate = processDate;
	}

	public SourceFrom getSource() {
		return source;
	}

	public void setSource(SourceFrom source) {
		this.source = source;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getContextType() {
		return contextType;
	}

	public void setContextType(String contextType) {
		this.contextType = contextType;
	}

	public String getRefObject() {
		return refObject;
	}

	public void setRefObject(String refObject) {
		this.refObject = refObject;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public int getConditionMode() {
		return conditionMode;
	}

	public void setConditionMode(int conditionMode) {
		this.conditionMode = conditionMode;
	}

	public List<SnapshotDetailEntity> getDetails() {
		return details;
	}

	public void setDetails(List<SnapshotDetailEntity> details) {
		this.details = details;
	}
}
