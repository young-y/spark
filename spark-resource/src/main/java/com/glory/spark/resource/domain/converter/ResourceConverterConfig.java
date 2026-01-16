/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.domain.converter;


import com.glory.data.jpa.domain.converter.DomainConverter;
import com.glory.foundation.context.AppContext;
import com.glory.spark.resource.domain.bo.*;
import com.glory.spark.resource.domain.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author : YY
 * @date : 2026/1/9
 * @descprition :
 *
 */
@Configuration
public class ResourceConverterConfig {

	@Bean
	public DomainConverter<SparkCodeDefinitionEntity, SparkCodeDefinitionBo> codeDefinitionEntityToBo(){
		return new DomainConverter<SparkCodeDefinitionEntity, SparkCodeDefinitionBo>() {
			@Override
			public SparkCodeDefinitionBo convert(SparkCodeDefinitionEntity entity) {
				SparkCodeDefinitionBo bo = new SparkCodeDefinitionBo();
				BeanUtils.copyProperties(entity,bo);
				return bo;
			}
		};
	}

	@Bean
	public DomainConverter<SparkCodeDefinitionBo, SparkCodeDefinitionEntity> codeDefinitionBoToEntity(){
		return new DomainConverter<SparkCodeDefinitionBo, SparkCodeDefinitionEntity>() {
			@Override
			public SparkCodeDefinitionEntity convert(SparkCodeDefinitionBo bo) {
				SparkCodeDefinitionEntity entity = new SparkCodeDefinitionEntity();
				BeanUtils.copyProperties(bo,entity,"code");
				if (!StringUtils.hasLength(entity.getTenant())){
					entity.setTenant(AppContext.getTenantCode());
				}
				return entity;
			}
		};
	}

	@Bean
	public DomainConverter<TaskCodeDefinitionEntity, TaskCodeDefinitionBo> taskCodeDefinitionEntityToBo(){
		return new DomainConverter<TaskCodeDefinitionEntity, TaskCodeDefinitionBo>() {
			@Override
			public TaskCodeDefinitionBo convert(TaskCodeDefinitionEntity entity) {
				TaskCodeDefinitionBo bo = new TaskCodeDefinitionBo();
				BeanUtils.copyProperties(entity,bo);
				return bo;
			}
		};
	}

	@Bean
	public DomainConverter<TaskCodeDefinitionBo, TaskCodeDefinitionEntity> taskCodeDefinitionBoToEntity(){
		return new DomainConverter<TaskCodeDefinitionBo, TaskCodeDefinitionEntity>() {
			@Override
			public TaskCodeDefinitionEntity convert(TaskCodeDefinitionBo bo) {
				TaskCodeDefinitionEntity entity = new TaskCodeDefinitionEntity();
				BeanUtils.copyProperties(bo,entity,"code");
				if (!StringUtils.hasLength(entity.getTenant())){
					entity.setTenant(AppContext.getTenantCode());
				}
				return entity;
			}
		};
	}

	@Bean
	public DomainConverter<SparkTypeDefinitionEntity, SparkTypeDefinitionBo> typeDefinitionEntityToBo(){
		return new DomainConverter<SparkTypeDefinitionEntity, SparkTypeDefinitionBo>() {
			@Override
			public SparkTypeDefinitionBo convert(SparkTypeDefinitionEntity entity) {
				SparkTypeDefinitionBo bo = new SparkTypeDefinitionBo();
				BeanUtils.copyProperties(entity,bo);
				return bo;
			}
		};
	}

	@Bean
	public DomainConverter<SparkTypeDefinitionBo, SparkTypeDefinitionEntity> typeDefinitionBoToEntity(){
		return new DomainConverter<SparkTypeDefinitionBo, SparkTypeDefinitionEntity>() {
			@Override
			public SparkTypeDefinitionEntity convert(SparkTypeDefinitionBo bo) {
				SparkTypeDefinitionEntity entity = new SparkTypeDefinitionEntity();
				BeanUtils.copyProperties(bo,entity,"type");
				if (!StringUtils.hasLength(entity.getTenant())){
					entity.setTenant(AppContext.getTenantCode());
				}
				return entity;
			}
		};
	}

	@Bean
	public DomainConverter<SparkRelationEntity, SparkRelationBo> relationEntityToBo(){
		 return new DomainConverter<SparkRelationEntity, SparkRelationBo>() {
			@Override
			public SparkRelationBo convert(SparkRelationEntity entity) {
				SparkRelationBo bo = new SparkRelationBo();
				BeanUtils.copyProperties(entity,bo);
				return bo;
			}
		};
	}

	@Bean
	public DomainConverter<SparkRelationBo, SparkRelationEntity> relationBoToEntity(){
		 return new DomainConverter<SparkRelationBo, SparkRelationEntity>() {
			 @Override
			 public SparkRelationEntity convert(SparkRelationBo bo) {
				 SparkRelationEntity entity = new SparkRelationEntity();
				 BeanUtils.copyProperties(bo,entity);
				 if (!StringUtils.hasLength(entity.getTenant())){
					 entity.setTenant(AppContext.getTenantCode());
				 }
				 return entity;
			 }
		 };
	}

	@Bean
	public DomainConverter<SparkTaskRelationEntity, SparkTaskRelationBo> taskRelationEntityToBo(){
		 return new DomainConverter<SparkTaskRelationEntity, SparkTaskRelationBo>() {
			 @Override
			 public SparkTaskRelationBo convert(SparkTaskRelationEntity entity) {
				 SparkTaskRelationBo bo = new SparkTaskRelationBo();
				 BeanUtils.copyProperties(entity,bo);
				 return bo;
			 }
		 };
	}

	@Bean
	 public DomainConverter<SparkTaskRelationBo, SparkTaskRelationEntity> taskRelationBoToEntity(){
		 return new DomainConverter<SparkTaskRelationBo, SparkTaskRelationEntity>() {
			 @Override
			 public SparkTaskRelationEntity convert(SparkTaskRelationBo bo) {
				 SparkTaskRelationEntity entity = new SparkTaskRelationEntity();
				 BeanUtils.copyProperties(bo,entity);
				 if (!StringUtils.hasLength(entity.getTenant())){
					 entity.setTenant(AppContext.getTenantCode());
				 }
				 return entity;
			 }
 		};
	}

}
