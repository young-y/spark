/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.service.local.impl;


import com.glory.data.jpa.domain.converter.DomainFactory;
import com.glory.http.client.service.impl.AbstractClientService;
import com.glory.spark.content.domain.bo.*;
import com.glory.spark.content.domain.entity.SparkContentEntity;
import com.glory.spark.content.domain.entity.SparkContentTrackEntity;
import com.glory.spark.content.repository.SparkContentRepository;
import com.glory.spark.content.repository.SparkContentTrackRepository;
import com.glory.spark.content.service.local.SparkContentLocalService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/12/8
 * @descprition :
 *
 */
@Service
public class SparkContentLocalServiceImpl extends AbstractClientService implements SparkContentLocalService {

	private SparkContentRepository repository;
	private SparkContentTrackRepository trackRepository;
	/**
	 * @param contentBo
	 * @return
	 */
	@Override
	public SparkContentBo saveAndUpdate(SparkContentBo contentBo) {
		SparkContentEntity entity = DomainFactory.convert(contentBo, SparkContentEntity.class);
		SparkContentEntity newEntity = repository.saveAndFlush(entity);
		SparkContentBo sparkContentBo = DomainFactory.convert(newEntity, SparkContentBo.class);
		Optional.ofNullable(contentBo.getTracks()).ifPresent(bo->{
			SparkContentTrackEntity trackEntity = DomainFactory.convert(bo, SparkContentTrackEntity.class);
			if (null == trackEntity.getContentId()){
				trackEntity.setContentId(newEntity.getListId());
			}
			SparkContentTrackEntity newTrackEntity = trackRepository.saveAndFlush(trackEntity);
			sparkContentBo.addTrack(DomainFactory.convert(newTrackEntity,SparkContentTrackBo.class));
		});
		return sparkContentBo;
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public SparkContentBo findById(Long listId) {
		SparkContentEntity entity = repository.findById(listId).orElseThrow(()->new RuntimeException("Can't find content by id "+listId));
		return DomainFactory.convert(entity,SparkContentBo.class);
	}

	/**
	 * @param sparkCode
	 * @param status
	 * @return
	 */
	@Override
	public List<SparkContentBo> findBySparkCode(String sparkCode, Integer status) {
		List<SparkContentEntity> entities = repository.findBySparkCodeAndStatus(sparkCode, status);
		return entities.stream().map(e-> DomainFactory.convert(e,SparkContentBo.class)).toList();
	}

	/**
	 * @param taskCode
	 * @param status
	 * @return
	 */
	@Override
	public List<SparkContentBo> findByTaskCode(String taskCode, Integer status) {
		List<SparkContentEntity> entities = repository.findByTaskCodeAndStatus(taskCode, status);
		return entities.stream().map(e-> DomainFactory.convert(e,SparkContentBo.class)).toList();
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SparkContentResponse<SparkContentBo> queryContents(SparkContentRequest request) {
		SparkContentResponse<SparkContentBo> response = new SparkContentResponse<>();
		Specification<SparkContentEntity> spec = new Specification<SparkContentEntity>() {
			@Override
			public Predicate toPredicate(Root<SparkContentEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> conditions = new ArrayList<>();
				Optional.ofNullable(request.getListId()).ifPresent(s -> conditions.add(cb.equal(root.get("listId"), s)));
				Optional.ofNullable(request.getSparkCode()).ifPresent(s -> conditions.add(cb.like(root.get("sparkCode"), s + "%")));
				Optional.ofNullable(request.getType()).ifPresent(s -> conditions.add(cb.equal(root.get("type"), s)));
				Optional.ofNullable(request.getTaskCode()).ifPresent(s -> conditions.add(cb.equal(root.get("taskCode"), s)));
				Optional.ofNullable(request.getTenant()).ifPresent(s -> conditions.add(cb.equal(root.get("tenant"), s)));
				Optional.ofNullable(request.getTemplateCode()).ifPresent(s -> conditions.add(cb.equal(root.get("templateCode"), s)));
				Optional.ofNullable(request.getSerialId()).ifPresent(s -> conditions.add(cb.equal(root.get("serialId"), s)));
				Optional.ofNullable(request.getSource()).ifPresent(s -> conditions.add(cb.equal(root.get("source"), s)));
				if (CollectionUtils.isEmpty(request.getStatuses())) {
					Optional.ofNullable(request.getStatus()).ifPresent(s -> conditions.add(cb.equal(root.get("status"), s)));
				} else {
					CriteriaBuilder.In<Object> status = cb.in(root.get("status"));
					request.getStatuses().forEach(status::value);
					conditions.add(status);
				}
				if (CollectionUtils.isEmpty(request.getModules())){
					Optional.ofNullable(request.getModule()).ifPresent(s -> conditions.add(cb.equal(root.get("module"), s)));
				}else {
					CriteriaBuilder.In<Object> modules = cb.in(root.get("module"));
					request.getModules().forEach(modules::value);
					conditions.add(modules);
				}
				Optional.ofNullable(request.getExpireDate()).ifPresent(s -> conditions.add(cb.lessThanOrEqualTo(root.get("expireDate"), s)));
				return query.where(conditions.toArray(Predicate[]::new)).getRestriction();
			}
		};
		if (null !=request.getPage()) {
			Page<SparkContentEntity> entityPage = repository.findAll(spec, request.getPage().createPageRequest());
			response.initPage(request.getPage());
			response.setElements(entityPage.getContent().stream().map(e-> DomainFactory.convert(e,SparkContentBo.class)).toList());
			response.getPage().setTotalPages(entityPage.getTotalPages());
			response.getPage().setTotalElements(entityPage.getTotalElements());
			response.getPage().setNumber(entityPage.getNumber());
			response.getPage().setSize(entityPage.getSize());
		}else {
			List<SparkContentEntity> entities = repository.findAll(spec);
			response.setElements(entities.stream().map(e-> DomainFactory.convert(e,SparkContentBo.class)).toList());
		}
		return response;
	}

	/**
	 * @param trackBo
	 * @return
	 */
	@Override
	public SparkContentTrackBo saveAndUpdate(SparkContentTrackBo trackBo) {
		SparkContentTrackEntity entity = DomainFactory.convert(trackBo, SparkContentTrackEntity.class);
		SparkContentTrackEntity trackEntity = trackRepository.saveAndFlush(entity);
		return DomainFactory.convert(trackEntity,SparkContentTrackBo.class);
	}

	/**
	 * @param trackId
	 * @return
	 */
	@Override
	public SparkContentTrackBo findByTrackId(Long trackId) {
		SparkContentTrackEntity entity=trackRepository.findById(trackId).orElseThrow(()-> new RuntimeException("Can't find Track by id "+trackId));
		return DomainFactory.convert(entity,SparkContentTrackBo.class);
	}

	/**
	 * @param contentId
	 * @param status
	 * @return
	 */
	@Override
	public List<SparkContentTrackBo> findByContentId(Long contentId, Integer status) {
		List<SparkContentTrackEntity> entities = trackRepository.findByContentIdAndStatus(contentId, status);
		return entities.stream().map(e-> DomainFactory.convert(e,SparkContentTrackBo.class)).toList();
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public SparkContentResponse<SparkContentTrackBo> queryTracks(SparkContentTrackRequest request) {
		SparkContentResponse<SparkContentTrackBo> response = new SparkContentResponse<>();
		Specification<SparkContentTrackEntity> spec = new Specification<SparkContentTrackEntity>() {
			@Override
			public Predicate toPredicate(Root<SparkContentTrackEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> conditions = new ArrayList<>();
				Optional.ofNullable(request.getTrackId()).ifPresent(s -> conditions.add(cb.equal(root.get("trackId"), s)));
				Optional.ofNullable(request.getContentId()).ifPresent(s -> conditions.add(cb.equal(root.get("contentId"), s )));
				Optional.ofNullable(request.getCategory()).ifPresent(s -> conditions.add(cb.equal(root.get("category"), s)));
				Optional.ofNullable(request.getTenant()).ifPresent(s -> conditions.add(cb.equal(root.get("tenant"), s)));
				Optional.ofNullable(request.getSerialId()).ifPresent(s -> conditions.add(cb.equal(root.get("serialId"), s)));
				Optional.ofNullable(request.getSource()).ifPresent(s -> conditions.add(cb.equal(root.get("source"), s)));
				if (CollectionUtils.isEmpty(request.getStatuses())) {
					Optional.ofNullable(request.getStatus()).ifPresent(s -> conditions.add(cb.equal(root.get("status"), s)));
				} else {
					CriteriaBuilder.In<Object> status = cb.in(root.get("status"));
					request.getStatuses().forEach(status::value);
					conditions.add(status);
				}

				Optional.ofNullable(request.getTrackTime()).ifPresent(s -> conditions.add(cb.lessThanOrEqualTo(root.get("trackTime"), s)));
				return query.where(conditions.toArray(Predicate[]::new)).getRestriction();
			}
		};
		if (null !=request.getPage()) {
			Page<SparkContentTrackEntity> entityPage = trackRepository.findAll(spec, request.getPage().createPageRequest());
			response.initPage(request.getPage());
			response.setElements(entityPage.getContent().stream().map(e-> DomainFactory.convert(e,SparkContentTrackBo.class)).toList());
			response.getPage().setTotalPages(entityPage.getTotalPages());
			response.getPage().setTotalElements(entityPage.getTotalElements());
			response.getPage().setNumber(entityPage.getNumber());
			response.getPage().setSize(entityPage.getSize());
		}else {
			List<SparkContentTrackEntity> entities = trackRepository.findAll(spec);
			response.setElements(entities.stream().map(e-> DomainFactory.convert(e,SparkContentTrackBo.class)).toList());
		}
		return response;
	}

	@Autowired
	public void setRepository(SparkContentRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setTrackRepository(SparkContentTrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}
}
