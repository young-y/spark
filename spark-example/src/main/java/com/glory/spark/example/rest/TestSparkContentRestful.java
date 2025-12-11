/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.rest;


import com.glory.spark.content.domain.bo.*;
import com.glory.spark.content.service.local.SparkContentLocalService;
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
@RequestMapping("/test/content")
public class TestSparkContentRestful {
	private SparkContentLocalService localService;

	@PostMapping("/save")
	public SparkContentBo saveAndUpdate(@RequestBody SparkContentBo contentBo) {
		return localService.saveAndUpdate(contentBo);
	}

	@GetMapping("/find/{listId}")
	public SparkContentBo findById(@PathVariable("listId") Long listId) {
		return localService.findById(listId);
	}

	@GetMapping("/findBySparkCode/{sparkCode}/{status}")
	public List<SparkContentBo> findBySparkCode(@PathVariable("sparkCode") String sparkCode,@PathVariable("status") Integer status) {
		return localService.findBySparkCode(sparkCode, status);
	}

	@GetMapping("/findByTaskCode/{taskCode}/{status}")
	public List<SparkContentBo> findByTaskCode(@PathVariable("taskCode") String taskCode, @PathVariable("status") Integer status) {
		return localService.findByTaskCode(taskCode, status);
	}

	@PostMapping("/query")
	public SparkContentResponse<SparkContentBo> queryContents(@RequestBody SparkContentRequest request) {
		return localService.queryContents(request);
	}

	@PostMapping("/track/save")
	public SparkContentTrackBo saveAndUpdate(SparkContentTrackBo trackBo) {
		return localService.saveAndUpdate(trackBo);
	}

	@GetMapping("/track/find/{trackId}")
	public SparkContentTrackBo findByTrackId(Long trackId) {
		return localService.findByTrackId(trackId);
	}

	@GetMapping("/track/find/{contentId}/{status}")
	public List<SparkContentTrackBo> findByContentId(Long contentId, Integer status) {
		return localService.findByContentId(contentId, status);
	}

	@PostMapping("/track/query")
	public SparkContentResponse<SparkContentTrackBo> queryTracks(SparkContentTrackRequest request) {
		return localService.queryTracks(request);
	}


	@Autowired
	public void setLocalService(SparkContentLocalService localService) {
		this.localService = localService;
	}
}
