/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.domain.type;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;

/**
 * @author : YY
 * @date : 2025/10/28
 * @descprition :
 *
 */

public enum ExceptionStrategy implements PersistableEnum<Integer>{
    Panic("panic",1)//Throw new exception for SparkException
    ,Capture("capture",2)  //try-catch and log it
    ,Try_Handle("try-handle",3)  //try and handle it
    ;

    private final String name;
    private final Integer value;

    private ExceptionStrategy(String name,Integer value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }


    public Integer getValue() {
        return value;
    }

    @Override
    @JsonValue
    public Integer value() {
        return getValue();
    }

    @JsonCreator
    public static  ExceptionStrategy fromPersistableValue(int value){
        return PersistableEnum.valueOf(value,ExceptionStrategy.class);
    }

    public static class ExceptionStrategyConverter implements Converter<Integer,ExceptionStrategy>{

        @Override
        public ExceptionStrategy convert(@Nonnull Integer source) {
            return ExceptionStrategy.fromPersistableValue(source);
        }
    }
}
