/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.domain.type;


/**
 * @author : YY
 * @date : 2025/12/2
 * @descprition :
 *
 */

public enum ConditionMode {
	SparkCode(1),
	Type(1<<1),
	TaskCode(1<<2);

	private final int code;
	private ConditionMode(int code){
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public boolean isMode(int mode){
		return this.code == (this.code & mode);
	}
}
