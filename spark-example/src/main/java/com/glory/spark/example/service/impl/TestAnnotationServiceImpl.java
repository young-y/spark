/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.service.impl;


import com.glory.spark.core.annotation.Spark;
import com.glory.spark.core.annotation.SparkCondition;
import com.glory.spark.core.annotation.SparkOccasion;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.launcher.SparkLauncher;
import com.glory.spark.example.domain.JacksonObject;
import com.glory.spark.example.domain.Person;
import com.glory.spark.example.domain.SparkObject;
import com.glory.spark.example.domain.Work;
import com.glory.spark.example.service.TestAnnotationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2025/11/27
 * @descprition :
 *
 */
@Component
public class TestAnnotationServiceImpl implements TestAnnotationService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SparkLauncher launcher;
	/**
	 * @param person
	 * @return
	 */
	@Override
	@Spark(sparkCode ="test1127",type = "test",taskCode = "test1127",conditionClass = TestSparkCondition.class,occasion = SparkOccasion.After,catchException = true)
	public Person savePerson(Person person) {
		person.setMobile("110110110");
		logger.info(">> test save person...");
//		throw new RuntimeException("TEST");
		return person;
	}

	@Override
	public JacksonObject dynamicFieldsTest(JacksonObject object){
		SparkContext context = SparkContext.create(object.getName());
		context.setType("test");
		context.setTaskCode("test1127");
		Person person = new Person();
		person.setNationality("hh");
		person.setName("test");
		person.setMobile("13242545");
		context.setContext(person);
		Work work = new Work();
		work.setBalance(2000);
		work.setName("test1");
		context.addProperty("work",work);
		launcher.transmit(context);
		return object;
	}

	@Override
	public SparkObject transmit(SparkObject obj) {
		SparkContext context = SparkContext.create(obj.getCode());
		context.setType(obj.getType());
		context.setTaskCode(obj.getTaskCode());
		context.setContext(obj.getPerson());
		context.addProperty("work",obj.getWork());
		launcher.transmit(context);
		return obj;
	}
}
