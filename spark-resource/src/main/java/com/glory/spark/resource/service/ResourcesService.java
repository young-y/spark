/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.resource.service;


import com.glory.spark.resource.domain.bo.*;

import java.util.List;

/**
 * @author : YY
 * @date : 2026/1/12
 * @descprition :
 *
 */

public interface ResourcesService {
	SparkCodeDefinitionBo saveSparkCodeDefinition(SparkCodeDefinitionBo sparkCodeDefinitionBo);
	SparkCodeDefinitionBo updateSparkCodeDefinition(SparkCodeDefinitionBo sparkCodeDefinitionBo);
	void deleteSparkCodeDefinition(Long listId,boolean relation);
	SparkTypeDefinitionBo saveSparkTypeDefinition(SparkTypeDefinitionBo sparkTypeDefinitionBo);
	SparkTypeDefinitionBo updateSparkTypeDefinition(SparkTypeDefinitionBo sparkTypeDefinitionBo);
	void deleteSparkTypeDefinition(Long ListId,boolean relation);
	TaskCodeDefinitionBo saveTaskCodeDefinition(TaskCodeDefinitionBo taskCodeDefinitionBo);
	TaskCodeDefinitionBo updateTaskCodeDefinition(TaskCodeDefinitionBo taskCodeDefinitionBo);
	void deleteTaskCodeDefinition(Long listId ,boolean relation);
	SparkRelationBo saveSparkRelation(SparkRelationBo sparkRelationBo);

	void deleteSparkRelation(Long listId);
	SparkTaskRelationBo saveSparkTaskRelation(SparkTaskRelationBo sparkTaskRelationBo);

	void deleteSparkTaskRelation(Long listId);

	List<SparkTypeDescBo> findSparkTypes(ResourceRequest request);
	List<SparkTaskDescBo> findTaskCodes(ResourceRequest request);
	List<SparkTaskDescBo> findSparkTasks(ResourceRequest request);
}
