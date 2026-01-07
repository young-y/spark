/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.notice.controller;


import com.glory.spark.core.component.controller.TypeController;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkResult;

import java.util.List;

/**
 * @author : YY
 * @date : 2026/1/5
 * @descprition :
 *
 */

public class NewTypeController implements TypeController {
	/**
	 * @param context
	 * @param <T>
	 * @param <E>
	 * @return
	 */
	@Override
	public <T, E> SparkResult<E> process(SparkContext<T> context) {
		return null;
	}

	/**
	 * @param context
	 * @param <T>
	 */
	@Override
	public <T> void asyncProcess(SparkContext<T> context) {

	}

	/**
	 * @return
	 */
	@Override
	public List<String> supportTypes() {
		return List.of("NewType");
	}
}
