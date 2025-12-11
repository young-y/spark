/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.compensate.rest;


import com.glory.spark.core.compensate.model.CompensateItem;
import com.glory.spark.core.compensate.model.CompensateItemRequest;
import com.glory.spark.core.compensate.model.CompensateRequest;
import com.glory.spark.core.compensate.model.CompensateResponse;
import com.glory.spark.core.compensate.service.route.CompensateProcessRouteService;
import com.glory.spark.core.compensate.service.route.CompensateTransmitRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : YY
 * @date : 2025/12/2
 * @descprition :
 *
 */
@RestController
@RequestMapping("compensate")
public class CompensateRestful {

	private CompensateProcessRouteService processRouteService;
	private CompensateTransmitRouteService transmitRouteService;

	/**
	 * Initiate the compensation operation
	 * @param request
	 * @return
	 */
	@PostMapping("/transmit")
	public CompensateResponse transmit(@RequestBody CompensateRequest request){
		return transmitRouteService.transmit(request);
	}

	/**
	 * batch compensation
	 * @param request
	 * @return
	 */
	@PostMapping("/batch/process")
	public CompensateResponse process(@RequestBody CompensateItemRequest request){
		return processRouteService.process(request);
	}

	/**
	 * single compensation
	 * @param item
	 * @return
	 */
	@PostMapping("/item/process")
	public CompensateResponse process(@RequestBody CompensateItem item){
		return processRouteService.process(item);
	}

	@Autowired
	public void setProcessRouteService(CompensateProcessRouteService processRouteService) {
		this.processRouteService = processRouteService;
	}

	@Autowired
	public void setTransmitRouteService(CompensateTransmitRouteService transmitRouteService) {
		this.transmitRouteService = transmitRouteService;
	}
}
