/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.content.rest;


import com.glory.spark.content.domain.bo.*;
import com.glory.spark.content.service.route.SparkContentRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/12/8
 * @descprition :
 *
 */
@RestController
@RequestMapping("content")
public class SparkContentRestful {
	private SparkContentRouteService routeService;

	@PostMapping("/save")
	public SparkContentBo saveAndUpdate(@RequestBody SparkContentBo contentBo) {
		return routeService.saveAndUpdate(contentBo);
	}

	@GetMapping("/find/{listId}")
	public SparkContentBo findById(@PathVariable("listId") Long listId) {
		return routeService.findById(listId);
	}

	@GetMapping("/findBySparkCode/{sparkCode}/{status}")
	public List<SparkContentBo> findBySparkCode(@PathVariable("sparkCode") String sparkCode,@PathVariable("status") Integer status) {
		return routeService.findBySparkCode(sparkCode, status);
	}

	@GetMapping("/findByTaskCode/{taskCode}/{status}")
	public List<SparkContentBo> findByTaskCode(@PathVariable("taskCode") String taskCode, @PathVariable("status") Integer status) {
		return routeService.findByTaskCode(taskCode, status);
	}

	@PostMapping("/query")
	public SparkContentResponse<SparkContentBo> queryContents(@RequestBody SparkContentRequest request) {
		return routeService.queryContents(request);
	}

	@PostMapping("/track/save")
	public SparkContentTrackBo saveAndUpdate(SparkContentTrackBo trackBo) {
		return routeService.saveAndUpdate(trackBo);
	}

	@GetMapping("/track/find/{trackId}")
	public SparkContentTrackBo findByTrackId(Long trackId) {
		return routeService.findByTrackId(trackId);
	}

	@GetMapping("/track/find/{contentId}/{status}")
	public List<SparkContentTrackBo> findByContentId(Long contentId, Integer status) {
		return routeService.findByContentId(contentId, status);
	}

	@PostMapping("/track/query")
	public SparkContentResponse<SparkContentTrackBo> queryTracks(SparkContentTrackRequest request) {
		return routeService.queryTracks(request);
	}


	@Autowired
	public void setRouteService(SparkContentRouteService routeService) {
		this.routeService = routeService;
	}
}
