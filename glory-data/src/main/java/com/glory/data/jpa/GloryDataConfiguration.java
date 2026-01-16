/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa;


import com.glory.data.jpa.domain.audit.AuditAware;
import com.glory.data.jpa.domain.scaner.JpaPackageScanner;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/10
 * @descprition :
 *
 */
@Configuration
@ComponentScan
public class GloryDataConfiguration{

	@Value("${spark.glory.default.user-id:1}")
	private long defaultUserId;

	@Bean
	@ConditionalOnMissingBean
	public AuditAware auditAware() {
		return (a) -> {
			if (0 == a.getCreateBy()) {
				a.setCreateBy(defaultUserId);
			}
			if (0 == a.getModifiedBy()) {
				a.setModifiedBy(defaultUserId);
			}
			LocalDateTime now = LocalDateTime.now();
			if (null == a.getCreateTime()) {
				a.setCreateTime(now);
			}
			a.setModifiedTime(now);
		};
	}

}
