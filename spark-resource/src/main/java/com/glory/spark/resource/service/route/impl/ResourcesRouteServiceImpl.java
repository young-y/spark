/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.service.route.impl;


import com.glory.http.client.service.router.AbstractRouteService;
import com.glory.spark.resource.domain.bo.*;
import com.glory.spark.resource.helper.ResourceHelper;
import com.glory.spark.resource.service.ResourcesService;
import com.glory.spark.resource.service.route.ResourcesRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YY
 * @date : 2026/1/12
 * @descprition :
 *
 */

@Service
public class ResourcesRouteServiceImpl extends AbstractRouteService<ResourcesService> implements ResourcesRouteService {
	private ResourceHelper helper;
	/**
	 * @param targetAppName
	 * @return
	 */
	@Override
	protected boolean isLocalRouter(String targetAppName) {
		return helper .isContentApp(targetAppName);
	}

	/**
	 * @return
	 */
	@Override
	public String targetAppName() {
		return helper.getAppName();
	}

	/**
	 * @param sparkCodeDefinitionBo
	 * @return
	 */
	@Override
	public SparkCodeDefinitionBo saveSparkCodeDefinition(SparkCodeDefinitionBo sparkCodeDefinitionBo) {
		return select().saveSparkCodeDefinition( sparkCodeDefinitionBo);
	}

	/**
	 * @param sparkCodeDefinitionBo
	 * @return
	 */
	@Override
	public SparkCodeDefinitionBo updateSparkCodeDefinition(SparkCodeDefinitionBo sparkCodeDefinitionBo) {
		return select().updateSparkCodeDefinition( sparkCodeDefinitionBo);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkCodeDefinition(Long listId, boolean deleteRelation) {
		 select().deleteSparkCodeDefinition(listId,  deleteRelation);
	}

	/**
	 * @param sparkTypeDefinitionBo
	 * @return
	 */
	@Override
	public SparkTypeDefinitionBo saveSparkTypeDefinition(SparkTypeDefinitionBo sparkTypeDefinitionBo) {
		return select().saveSparkTypeDefinition(  sparkTypeDefinitionBo);
	}

	/**
	 * @param sparkTypeDefinitionBo
	 * @return
	 */
	@Override
	public SparkTypeDefinitionBo updateSparkTypeDefinition(SparkTypeDefinitionBo sparkTypeDefinitionBo) {
		return select().updateSparkTypeDefinition( sparkTypeDefinitionBo);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkTypeDefinition(Long listId,  boolean deleteRelation) {
		 select().deleteSparkTypeDefinition(  listId,   deleteRelation);
	}

	/**
	 * @param taskCodeDefinitionBo
	 * @return
	 */
	@Override
	public TaskCodeDefinitionBo saveTaskCodeDefinition(TaskCodeDefinitionBo taskCodeDefinitionBo) {
		return select().saveTaskCodeDefinition( taskCodeDefinitionBo);
	}

	/**
	 * @param taskCodeDefinitionBo
	 * @return
	 */
	@Override
	public TaskCodeDefinitionBo updateTaskCodeDefinition(TaskCodeDefinitionBo taskCodeDefinitionBo) {
		return select().updateTaskCodeDefinition( taskCodeDefinitionBo);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteTaskCodeDefinition(Long listId, boolean deleteRelation) {
		 select().deleteTaskCodeDefinition(listId,  deleteRelation);
	}

	/**
	 * @param sparkRelationBo
	 * @return
	 */
	@Override
	public SparkRelationBo saveSparkRelation(SparkRelationBo sparkRelationBo) {
		return select().saveSparkRelation(  sparkRelationBo);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkRelation(Long listId) {
		 select().deleteSparkRelation(listId);
	}

	/**
	 * @param sparkTaskRelationBo
	 * @return
	 */
	@Override
	public SparkTaskRelationBo saveSparkTaskRelation(SparkTaskRelationBo sparkTaskRelationBo) {
		return select().saveSparkTaskRelation(  sparkTaskRelationBo);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkTaskRelation(Long listId) {
		 select().deleteSparkTaskRelation( listId);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public List<SparkTypeDescBo> findSparkTypes(ResourceRequest request) {
		return select().findSparkTypes( request);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public List<SparkTaskDescBo> findTaskCodes(ResourceRequest request) {
		return select().findTaskCodes( request);
	}

	@Override
	public List<SparkTaskDescBo> findSparkTasks(ResourceRequest request) {
		return select().findSparkTasks(request);
	}

	@Autowired
	public void setHelper(ResourceHelper helper) {
		this.helper = helper;
	}
}
