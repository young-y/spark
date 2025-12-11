/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.context;


/**
 * @author : YY
 * @date : 2025/11/6
 * @descprition :
 *
 */

public interface SparkConstant {
    String PROPERTY_PREFIX = "spark";
    String EXCEPTION_PREFIX = PROPERTY_PREFIX+".exception";
    String E_PREFIX_STRATEGY = "strategy";
    String SPARK_HTTP_HOST = "spark.http.host.";
    Integer MISS_DEFAULT_ORDER = 100;
    Integer DEFAULT_ORDER = 9999;
    String INTEGRATOR_PREFIX = PROPERTY_PREFIX+".integrator";
    String FILTER_PREFIX = PROPERTY_PREFIX+".filter";
	int PARAMETER_MODE_SPARK = 1;
	int PARAMETER_MODE_TYPE = 1<<1;
	int PARAMETER_MODE_TASK = 1<<2;

}
