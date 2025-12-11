/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.domain.audit;


import com.glory.data.jpa.domain.entity.AuditSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */

@Component
public final class AuditHelper {
	private static AuditAware auditAware;

	@Autowired
	public AuditHelper(AuditAware aware) {
		auditAware = aware;
	}

	public static void update(AuditSupport audit) {
		Optional.ofNullable(audit).ifPresent(a -> {
			if (null != auditAware) {
				auditAware.update(a);
			} else {
				LocalDateTime now = LocalDateTime.now();
				if (null == a.getCreateTime()) {
					a.setCreateTime(now);
				}
				a.setModifiedTime(now);
			}
		});
	}

}
