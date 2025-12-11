/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.rest;


import com.glory.spark.core.compensate.model.CompensateItem;
import com.glory.spark.core.compensate.model.CompensateRequest;
import com.glory.spark.core.compensate.model.CompensateResponse;
import com.glory.spark.core.compensate.service.local.CompensateProcessLocalService;
import com.glory.spark.core.compensate.service.local.CompensateTransmitLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : YY
 * @date : 2025/12/3
 * @descprition :
 *
 */
@RestController
@RequestMapping("/test/compensate")
public class SparkCompensateRestful {
	@Autowired
	private CompensateTransmitLocalService transmitLocalService;
	@Autowired
	private CompensateProcessLocalService processLocalService;
	@PostMapping("/item/process")
	public CompensateResponse process(@RequestBody CompensateItem item){
		return processLocalService.process(item);
	}

	@PostMapping("/transmit")
	public CompensateResponse process(@RequestBody CompensateRequest request){
		return transmitLocalService.transmit(request);
	}

}
