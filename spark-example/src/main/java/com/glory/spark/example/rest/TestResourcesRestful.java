/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.rest;


import com.glory.spark.resource.domain.bo.*;
import com.glory.spark.resource.service.local.ResourcesLocalService;
import com.glory.spark.resource.service.route.ResourcesRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : YY
 * @date : 2026/1/12
 * @descprition :
 *
 */
@RestController
@RequestMapping("/test/resources")
public class TestResourcesRestful {

	private ResourcesLocalService service;
	/**
	 * @param sparkCodeDefinitionBo
	 * @return
	 */
	@PostMapping("/sparkCode/save")
	@Transactional
	public SparkCodeDefinitionBo saveSparkCodeDefinition(@RequestBody SparkCodeDefinitionBo sparkCodeDefinitionBo) {
		return service.saveSparkCodeDefinition(sparkCodeDefinitionBo);
	}

	/**
	 * @param sparkCodeDefinitionBo
	 * @return
	 */
	@PostMapping("/sparkCode/update")
	@Transactional
	public SparkCodeDefinitionBo updateSparkCodeDefinition(@RequestBody SparkCodeDefinitionBo sparkCodeDefinitionBo) {
		return service.updateSparkCodeDefinition(sparkCodeDefinitionBo);
	}

	/**
	 * @param listId
	 * @return
	 */
	@PostMapping("/sparkCode/delete/{listId}/{relation}")
	@Transactional
	public void deleteSparkCodeDefinition(@PathVariable("listId") Long listId,@PathVariable("relation") boolean relation) {
		 service.deleteSparkCodeDefinition(listId,relation);
	}

	/**
	 * @param sparkTypeDefinitionBo
	 * @return
	 */
	@PostMapping("/sparkType/save")
	@Transactional
	public SparkTypeDefinitionBo saveSparkTypeDefinition(@RequestBody SparkTypeDefinitionBo sparkTypeDefinitionBo) {
		return service.saveSparkTypeDefinition(sparkTypeDefinitionBo);
	}

	/**
	 * @param sparkTypeDefinitionBo
	 * @return
	 */
	@PostMapping("/sparkType/update")
	@Transactional
	public SparkTypeDefinitionBo updateSparkTypeDefinition(@RequestBody SparkTypeDefinitionBo sparkTypeDefinitionBo) {
		return service.updateSparkTypeDefinition(sparkTypeDefinitionBo);
	}

	/**
	 * @param listId
	 * @return
	 */
	@PostMapping("/sparkType/delete/{listId}/{relation}")
	@Transactional
	public void deleteSparkTypeDefinition(@PathVariable("listId") Long listId,@PathVariable("relation") boolean relation) {
		 service.deleteSparkTypeDefinition(listId,relation);
	}

	/**
	 * @param taskCodeDefinitionBo
	 * @return
	 */
	@PostMapping("/taskCode/save")
	@Transactional
	public TaskCodeDefinitionBo saveTaskCodeDefinition(@RequestBody TaskCodeDefinitionBo taskCodeDefinitionBo) {
		return service.saveTaskCodeDefinition(taskCodeDefinitionBo);
	}

	/**
	 * @param taskCodeDefinitionBo
	 * @return
	 */
	@PostMapping("/taskCode/update")
	@Transactional
	public TaskCodeDefinitionBo updateTaskCodeDefinition(@RequestBody TaskCodeDefinitionBo taskCodeDefinitionBo) {
		return service.updateTaskCodeDefinition(taskCodeDefinitionBo);
	}

	/**
	 * @param listId
	 * @return
	 */
	@PostMapping("/taskCode/delete/{listId}/{relation}")
	@Transactional
	public void deleteTaskCodeDefinition(@PathVariable("listId") Long listId,@PathVariable("relation") boolean relation) {
		 service.deleteTaskCodeDefinition(listId,relation);
	}

	/**
	 * @param sparkRelationBo
	 * @return
	 */
	@PostMapping("/sparkRelation/save")
	@Transactional
	public SparkRelationBo saveSparkRelation(@RequestBody SparkRelationBo sparkRelationBo) {
		return service.saveSparkRelation(sparkRelationBo);
	}

	/**
	 * @param ListId
	 * @return
	 */
	@PostMapping("/sparkRelation/delete/{listId}")
	@Transactional
	public void deleteSparkRelation(@PathVariable("listId") Long ListId) {
		 service.deleteSparkRelation(ListId);
	}

	/**
	 * @param sparkTaskRelationBo
	 * @return
	 */
	@PostMapping("/taskRelation/save")
	@Transactional
	public SparkTaskRelationBo saveSparkTaskRelation(@RequestBody SparkTaskRelationBo sparkTaskRelationBo) {
		return service.saveSparkTaskRelation(sparkTaskRelationBo);
	}

	/**
	 * @param ListId
	 * @return
	 */
	@PostMapping("/taskRelation/delete/{listId}")
	@Transactional
	public void deleteSparkTaskRelation(@PathVariable("listId") Long ListId) {
		 service.deleteSparkTaskRelation(ListId);
	}

	/**
	 * @param request
	 * @return
	 */
	@PostMapping("/sparkTypes/find")
	public List<SparkTypeDescBo> findSparkTypes(@RequestBody ResourceRequest request) {
		return service.findSparkTypes(request);
	}

	/**
	 * @param request
	 * @return
	 */
	@PostMapping("/taskCodes/find")
	public List<SparkTaskDescBo> findTaskCodes(@RequestBody ResourceRequest request) {
		return service.findTaskCodes(request);
	}

	@PostMapping("/sparkTasks/find")
	public List<SparkTaskDescBo> findSparkTasks(@RequestBody ResourceRequest request) {
		return service.findSparkTasks(request);
	}

	@Autowired
	public void setService(ResourcesLocalService service) {
		this.service = service;
	}
}
