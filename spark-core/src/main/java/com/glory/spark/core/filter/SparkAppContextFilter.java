/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.filter;


import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author : YY
 * @date : 2025/11/9
 * @descprition :
 *
 */

public class SparkAppContextFilter implements Filter {

	private SparkUserContextInitializer initializer;

	@Override
	public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		initializer.init(request);
		filterChain.doFilter(request, servletResponse);
	}

	@Autowired
	public void setInitializer(SparkUserContextInitializer initializer) {
		this.initializer = initializer;
	}
}
