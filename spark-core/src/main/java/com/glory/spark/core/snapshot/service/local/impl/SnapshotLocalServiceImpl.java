/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.snapshot.service.local.impl;


import com.glory.data.jpa.domain.converter.DomainFactory;
import com.glory.data.jpa.domain.entity.BeanTuple;
import com.glory.foundation.context.AppContext;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.snapshot.domain.bo.SnapshotBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotDetailBo;
import com.glory.spark.core.snapshot.domain.bo.SnapshotItemBo;
import com.glory.spark.core.snapshot.domain.entity.SnapshotDetailEntity;
import com.glory.spark.core.snapshot.domain.entity.SnapshotEntity;
import com.glory.spark.core.snapshot.domain.rest.SnapshotRequest;
import com.glory.spark.core.snapshot.domain.rest.SnapshotResponse;
import com.glory.spark.core.snapshot.repository.SnapshotDetailRepository;
import com.glory.spark.core.snapshot.repository.SnapshotRepository;
import com.glory.spark.core.snapshot.service.local.SnapshotLocalService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author : YY
 * @date : 2025/11/18
 * @descprition :
 *
 */
@Service
public class SnapshotLocalServiceImpl implements SnapshotLocalService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SnapshotRepository snapshotRepository;
	@Autowired
	private SnapshotDetailRepository detailRepository;
	@PersistenceContext
	private EntityManager entityManager;
	@Resource(name = "mvcConversionService")
	private ConversionService mvcConversionService;

	/**
	 * @param snapshotId
	 * @param includeDetail
	 * @return
	 */
	@Override
	public SnapshotBo findById(@Nonnull Long snapshotId, boolean includeDetail) {
		SnapshotEntity entity = snapshotRepository.findById(snapshotId).orElse(null);
		if (null != entity) {
			if (includeDetail) {
				List<SnapshotDetailEntity> detailEntities = detailRepository.findBySnapshotId(entity.getSnapshotId());
				entity.setDetails(detailEntities);
			}
			return DomainFactory.convert(entity,SnapshotBo.class);
		}
		return null;
	}

	/**
	 * @param bo
	 * @return
	 */
	@Override
	public SnapshotBo saveAndUpdate(@Nonnull SnapshotBo bo) {
		SnapshotEntity entity = DomainFactory.convert(bo,SnapshotEntity.class);
		SnapshotEntity snapshotEntity = snapshotRepository.save(entity);
		Optional.ofNullable(entity.getDetails()).ifPresent(ds -> {
			ds.forEach(d -> {
				d.setSnapshotId(snapshotEntity.getSnapshotId());
			});
			List<SnapshotDetailEntity> entities = detailRepository.saveAll(ds);
			snapshotEntity.setDetails(entities);
		});
		return DomainFactory.convert(snapshotEntity,SnapshotBo.class);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotBo> querySnapshots(@Nonnull SnapshotRequest request) {
		SnapshotResponse<SnapshotBo> response = new SnapshotResponse<>();
		if (!StringUtils.hasLength(request.getTenant())){
			request.setTenant(AppContext.getTenantCode());
		}
		Specification<SnapshotEntity> spec = new Specification<SnapshotEntity>() {
			@Override
			public Predicate toPredicate(Root<SnapshotEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> conditions = new ArrayList<>();
				Optional.ofNullable(request.getSnapshotId()).ifPresent(s -> conditions.add(cb.equal(root.get("snapshotId"), s)));
				Optional.ofNullable(request.getAppName()).ifPresent(s -> conditions.add(cb.equal(root.get("appName"), s)));
				Optional.ofNullable(request.getSerialId()).ifPresent(s -> conditions.add(cb.equal(root.get("serialId"), s)));
				Optional.ofNullable(request.getSparkCode()).ifPresent(s -> conditions.add(cb.like(root.get("sparkCode"), s + "%")));
				Optional.ofNullable(request.getSource()).ifPresent(s -> conditions.add(cb.equal(root.get("source"), s.getValue())));
				Optional.ofNullable(request.getTenant()).ifPresent(s -> conditions.add(cb.equal(root.get("tenant"), s)));
				if (CollectionUtils.isEmpty(request.getStatuses())) {
					Optional.ofNullable(request.getStatus()).ifPresent(s -> conditions.add(cb.equal(root.get("status"), s.getValue())));
				} else {
					CriteriaBuilder.In<Object> status = cb.in(root.get("status"));
					request.getStatuses().forEach(status::value);
					conditions.add(status);
				}
				Optional.ofNullable(request.getStartTime()).ifPresent(s -> conditions.add(cb.greaterThanOrEqualTo(root.get("processDate"), s)));
				Optional.ofNullable(request.getEndTime()).ifPresent(s -> conditions.add(cb.lessThan(root.get("processDate"), s)));
				return query.where(conditions.toArray(Predicate[]::new)).getRestriction();
			}
		};
		if (null != request.getPage()) {
			Page<SnapshotEntity> entityPage = snapshotRepository.findAll(spec, request.getPage().createPageRequest());
			response.initPage(request.getPage());
			response.getPage().setTotalElements(entityPage.getTotalElements());
			response.getPage().setTotalPages(entityPage.getTotalPages());
			response.getPage().setNext(entityPage.hasNext());
			response.setElements(entityPage.getContent().stream().map(e-> DomainFactory.convert(e,SnapshotBo.class)).toList());
		} else {
			List<SnapshotEntity> entities = snapshotRepository.findAll(spec);
			response.setElements(entities.stream().map(e-> DomainFactory.convert(e,SnapshotBo.class)).toList());
		}
		return response;
	}

	/**
	 * @param detailId
	 * @return
	 */
	@Override
	public SnapshotDetailBo findByDetailId(@Nonnull Long detailId) {
		SnapshotDetailEntity entity = detailRepository.findById(detailId).orElse(null);
		if (null != entity) {
			return DomainFactory.convert(entity,SnapshotDetailBo.class);
		}
		return null;
	}

	/**
	 * @param snapshotId
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotDetailBo> findBySnapshotId(@Nonnull Long snapshotId) {
		SnapshotResponse<SnapshotDetailBo> response = new SnapshotResponse<>();
		detailRepository.findBySnapshotId(snapshotId).forEach(d -> {
			response.addElement(DomainFactory.convert(d,SnapshotDetailBo.class));
		});
		return response;
	}

	/**
	 * @param taskCode
	 * @param status
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotDetailBo> findByTaskCodeAndStatus(@Nonnull String taskCode, @Nonnull TaskStatus status) {
		SnapshotResponse<SnapshotDetailBo> response = new SnapshotResponse<>();
		detailRepository.findByTaskCodeAndStatus(taskCode, status).forEach(d -> {
			response.addElement(DomainFactory.convert(d,SnapshotDetailBo.class));
		});
		return response;
	}

	/**
	 * @param detailBo
	 * @return
	 */
	@Override
	public SnapshotDetailBo saveAndUpdate(@Nonnull SnapshotDetailBo detailBo) {
		SnapshotDetailEntity entity = DomainFactory.convert(detailBo,SnapshotDetailEntity.class);
		SnapshotDetailEntity detailEntity = detailRepository.saveAndFlush(entity);
		return DomainFactory.convert(detailEntity,SnapshotDetailBo.class);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SnapshotResponse<SnapshotDetailBo> queryDetails(@Nonnull SnapshotRequest request) {
		SnapshotResponse<SnapshotDetailBo> response = new SnapshotResponse<>();
		if (!StringUtils.hasLength(request.getTenant())){
			request.setTenant(AppContext.getTenantCode());
		}
		Specification<SnapshotDetailEntity> spec = new Specification<>() {
			@Override
			public Predicate toPredicate(Root<SnapshotDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> conditions = new ArrayList<>();
				Optional.ofNullable(request.getSnapshotId()).ifPresent(s -> conditions.add(cb.equal(root.get("snapshotId"), s)));
				Optional.ofNullable(request.getDetailId()).ifPresent(s -> conditions.add(cb.equal(root.get("detailId"), s)));
				Optional.ofNullable(request.getSerialId()).ifPresent(s -> conditions.add(cb.equal(root.get("traceId"), s)));
				Optional.ofNullable(request.getSparkCode()).ifPresent(s -> conditions.add(cb.like(root.get("sparkCode"), s + "%")));
				Optional.ofNullable(request.getTaskCode()).ifPresent(s -> conditions.add(cb.like(root.get("taskCode"), s + "%")));
				Optional.ofNullable(request.getTenant()).ifPresent(s -> conditions.add(cb.like(root.get("tenant"), s )));
				Optional.ofNullable(request.getType()).ifPresent(s -> conditions.add(cb.equal(root.get("type"), s)));
				Optional.ofNullable(request.getSource()).ifPresent(s -> conditions.add(cb.equal(root.get("source"), s.getValue())));
				if (CollectionUtils.isEmpty(request.getStatuses())) {
					Optional.ofNullable(request.getStatus()).ifPresent(s -> conditions.add(cb.equal(root.get("status"), s.getValue())));
				} else {
					CriteriaBuilder.In<Object> status = cb.in(root.get("status"));
					request.getStatuses().forEach(status::value);
					conditions.add(status);
				}
				Optional.ofNullable(request.getStrategy()).ifPresent(s -> conditions.add(cb.equal(root.get("strategy"), s.getValue())));
				Optional.ofNullable(request.getStartTime()).ifPresent(s -> conditions.add(cb.greaterThanOrEqualTo(root.get("modifiedTime"), s)));
				Optional.ofNullable(request.getEndTime()).ifPresent(s -> conditions.add(cb.lessThan(root.get("modifiedTime"), s)));
				return query.where(conditions.toArray(Predicate[]::new)).getRestriction();
			}
		};
		if (null != request.getPage()) {
			Page<SnapshotDetailEntity> entityPage = detailRepository.findAll(spec, request.getPage().createPageRequest());
			response.initPage(request.getPage());
			response.setElements(entityPage.getContent().stream().map(e-> DomainFactory.convert(e,SnapshotDetailBo.class)).toList());
			response.getPage().setNext(entityPage.hasNext());
			response.getPage().setTotalPages(entityPage.getTotalPages());
			response.getPage().setTotalElements(entityPage.getTotalElements());
		} else {
			detailRepository.findAll(spec).forEach(d -> {
				response.addElement(DomainFactory.convert(d,SnapshotDetailBo.class));
			});
		}
		return response;
	}

	/**
	 * @param request
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public SnapshotResponse<SnapshotItemBo> querySnapshotDetails(@Nonnull SnapshotRequest request) {
		SnapshotResponse<SnapshotItemBo> response = new SnapshotResponse<>();
		Map<String, Object> parameters = new HashMap<>();
		String sql = buildQuerySql(request, parameters);
		logger.info("SQL: {}",sql);
		NativeQueryImpl query = entityManager.createNativeQuery(sql, SnapshotItemBo.class).unwrap(NativeQueryImpl.class);
		query.setTupleTransformer((values,aliases)->{
			BeanTuple beanTuple = new BeanTuple(values,aliases);
			beanTuple.setConversionService(mvcConversionService);
			return beanTuple.convert(SnapshotItemBo.class);
		});

		if (!CollectionUtils.isEmpty(parameters)) {
			parameters.forEach(query::setParameter);
		}
		if (null != request.getPage()) {
			response.initPage(response.getPage());
			int number = request.getPage().getNumber();
			int size = request.getPage().getSize();
			query.setFirstResult(number * size);
			query.setMaxResults(size);
		}
		List<SnapshotItemBo> resultList = query.getResultList();
		response.setElements(resultList);
		return response;
	}

	private String buildQuerySql(SnapshotRequest request, Map<String, Object> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT s.spark_code sparkCode,s.app_name appName,s.context_path contextPath,s.process_date processDate," +
				"d.type,d.detail_id detailId,d.task_code taskCode, s.snapshot_id snapshotId");
		builder.append(" ,d.source,d.status,d.strategy ,d.last_retry_time lastRetryTime,d.related_id relatedId,d.message," +
				"d.trace_id traceId,d.target_id targetId");
		builder.append(" from T_SPARK_SNAPSHOT s, T_SPARK_SNAPSHOT_DETAIL d ");
		builder.append(" WHERE s.snapshot_id = d.snapshot_id ");
		Optional.ofNullable(request.getAppName()).ifPresent(s -> {
			builder.append(" and s.app_name =:appName");
			parameters.put("appName", s);
		});
		Optional.ofNullable(request.getSparkCode()).ifPresent(s -> {
			builder.append(" and s.spark_code = :sparkCode");
			parameters.put("sparkCode", s);
		});
		Optional.ofNullable(request.getTaskCode()).ifPresent(s -> {
			builder.append(" and d.task_code =:task_code");
			parameters.put("taskCode", s);
		});
		Optional.ofNullable(request.getSnapshotId()).ifPresent(s -> {
			builder.append(" and d.snapshot_id =:snapshotId");
			parameters.put("snapshotId", s);
		});
		Optional.ofNullable(request.getDetailId()).ifPresent(s -> {
			builder.append(" and d.detail_id =:detail_id");
			parameters.put("detailId", s);
		});
		Optional.ofNullable(request.getType()).ifPresent(s -> {
			builder.append(" and d.type =:type");
			parameters.put("type", s);
		});
		Optional.ofNullable(request.getSource()).ifPresent(s -> {
			builder.append(" and d.source =:source");
			parameters.put("source", s.getValue());
		});
		Optional.ofNullable(request.getStatuses()).ifPresent(s -> {
			builder.append(" and d.status in :statuses");
			parameters.put("statuses", s);
		});
		if (CollectionUtils.isEmpty(request.getStatuses())) {
			Optional.ofNullable(request.getStatus()).ifPresent(s -> {
				builder.append(" and d.status =:status");
				parameters.put("status", s.getValue());
			});
		}
		Optional.ofNullable(request.getStrategy()).ifPresent(s -> {
			builder.append(" and d.strategy =:strategy");
			parameters.put("strategy", s.getValue());
		});
		Optional.ofNullable(request.getStartTime()).ifPresent(s->{
			builder.append(" and s.process_date  >= :startTime");
			parameters.put("startTime",s);
		});
		Optional.ofNullable(request.getEndTime()).ifPresent(s->{
			builder.append(" and s.process_date < :endTime");
			parameters.put("endTime",s);
		});
		return builder.toString();
	}
}
