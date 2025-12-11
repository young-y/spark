/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.domain.converter;


import com.glory.data.jpa.domain.converter.DomainConverter;
import com.glory.data.jpa.domain.converter.DomainFactory;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import com.glory.spark.core.snapshot.domain.entity.SnapshotDetailEntity;
import com.glory.spark.core.snapshot.domain.entity.SnapshotEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/12/9
 * @descprition :
 *
 */
@Configuration
public class SnapshotModelConverterConfig {

	@Bean
	public DomainConverter<SnapshotBo, SnapshotEntity> snapshotBoToEntity(){
		return new DomainConverter<SnapshotBo, SnapshotEntity>() {
			@Override
			public SnapshotEntity convert(SnapshotBo bo) {
				SnapshotEntity entity = new SnapshotEntity();
				BeanUtils.copyProperties(bo, entity, bo.getIgnorePropertiesInSync().toArray(String[]::new));
				Optional.ofNullable(bo.getDetails()).ifPresent(details->{
					List<SnapshotDetailEntity> list = details.stream().map(detailBo -> DomainFactory.convert(detailBo,SnapshotDetailEntity.class)).toList();
					entity.setDetails(list);
				});
				return entity;
			}
		};
	}

	@Bean
	public DomainConverter<SnapshotEntity,SnapshotBo> snapshotEntityToBo(){
		return new DomainConverter<SnapshotEntity, SnapshotBo>() {
			@Override
			public SnapshotBo convert(SnapshotEntity entity) {
				SnapshotBo bo = new SnapshotBo();
				BeanUtils.copyProperties(entity, bo);
				Optional.ofNullable(entity.getDetails()).ifPresent(ds->{
					List<SnapshotDetailBo> list = ds.stream().map(d -> DomainFactory.convert(d,SnapshotDetailBo.class)).toList();
					bo.setDetails(list);
				});
				return bo;
			}
		} ;
	}

	@Bean
	public DomainConverter<SnapshotDetailBo,SnapshotDetailEntity> detailBoToEntity(){
		return new DomainConverter<SnapshotDetailBo, SnapshotDetailEntity>() {
			@Override
			public SnapshotDetailEntity convert(SnapshotDetailBo bo) {
				SnapshotDetailEntity entity = new SnapshotDetailEntity();
				BeanUtils.copyProperties(bo,entity,bo.getIgnorePropertiesInSync().toArray(String[]::new));
				return entity;
			}
		};
	}

	@Bean
	public DomainConverter<SnapshotDetailEntity,SnapshotDetailBo> detailEntityToBo(){
		return new DomainConverter<SnapshotDetailEntity, SnapshotDetailBo>() {
			@Override
			public SnapshotDetailBo convert(SnapshotDetailEntity entity) {
				SnapshotDetailBo bo = new SnapshotDetailBo();
				BeanUtils.copyProperties(entity,bo);
				return bo;
			}
		};
	}
}
