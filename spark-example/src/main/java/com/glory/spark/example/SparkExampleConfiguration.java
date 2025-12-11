/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @author : YY
 * @date : 2025/11/14
 * @descprition :
 *
 */

@Configuration
public class SparkExampleConfiguration {
	@Value("${spring.jackson.date-format:yyyy-MM-dd hh:mm:ss}")
	private String dateFormat;

	@Bean
	@ConditionalOnClass(Jackson2ObjectMapperBuilder.class)
	public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(ApplicationContext context, List<Jackson2ObjectMapperBuilderCustomizer> customizerList){
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.applicationContext(context);
		//LocalDateTime
		builder.serializerByType(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateFormat)));
		builder.deserializerByType(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
		//LocalDate
		builder.serializerByType(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
		builder.deserializerByType(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
		//LocalTime
		builder.serializerByType(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(dateFormat)));
		builder.deserializerByType(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
		//Date
		builder.dateFormat(new SimpleDateFormat(dateFormat));
		//Long
		builder.serializerByType(Long.class,new ToStringSerializer());
		builder.serializerByType(Long.TYPE,new ToStringSerializer());

		builder.serializationInclusion(JsonInclude.Include.NON_NULL);

		Optional.ofNullable(customizerList).ifPresent(cs->{
			cs.forEach(c-> c.customize(builder));
		});
		return builder;
	}

	@Bean
	@ConditionalOnMissingClass
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.setConnectTimeout(Duration.ofSeconds(5)).
				setReadTimeout(Duration.ofSeconds(30))
				.build();
	}

}
