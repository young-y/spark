/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.domain.converter;


import com.glory.data.jpa.domain.converter.DomainConverter;
import com.glory.spark.content.domain.bo.SparkContentBo;
import com.glory.spark.content.domain.bo.SparkContentTrackBo;
import com.glory.spark.content.domain.entity.SparkContentEntity;
import com.glory.spark.content.domain.entity.SparkContentTrackEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : YY
 * @date : 2025/12/9
 * @descprition :
 *
 */
@Configuration
public class ContentConverterConfig {

	@Bean
	public DomainConverter<SparkContentBo, SparkContentEntity> contentBoToEntity(){
		return new DomainConverter<SparkContentBo, SparkContentEntity>() {
			@Override
			public SparkContentEntity convert(SparkContentBo bo) {
				SparkContentEntity entity = new SparkContentEntity();
				BeanUtils.copyProperties(bo,entity,entity.getIgnorePropertiesInSync().toArray(String[]::new));
				return entity;

			}
		};
	}

	@Bean
	public DomainConverter<SparkContentEntity,SparkContentBo> contentEntityToBo(){
		return new DomainConverter<SparkContentEntity, SparkContentBo>() {
			@Override
			public SparkContentBo convert(SparkContentEntity entity) {
				SparkContentBo bo = new SparkContentBo();
				BeanUtils.copyProperties(entity,bo);
				return bo;
			}
		};
	}

	@Bean
	public DomainConverter<SparkContentTrackBo, SparkContentTrackEntity> trackBoToEntity(){
		return new DomainConverter<SparkContentTrackBo, SparkContentTrackEntity>() {
			@Override
			public SparkContentTrackEntity convert(SparkContentTrackBo bo) {
				SparkContentTrackEntity entity = new SparkContentTrackEntity();
				BeanUtils.copyProperties(bo,entity,entity.getIgnorePropertiesInSync().toArray(String[]::new));
				return entity;
			}
		};
	}

	@Bean
	public DomainConverter<SparkContentTrackEntity,SparkContentTrackBo> trackEntityToBo(){
		return new DomainConverter<SparkContentTrackEntity, SparkContentTrackBo>() {
			@Override
			public SparkContentTrackBo convert(SparkContentTrackEntity entity) {
				SparkContentTrackBo bo = new SparkContentTrackBo();
				BeanUtils.copyProperties(entity,bo);
				return bo;
			}
		};
	}
}
