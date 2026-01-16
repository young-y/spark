/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.domain.bo;


import com.glory.data.jpa.domain.page.PageRequest;
import com.glory.foundation.context.AppContext;
import com.glory.spark.resource.domain.type.ActivityStatus;
import jakarta.persistence.Convert;
import org.springframework.util.StringUtils;

/**
 * @author : YY
 * @date : 2026/1/12
 * @descprition :
 *
 */

public class ResourceRequest extends PageRequest {
	private String code;
	private String type;
	@Convert(converter = ActivityStatus.ActivityStatusConverter.class)
	private ActivityStatus status = ActivityStatus.Enabled;
	private String tenant;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ActivityStatus getStatus() {
		return status;
	}

	public void setStatus(ActivityStatus status) {
		this.status = status;
	}

	public String getTenant() {
		return StringUtils.hasLength(tenant)?tenant: AppContext.getTenantCode();
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
}
