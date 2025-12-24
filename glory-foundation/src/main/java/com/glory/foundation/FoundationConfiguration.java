/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.foundation;


import com.glory.foundation.context.AppContext;
import com.glory.foundation.desensitize.condition.DesensitizePredicate;
import com.glory.foundation.formater.DateFormater;
import com.glory.foundation.formater.GloryDateFormater;
import com.glory.foundation.formater.GloryObjectFormater;
import com.glory.foundation.formater.ObjectFormater;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : YY
 * @date : 2025/11/27
 * @descprition :
 *
 */
@Configuration
@ComponentScan
public class FoundationConfiguration {

	public static final String DESENSITIZE_ACTIVITY = "DESENSITIZE_ACTIVITY";

	@Bean
	@ConditionalOnMissingBean
	public ObjectFormater objectFormater(){
		return new GloryObjectFormater();
	}

	@Bean
	@ConditionalOnMissingBean
	public DateFormater dateFormater(){
		return new GloryDateFormater();
	}

	@Bean
	@ConditionalOnMissingBean
	public DesensitizePredicate desensitizePredicate(){
		return ()-> AppContext.get(DESENSITIZE_ACTIVITY, true);
	}
}
