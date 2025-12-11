/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.notice.assembler;


import com.glory.foundation.context.AppContext;
import com.glory.spark.content.domain.bo.SparkContentBo;
import com.glory.spark.core.component.assembler.TaskAssembler;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkResult;
import com.glory.spark.example.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/27
 * @descprition :
 *
 */

@Component
public class TestPersonTaskAssembler2 implements TaskAssembler<Person, SparkContentBo> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @param sparkContext
	 * @return
	 */
	@Override
	public SparkResult<SparkContentBo> assemble(SparkContext<Person> sparkContext) {
		logger.info(">> Person test Assembler {}", sparkContext.getContext());
		SparkContentBo contentBo = new SparkContentBo();
		contentBo.setName("test 2case");
		contentBo.setCategory(0);
		contentBo.setDescription("this is second test case");
		contentBo.setModule(1);
		contentBo.setReceivers(List.of("test user"));
		contentBo.setSerialId(sparkContext.getSerialId());
		contentBo.setSource(0);
		contentBo.setSparkCode(sparkContext.getSparkCode());
		contentBo.setTenant(AppContext.getTenantCode());
		contentBo.setStatus(0);
		contentBo.setTaskCode(sparkContext.getTaskCode());
		contentBo.setType(sparkContext.getTypeDesc().getType());
		Person person = sparkContext.getContext();
		contentBo.addContent("name", person.getName());
		contentBo.addContent("age", person.getAge());
		contentBo.addContent("mobile", person.getMobile());
		contentBo.addContent("nationality", person.getNationality());
		SparkResult<SparkContentBo> result = SparkResult.Success(sparkContext);
		result.addElement(contentBo);
		throw  new RuntimeException("TEst exception");
//		return result;
	}

	@Override
	public List<String> supportSparkCodes() {
		return List.of("testSpark");
	}


	@Override
	public List<String> supportTypes() {
		return List.of("test");
	}
}
