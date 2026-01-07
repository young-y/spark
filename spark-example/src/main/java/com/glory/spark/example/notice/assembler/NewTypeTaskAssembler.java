/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.notice.assembler;


import com.glory.spark.core.component.assembler.TaskAssembler;

import java.util.List;

/**
 * @author : YY
 * @date : 2026/1/6
 * @descprition :
 *
 */

public interface NewTypeTaskAssembler <T,E>extends TaskAssembler<T,E> {
	@Override
	default List<String> supportTypes() {
		return List.of("NewType");
	}
}
