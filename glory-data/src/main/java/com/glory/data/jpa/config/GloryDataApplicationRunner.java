/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.data.jpa.config;


import com.glory.data.jpa.domain.type.PersistableEnumConverterFactory;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.stereotype.Component;

/**
 * @author : YY
 * @date : 2026/1/15
 * @descprition :
 *
 */
@Component
public class GloryDataApplicationRunner implements ApplicationRunner {
	@Resource(name = "mvcConversionService")
	private ConversionService mvcConversionService;
	/**
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (this.mvcConversionService instanceof ConverterRegistry){
			((ConverterRegistry) this.mvcConversionService).addConverterFactory(new PersistableEnumConverterFactory());
			((ConverterRegistry) this.mvcConversionService).addConverter(new Converter<Integer, Boolean>() {
				@Override
				public Boolean convert(@Nonnull Integer source) {
					return source == 1;
				}
			});
		}
	}
}
