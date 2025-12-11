/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core;


import com.glory.spark.core.component.controller.TypeController;
import com.glory.spark.core.component.controller.impl.DefaultTypeController;
import com.glory.spark.core.component.loader.SparkTaskGenerator;
import com.glory.spark.core.component.provider.SparkTypeGenerator;
import com.glory.foundation.context.AppContext;
import com.glory.spark.core.filter.SparkAppContextFilter;
import com.glory.foundation.context.GloryThreadContextHolder;
import com.glory.spark.core.filter.SparkUserContextInitializer;
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.spark.core.domain.SparkTypeDesc;
import com.glory.spark.core.launcher.SparkLauncher;
import com.glory.spark.core.launcher.impl.DefaultSparkLauncher;
import com.glory.spark.core.snapshot.listener.NoneSnapshotListener;
import com.glory.spark.core.snapshot.listener.SnapshotListener;
import com.glory.spark.core.snapshot.listener.DefaultSnapshotListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executor;

/**
* @date : 2025/11/5
* @author : YY
* @descprition :
*
*/

@Configuration
@ComponentScan
@EntityScan
@EnableJpaRepositories
public class SparkConfiguration {

    @Value("${spark.executor.max-pool-size:50}")
    private int maxPoolSize;
    @Value("${spark.executor.core-pool-size:20}")
    private int corePoolSize;
    @Value("${spark.executor.queue-capacity:20}")
    private int queueCapacity;

    @Bean("sparkExecutor")
    @ConditionalOnMissingBean(name = "sparkExecutor")
    public Executor sparkExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("spark-");
        executor.setTaskDecorator((r)->{
            Map<String, Object> contextMap = GloryThreadContextHolder.getContextMap();
            return ()->{
                try{
                    GloryThreadContextHolder.setMap(contextMap);
                    r.run();
                }finally {
                    GloryThreadContextHolder.clear();
                }
            };
        });
        return executor;
    }

    @Bean
    @ConditionalOnMissingBean
    public SparkLauncher sparkLauncher(){
        return new DefaultSparkLauncher();
    }

    @Bean
    @ConditionalOnMissingBean
	@Order(999)
    public SnapshotListener snapshotListener(){
        return new DefaultSnapshotListener();
    }

	@Bean
	@ConditionalOnProperty(name = "spark.snapshot.disabled",havingValue = "true",matchIfMissing = false)
	@Order(1)
	public SnapshotListener noneSnapshotListener() {
		return new NoneSnapshotListener();
	}

	@Bean
    @Order(9999)
    public TypeController typeController(){
        return new DefaultTypeController();
    }

    @Bean
    @ConditionalOnMissingBean
    public SparkTypeGenerator sparkTypeGenerator(){
        return (context)->{
            SparkTypeDesc desc = new SparkTypeDesc();
            desc.setSparkCode(context.getSparkCode());
            Optional.ofNullable(context.getType()).ifPresent(desc::setType);
            if (!StringUtils.hasLength(desc.getType())){
                desc.setType(context.getSparkCode());
            }
            desc.setContext(context);
            return List.of(desc);
        };
    }
	@Bean
	@ConditionalOnMissingBean
	public SparkTaskGenerator sparkTaskGenerator(){
		return context -> {
			SparkTaskDesc desc = new SparkTaskDesc();
			desc.setSparkCode(context.getSparkCode());
			desc.setType(context.getType());
			desc.setTaskCode(context.getTaskCode());
			desc.setContext(context);
			return List.of(desc);
		};
	}

    @Bean
    @ConditionalOnProperty(name = "spark.app-context.filter.enabled",havingValue = "true",matchIfMissing = true)
    public FilterRegistrationBean<SparkAppContextFilter> sparkAppContextFilterBean(SparkAppContextFilter defaultSparkAppContextFilter) {
        FilterRegistrationBean<SparkAppContextFilter> filter = new FilterRegistrationBean<>();
        filter.setName("sparkAppContextFilter");
        filter.setFilter(defaultSparkAppContextFilter);
        filter.setOrder(100);
        return filter;
    }

	@Bean
	@ConditionalOnProperty(name = "spark.app-context.filter.enabled",havingValue = "true",matchIfMissing = true)
	public SparkAppContextFilter defaultSparkAppContextFilter(){
		return new SparkAppContextFilter();
	}

    @Bean
    @ConditionalOnMissingBean
    public SparkUserContextInitializer  sparkUserContextInitializer(){
        return (request)->{
			AppContext.setUserId(100L);
			AppContext.setTenantCode("SPARK");
			AppContext.setTraceId(UUID.randomUUID().toString());
        };
    }

}
