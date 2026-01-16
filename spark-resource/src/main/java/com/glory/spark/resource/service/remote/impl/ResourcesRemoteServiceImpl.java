/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.service.remote.impl;


import com.glory.http.client.service.impl.AbstractClientService;
import com.glory.http.client.service.wrapper.HttpRequestWrapper;
import com.glory.http.client.service.wrapper.UrlWrapper;
import com.glory.spark.resource.domain.bo.*;
import com.glory.spark.resource.helper.ResourceHelper;
import com.glory.spark.resource.service.remote.ResourcesRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YY
 * @date : 2026/1/12
 * @descprition :
 *
 */
@Service
public class ResourcesRemoteServiceImpl extends AbstractClientService implements ResourcesRemoteService {
	private static  final String SPARK_HTTP_HOST = "spark.http.host.";
	private ResourceHelper helper;
	// sparkCode
	@Value("${spark.http.resource.save.spark-code:/resources/sparkCode/save}")
	private String saveSparkCodeDefinition;
	@Value("${spark.http.resource.update.spark-code:/resources/sparkCode/update}")
	private String updateSparkCodeDefinition;
	@Value("${spark.http.resource.delete.spark-code:/resources/sparkCode/delete/{listId}/{relation}}")
	private String deleteSparkCodeDefinition;
	// query
	@Value("${spark.http.resource.find.spark-types:/resources/sparkTypes/find}")
	private String findSparkTypes;
	@Value("${spark.http.resource.find.task-codes:/resources/taskCodes/find}")
	private String findTaskCodes;
	@Value("${spark.http.resource.find.task-codes:/resources/sparkCodes/find}")
	private String findSparkTasks;
	//sparkType
	@Value("${spark.http.resource.save.spark-type:/resources/sparkType/save}")
	private String saveSparkTypeDefinition;
	@Value("${spark.http.resource.update.spark-type:/resources/sparkType/update}")
	private String updateSparkTypeDefinition;
	@Value("${spark.http.resource.delete.spark-type:/resources/sparkType/delete/{listId}/{relation}}")
	private String deleteSparkTypeDefinition;
	//taskCode
	@Value("${spark.http.resource.save.task-code:/resources/taskCode/save}")
	private String saveTaskCodeDefinition;
	@Value("${spark.http.resource.update.task-code:/resources/taskCode/update}")
	private String updateTaskCodeDefinition;
	@Value("${spark.http.resource.delete.task-code:/resources/taskCode/delete/{listId}/{relation}}")
	private String deleteTaskCodeDefinition;
	// spark relation
	@Value("${spark.http.resource.save.spark-relation:/resources/sparkRelation/save}")
	private String saveSparkRelation;
	@Value("${spark.http.resource.delete.spark-relation:/resources/sparkRelation/delete/{listId}}")
	private String deleteSparkRelation;
	// task relation
	@Value("${spark.http.resource.save.task-relation:/resources/taskRelation/save}")
	private String saveTaskRelation;
	@Value("${spark.http.resource.delete.task-relation:/resources/taskRelation/delete/{listId}}")
	private String deleteTaskRelation;
	/**
	 * @param sparkCodeDefinitionBo
	 * @return
	 */
	@Override
	public SparkCodeDefinitionBo saveSparkCodeDefinition(SparkCodeDefinitionBo sparkCodeDefinitionBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(saveSparkCodeDefinition);
		HttpRequestWrapper<SparkCodeDefinitionBo, SparkCodeDefinitionBo> wrapper =
				HttpRequestWrapper.create(sparkCodeDefinitionBo,SparkCodeDefinitionBo.class)
						.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	/**
	 * @param sparkCodeDefinitionBo
	 * @return
	 */
	@Override
	public SparkCodeDefinitionBo updateSparkCodeDefinition(SparkCodeDefinitionBo sparkCodeDefinitionBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(updateSparkCodeDefinition);
		HttpRequestWrapper<SparkCodeDefinitionBo, SparkCodeDefinitionBo> wrapper =
				HttpRequestWrapper.create(sparkCodeDefinitionBo,SparkCodeDefinitionBo.class)
						.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkCodeDefinition(Long listId,boolean deleteRelation) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(deleteSparkCodeDefinition)
				.addUriVariable("listId", listId)
				.addUriVariable("relation", deleteRelation);
		HttpRequestWrapper<Void, Object> wrapper =
				HttpRequestWrapper.create(null,Void.class)
						.setUrlWrapper(urlWrapper);
		 post(wrapper);
	}

	/**
	 * @param sparkTypeDefinitionBo
	 * @return
	 */
	@Override
	public SparkTypeDefinitionBo saveSparkTypeDefinition(SparkTypeDefinitionBo sparkTypeDefinitionBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(saveSparkTypeDefinition);
		HttpRequestWrapper<SparkTypeDefinitionBo, SparkTypeDefinitionBo> wrapper =
				HttpRequestWrapper.create(sparkTypeDefinitionBo,SparkTypeDefinitionBo.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}

	/**
	 * @param sparkTypeDefinitionBo
	 * @return
	 */
	@Override
	public SparkTypeDefinitionBo updateSparkTypeDefinition(SparkTypeDefinitionBo sparkTypeDefinitionBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(updateSparkTypeDefinition);
		HttpRequestWrapper<SparkTypeDefinitionBo, SparkTypeDefinitionBo> wrapper =
				HttpRequestWrapper.create(sparkTypeDefinitionBo,SparkTypeDefinitionBo.class)
				.setUrlWrapper(urlWrapper);
		return post( wrapper);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkTypeDefinition(Long listId, boolean deleteRelation) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(deleteSparkTypeDefinition)
				.addUriVariable("listId", listId)
				.addUriVariable("relation", deleteRelation);
		HttpRequestWrapper<Void, Object> wrapper =
				HttpRequestWrapper.create(null,Void.class)
				.setUrlWrapper(urlWrapper);
		 post( wrapper);
	}

	/**
	 * @param taskCodeDefinitionBo
	 * @return
	 */
	@Override
	public TaskCodeDefinitionBo saveTaskCodeDefinition(TaskCodeDefinitionBo taskCodeDefinitionBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(saveTaskCodeDefinition);
		HttpRequestWrapper<TaskCodeDefinitionBo, TaskCodeDefinitionBo> wrapper =
				HttpRequestWrapper.create(taskCodeDefinitionBo,TaskCodeDefinitionBo.class)
				.setUrlWrapper(urlWrapper);
		return post( wrapper);
	}

	/**
	 * @param taskCodeDefinitionBo
	 * @return
	 */
	@Override
	public TaskCodeDefinitionBo updateTaskCodeDefinition(TaskCodeDefinitionBo taskCodeDefinitionBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(updateTaskCodeDefinition);
		HttpRequestWrapper<TaskCodeDefinitionBo, TaskCodeDefinitionBo> wrapper =
				HttpRequestWrapper.create(taskCodeDefinitionBo,TaskCodeDefinitionBo.class)
				.setUrlWrapper(urlWrapper);
		return post( wrapper);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteTaskCodeDefinition(Long listId, boolean deleteTaskRelation) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(deleteTaskCodeDefinition)
				.addUriVariable("listId", listId)
				.addUriVariable("relation", deleteTaskRelation);
		HttpRequestWrapper<Void, Object> wrapper =
				HttpRequestWrapper.create(null,Void.class)
				.setUrlWrapper(urlWrapper);
		 post(wrapper);
	}

	/**
	 * @param sparkRelationBo
	 * @return
	 */
	@Override
	public SparkRelationBo saveSparkRelation(SparkRelationBo sparkRelationBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(saveSparkRelation);
		HttpRequestWrapper<SparkRelationBo, SparkRelationBo> wrapper =
				HttpRequestWrapper.create(sparkRelationBo,SparkRelationBo.class)
				.setUrlWrapper(urlWrapper);
		return post(wrapper);
	}


	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkRelation(Long listId) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(deleteSparkRelation)
				.addUriVariable("listId", listId);
		HttpRequestWrapper<Void, Object> wrapper =
				HttpRequestWrapper.create(null,Void.class)
				.setUrlWrapper(urlWrapper);
		 post(wrapper);
	}

	/**
	 * @param sparkTaskRelationBo
	 * @return
	 */
	@Override
	public SparkTaskRelationBo saveSparkTaskRelation(SparkTaskRelationBo sparkTaskRelationBo) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(saveTaskRelation);
		HttpRequestWrapper<SparkTaskRelationBo, SparkTaskRelationBo> wrapper =
				HttpRequestWrapper.create(sparkTaskRelationBo,SparkTaskRelationBo.class)
				.setUrlWrapper(urlWrapper);
		return post( wrapper);
	}

	/**
	 * @param listId
	 * @return
	 */
	@Override
	public void deleteSparkTaskRelation(Long listId) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(deleteTaskRelation)
				.addUriVariable("listId", listId);
		HttpRequestWrapper<Void, Object> wrapper =
				HttpRequestWrapper.create(null,Void.class)
				.setUrlWrapper(urlWrapper);
		 post( wrapper);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public List<SparkTypeDescBo> findSparkTypes(ResourceRequest request) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findSparkTypes);
		HttpRequestWrapper<List, ResourceRequest> wrapper =
				HttpRequestWrapper.create(request,List.class)
				.setUrlWrapper(urlWrapper);
		return post( wrapper);
	}

	/**
	 * @param request
	 * @return
	 */
	@Override
	public List<SparkTaskDescBo> findTaskCodes(ResourceRequest request) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findTaskCodes);
		HttpRequestWrapper<List, ResourceRequest> wrapper =
				HttpRequestWrapper.create(request,List.class)
				.setUrlWrapper(urlWrapper);
		return post( wrapper);
	}

	@Override
	public List<SparkTaskDescBo> findSparkTasks(ResourceRequest request) {
		UrlWrapper urlWrapper = UrlWrapper.createByAppName(helper.getResourceAppName())
				.setHostKey(SPARK_HTTP_HOST)
				.setUri(findSparkTasks);
		HttpRequestWrapper<List, ResourceRequest> wrapper =
				HttpRequestWrapper.create(request,List.class)
						.setUrlWrapper(urlWrapper);
		return post( wrapper);
	}

	@Autowired
	public void setHelper(ResourceHelper helper) {
		this.helper = helper;
	}
}
