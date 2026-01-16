/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.service.local.impl;


import com.glory.data.jpa.domain.converter.DomainFactory;
import com.glory.data.jpa.domain.entity.BeanTuple;
import com.glory.data.jpa.domain.type.PersistableEnumConverterFactory;
import com.glory.foundation.context.AppContext;
import com.glory.spark.resource.domain.bo.*;
import com.glory.spark.resource.domain.entity.*;
import com.glory.spark.resource.repository.*;
import com.glory.spark.resource.service.local.ResourcesLocalService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2026/1/12
 * @descprition :
 *
 */
@Service
public class ResourcesLocalServiceImpl implements ResourcesLocalService {
	private Logger logger = LoggerFactory.getLogger(ResourcesLocalServiceImpl.class);
	private SparkCodeRepository sparkCodeRepository;
	private SparkTaskRelationRepository sparkTaskRelationRepository;
	private SparkRelationRepository sparkRelationRepository;
	private SparkTypeRepository sparkTypeRepository;
	private TaskCodeRepository taskCodeRepository;
	@PersistenceContext
	private EntityManager entityManager;
	private ConversionService mvcConversionService;

	/**
	 * @param sparkCodeDefinitionBo
	 * @return
	 */
	@Override
	public SparkCodeDefinitionBo saveSparkCodeDefinition(SparkCodeDefinitionBo sparkCodeDefinitionBo) {
		SparkCodeDefinitionEntity entity = DomainFactory.convert(sparkCodeDefinitionBo, SparkCodeDefinitionEntity.class);
		SparkCodeDefinitionEntity newEntity = sparkCodeRepository.save(entity);
		SparkCodeDefinitionBo newBo = DomainFactory.convert(newEntity, SparkCodeDefinitionBo.class);
		Optional.ofNullable(sparkCodeDefinitionBo.getSparkTypes()).ifPresent(types -> {
			types.forEach(type -> {
				SparkRelationEntity relationEntity = new SparkRelationEntity();
				relationEntity.setSparkId(newEntity.getListId());
				relationEntity.setTypeId(type.getListId());
				relationEntity.setTenant(newEntity.getTenant());
				sparkRelationRepository.save(relationEntity);
				newBo.addSparkType(type);
			});
		});
		return newBo;
	}

	/**
	 * @param sparkCodeDefinitionBo
	 * @return
	 */
	@Override
	public SparkCodeDefinitionBo updateSparkCodeDefinition(SparkCodeDefinitionBo sparkCodeDefinitionBo) {
		SparkCodeDefinitionEntity entity = DomainFactory.convert(sparkCodeDefinitionBo, SparkCodeDefinitionEntity.class);
		sparkCodeRepository.findById(sparkCodeDefinitionBo.getListId()).ifPresent(oldEntity -> {
			entity.setCode(oldEntity.getCode());
		});
		SparkCodeDefinitionEntity newEntity = sparkCodeRepository.saveAndFlush(entity);
		return DomainFactory.convert(newEntity, SparkCodeDefinitionBo.class);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkCodeDefinition(Long listId, boolean deleteRelation) {
		if (deleteRelation) {
			sparkRelationRepository.findBySparkId(listId).forEach(relation -> {
				sparkRelationRepository.delete(relation);
			});
		}
		sparkCodeRepository.deleteById(listId);
	}

	/**
	 * @param sparkTypeDefinitionBo
	 * @return
	 */
	@Override
	public SparkTypeDefinitionBo saveSparkTypeDefinition(SparkTypeDefinitionBo sparkTypeDefinitionBo) {
		SparkTypeDefinitionEntity entity = DomainFactory.convert(sparkTypeDefinitionBo, SparkTypeDefinitionEntity.class);
		SparkTypeDefinitionEntity newEntity = sparkTypeRepository.save(entity);
		return DomainFactory.convert(newEntity, SparkTypeDefinitionBo.class);
	}

	/**
	 * @param sparkTypeDefinitionBo
	 * @return
	 */
	@Override
	public SparkTypeDefinitionBo updateSparkTypeDefinition(SparkTypeDefinitionBo sparkTypeDefinitionBo) {
		SparkTypeDefinitionEntity entity = DomainFactory.convert(sparkTypeDefinitionBo, SparkTypeDefinitionEntity.class);
		sparkTypeRepository.findById(sparkTypeDefinitionBo.getListId()).ifPresent(oldEntity -> {
			entity.setType(oldEntity.getType());
		});
		SparkTypeDefinitionEntity newEntity = sparkTypeRepository.saveAndFlush(entity);
		return DomainFactory.convert(newEntity, SparkTypeDefinitionBo.class);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkTypeDefinition(Long listId, boolean deleteRelation) {
		if (deleteRelation) {
			sparkRelationRepository.findByTypeId(listId).forEach(relation -> {
				sparkRelationRepository.delete(relation);
			});
			sparkTaskRelationRepository.findByTypeId(listId).forEach(relation -> {
				sparkTaskRelationRepository.delete(relation);
			});
		}
		sparkTypeRepository.deleteById(listId);
	}

	/**
	 * @param taskCodeDefinitionBo
	 * @return
	 */
	@Override
	public TaskCodeDefinitionBo saveTaskCodeDefinition(TaskCodeDefinitionBo taskCodeDefinitionBo) {
		TaskCodeDefinitionEntity entity = DomainFactory.convert(taskCodeDefinitionBo, TaskCodeDefinitionEntity.class);
		TaskCodeDefinitionEntity newEntity = taskCodeRepository.save(entity);
		TaskCodeDefinitionBo newBo = DomainFactory.convert(newEntity, TaskCodeDefinitionBo.class);
		Optional.ofNullable(taskCodeDefinitionBo.getSparkTypes()).ifPresent(types -> {
			types.forEach(type -> {
				SparkTaskRelationEntity relationEntity = new SparkTaskRelationEntity();
				relationEntity.setTaskId(newEntity.getListId());
				relationEntity.setTypeId(type.getListId());
				relationEntity.setTenant(newEntity.getTenant());
				sparkTaskRelationRepository.save(relationEntity);
				newBo.addSparkType(type);
			});
		});
		return newBo;
	}

	/**
	 * @param taskCodeDefinitionBo
	 * @return
	 */
	@Override
	public TaskCodeDefinitionBo updateTaskCodeDefinition(TaskCodeDefinitionBo taskCodeDefinitionBo) {
		TaskCodeDefinitionEntity entity = DomainFactory.convert(taskCodeDefinitionBo, TaskCodeDefinitionEntity.class);
		taskCodeRepository.findById(taskCodeDefinitionBo.getListId()).ifPresent(oldEntity -> {
			entity.setCode(oldEntity.getCode());
		});
		TaskCodeDefinitionEntity newEntity = taskCodeRepository.saveAndFlush(entity);
		return DomainFactory.convert(newEntity, TaskCodeDefinitionBo.class);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteTaskCodeDefinition(Long listId, boolean deleteRelation) {
		if (deleteRelation) {
//			sparkTaskRelationRepository.deleteByTaskId(listId);
			sparkTaskRelationRepository.findByTaskId(listId).forEach(relation -> {
				sparkTaskRelationRepository.delete(relation);
			});
		}
		taskCodeRepository.deleteById(listId);
	}

	/**
	 * @param sparkRelationBo
	 * @return
	 */
	@Override
	public SparkRelationBo saveSparkRelation(SparkRelationBo sparkRelationBo) {
		SparkRelationEntity entity = DomainFactory.convert(sparkRelationBo, SparkRelationEntity.class);
		SparkRelationEntity newEntity = sparkRelationRepository.save(entity);
		return DomainFactory.convert(newEntity, SparkRelationBo.class);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkRelation(Long listId) {
		sparkRelationRepository.deleteById(listId);
	}

	/**
	 * @param sparkTaskRelationBo
	 * @return
	 */
	@Override
	public SparkTaskRelationBo saveSparkTaskRelation(SparkTaskRelationBo sparkTaskRelationBo) {
		SparkTaskRelationEntity entity = DomainFactory.convert(sparkTaskRelationBo, SparkTaskRelationEntity.class);
		SparkTaskRelationEntity newEntity = sparkTaskRelationRepository.save(entity);
		return DomainFactory.convert(newEntity, SparkTaskRelationBo.class);
	}


	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkTaskRelation(Long listId) {
		sparkTaskRelationRepository.deleteById(listId);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	@Cacheable(cacheNames = "findSparkTypes", key = "#p0.code")
	public List<SparkTypeDescBo> findSparkTypes(ResourceRequest request) {
		Assert.hasLength(request.getCode(), "code can not be null");
		Map<String, Object> parameters = new HashMap<>();
		String sql = buildQueryTypeSql(request, parameters);
		logger.debug("SQL: {}", sql);
		NativeQueryImpl query = entityManager.createNativeQuery(sql, SparkTypeDescBo.class).unwrap(NativeQueryImpl.class);
		query.setTupleTransformer((values, aliases) -> {
			BeanTuple beanTuple = new BeanTuple(values, aliases);
			beanTuple.setConversionService(mvcConversionService);
			return beanTuple.convert(SparkTypeDescBo.class);
		});

		if (!CollectionUtils.isEmpty(parameters)) {
			parameters.forEach(query::setParameter);
		}
		if (null != request.getPage()) {
			int number = request.getPage().getNumber();
			int size = request.getPage().getSize();
			query.setFirstResult(number * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}

	private String buildQueryTypeSql(ResourceRequest request, Map<String, Object> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT c.`CODE` sparkCode,t.TYPE type ,t.SYNC sync,c.`STATUS` enabled,");
		builder.append(" c.EXCEPTION_STRATEGY exceptionStrategy,c.START_EFFECTIVE_TIME startEffectiveTime,");
		builder.append(" c.END_EFFECTIVE_TIME endEffectiveTime,c.DYNAMIC_FIELDS properties");
		builder.append(" from T_SPARK_CODE_DEF c,T_SPARK_RELATION r, T_SPARK_TYPE_DEF t ");
		builder.append(" WHERE  c.LIST_ID = r.SPARK_ID ");
		builder.append(" and r.TYPE_ID = t.LIST_ID");
		Optional.ofNullable(request.getStatus()).ifPresent(s -> {
			builder.append(" and c.`STATUS` =:status");
			builder.append(" and t.`STATUS` =:status");
			parameters.put("status", s.getValue());
		});
		Optional.ofNullable(request.getTenant()).ifPresent(tenantCode -> {
			builder.append(" and c.TENANT = :tenantCode");
			parameters.put("tenantCode", tenantCode);
		});
		Optional.ofNullable(request.getCode()).ifPresent(s -> {
			builder.append(" and c.`CODE`=:code");
			parameters.put("code", s);
		});
		Optional.ofNullable(request.getType()).ifPresent(s -> {
			builder.append(" and t.`TYPE`=:type");
			parameters.put("type", s);
		});
		return builder.toString();
	}

	private String buildQueryTaskSql(ResourceRequest request, Map<String, Object> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT c.`CODE` taskCode,c.`NAME` taskName,c.DESCRIPTION taskDescription,c.`STATUS` enabled, ");
		builder.append(" c.SYNC sync,c.EXCEPTION_STRATEGY exceptionStrategy,c.START_EFFECTIVE_TIME startEffectiveTime,");
		builder.append(" c.END_EFFECTIVE_TIME endEffectiveTime,c.DYNAMIC_FIELDS properties,t.TYPE type");
		builder.append(" from T_SPARK_TYPE_DEF t,T_SPARK_TASK_DEF c,T_SPARK_TASK_RELATION r ");
		builder.append(" WHERE c.LIST_ID = r.TASK_ID ");
		builder.append(" and t.LIST_ID = r.TYPE_ID ");
		Optional.ofNullable(request.getStatus()).ifPresent(s -> {
			builder.append(" and c.`STATUS` =:status");
			builder.append(" and t.`STATUS` =:status");
			parameters.put("status", s.getValue());
		});
		Optional.ofNullable(request.getType()).ifPresent(s -> {
			builder.append(" and t.TYPE =:type");
			parameters.put("type", s);
		});
		Optional.ofNullable(request.getCode()).ifPresent(s -> {
			builder.append(" and c.`CODE`=:code");
			parameters.put("code", s);
		});
		Optional.ofNullable(request.getTenant()).ifPresent(tenantCode -> {
			builder.append(" and c.TENANT =:tenantCode");
			parameters.put("tenantCode", tenantCode);
		});
		return builder.toString();
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	@Cacheable(cacheNames = "findTaskCodes", key = "#p0.type")
	public List<SparkTaskDescBo> findTaskCodes(ResourceRequest request) {
		Assert.hasLength(request.getType(), "type can not be null");
		Map<String, Object> parameters = new HashMap<>();
		String sql = buildQueryTaskSql(request, parameters);
		logger.debug(">> findTask SQL: {}", sql);
		NativeQueryImpl query = entityManager.createNativeQuery(sql, SparkTaskDescBo.class).unwrap(NativeQueryImpl.class);
		query.setTupleTransformer((values, aliases) -> {
			BeanTuple beanTuple = new BeanTuple(values, aliases);
			beanTuple.setConversionService(mvcConversionService);
			return beanTuple.convert(SparkTaskDescBo.class);
		});

		if (!CollectionUtils.isEmpty(parameters)) {
			parameters.forEach(query::setParameter);
		}
		if (null != request.getPage()) {
			int number = request.getPage().getNumber();
			int size = request.getPage().getSize();
			query.setFirstResult(number * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}

	@Override
	@Cacheable(cacheNames = "findSparkTasks", key = "#p0.code")
	public List<SparkTaskDescBo> findSparkTasks(ResourceRequest request) {
		Assert.hasLength(request.getCode(), "code can not be null");
		Map<String, Object> parameters = new HashMap<>();
		String sql = buildQuerySparkTaskSql(request, parameters);
		logger.debug(">> findSparkTask SQL: {}", sql);
		NativeQueryImpl query = entityManager.createNativeQuery(sql, SparkTaskDescBo.class).unwrap(NativeQueryImpl.class);
		query.setTupleTransformer((values, aliases) -> {
			BeanTuple beanTuple = new BeanTuple(values, aliases);
			beanTuple.setConversionService(mvcConversionService);
			return beanTuple.convert(SparkTaskDescBo.class);
		});

		if (!CollectionUtils.isEmpty(parameters)) {
			parameters.forEach(query::setParameter);
		}
		if (null != request.getPage()) {
			int number = request.getPage().getNumber();
			int size = request.getPage().getSize();
			query.setFirstResult(number * size);
			query.setMaxResults(size);
		}
		return query.getResultList();
	}

	private String buildQuerySparkTaskSql(ResourceRequest request, Map<String, Object> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT sc.`CODE` sparkCode,c.`CODE` taskCode,c.`NAME` taskName,c.DESCRIPTION taskDescription,c.`STATUS` enabled, ");
		builder.append(" c.SYNC sync,c.EXCEPTION_STRATEGY exceptionStrategy,c.START_EFFECTIVE_TIME startEffectiveTime,");
		builder.append(" c.END_EFFECTIVE_TIME endEffectiveTime,c.DYNAMIC_FIELDS properties,t.TYPE type");
		builder.append(" from T_SPARK_TYPE_DEF t,T_SPARK_TASK_DEF c,T_SPARK_TASK_RELATION r ,T_SPARK_CODE_DEF sc,T_SPARK_RELATION sr ");
		builder.append(" WHERE c.LIST_ID = r.TASK_ID ");
		builder.append(" and t.LIST_ID = r.TYPE_ID ");
		builder.append(" and sc.LIST_ID = SPARK_ID ");
		builder.append(" and t.LIST_ID = sr.TYPE_ID ");
		Optional.ofNullable(request.getStatus()).ifPresent(s -> {
			builder.append(" and c.`STATUS` =:status");
			builder.append(" and sc.`STATUS` =:status");
			builder.append(" and t.`STATUS` =:status");
			parameters.put("status", s.getValue());
		});
		Optional.ofNullable(request.getCode()).ifPresent(s -> {
			builder.append(" and sc.`CODE`=:code");
			parameters.put("code", s);
		});
		Optional.ofNullable(request.getType()).ifPresent(s -> {
			builder.append(" and t.TYPE =:type");
			parameters.put("type", s);
		});
		Optional.ofNullable(request.getTenant()).ifPresent(tenantCode -> {
			builder.append(" and c.TENANT =:tenantCode");
			parameters.put("tenantCode", tenantCode);
		});
		return builder.toString();
	}

	@Autowired
	public void setSparkCodeRepository(SparkCodeRepository sparkCodeRepository) {
		this.sparkCodeRepository = sparkCodeRepository;
	}

	@Autowired
	public void setSparkTaskRelationRepository(SparkTaskRelationRepository sparkTaskRelationRepository) {
		this.sparkTaskRelationRepository = sparkTaskRelationRepository;
	}

	@Autowired
	public void setSparkRelationRepository(SparkRelationRepository sparkRelationRepository) {
		this.sparkRelationRepository = sparkRelationRepository;
	}

	@Autowired
	public void setSparkTypeRepository(SparkTypeRepository sparkTypeRepository) {
		this.sparkTypeRepository = sparkTypeRepository;
	}

	@Autowired
	public void setTaskCodeRepository(TaskCodeRepository taskCodeRepository) {
		this.taskCodeRepository = taskCodeRepository;
	}

	@Resource(name = "mvcConversionService")
	public void setMvcConversionService(ConversionService mvcConversionService) {
		this.mvcConversionService = mvcConversionService;
	}
}
