/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.rest;


import com.glory.spark.example.domain.JacksonObject;
import com.glory.spark.example.domain.Person;
import com.glory.spark.example.domain.SparkObject;
import com.glory.spark.example.service.TestAnnotationService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : YY
 * @date : 2025/11/27
 * @descprition :
 *
 */
@RestController
@RequestMapping("/annotation")
public class SparkAnnotationTestRestful {

	@Autowired
	private TestAnnotationService service;
	@PostMapping("save")
	public Person savePerson(@RequestBody Person person){
		return service.savePerson(person);
	}
	@PostMapping("dynamicTest")
	public JacksonObject testDynamicFields(@RequestBody JacksonObject object){
		return service.dynamicFieldsTest(object);
	}

	@PostMapping("/transmit")
	public SparkObject testTransmit(@RequestBody SparkObject object){
		return service.transmit(object);
	}
}
