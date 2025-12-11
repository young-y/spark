/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.delegate.base;


import com.glory.spark.core.component.base.Selector;
import com.glory.spark.core.context.SparkContext;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;
import org.springframework.util.comparator.Comparators;

import java.lang.reflect.ParameterizedType;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.glory.spark.core.context.SparkConstant.MISS_DEFAULT_ORDER;

/**
 * @author : YY
 * @date : 2025/10/29
 * @descprition :
 *
 */

public abstract class AbstractDelegate<S extends Selector>  implements ApplicationContextAware, ApplicationRunner, SparkDelegate<S> {

    protected List<S> targets;
    protected ApplicationContext applicationContext;
    protected Comparator<S> comparator=(o1, o2)->{
        Order od1= o1.getClass().getAnnotation(Order.class);
        Order od2= o2.getClass().getAnnotation(Order.class);
        int s1= null!= od1?od1.value(): MISS_DEFAULT_ORDER;
        int s2= null!= od2?od2.value(): MISS_DEFAULT_ORDER;
        return Comparators.comparable().compare(s1,s2);
};

    @SuppressWarnings("unchecked")
    protected Class<S> getActualType(){
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<S>) type.getActualTypeArguments()[0];
    }

    protected BiFunction<List<S>,SparkContext<?>,List<S>> getSelector(){
        return (ts,context)-> ts.stream().filter(t->t.match(context)).toList();
    }

    protected Supplier<S> getDefaultSelector() {
        return ()->targets.getLast();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        targets = applicationContext.getBeansOfType(getActualType()).values().stream().sorted(comparator).toList();
    }

    @Override
    public S delegate(SparkContext<?> context) {
        Assert.hasLength(context.getSparkCode(),"Spark Code is null.");
        S target =getSelector().apply(targets,context).getFirst();
        if (null == target){
            target = getDefaultSelector().get();
        }
        return target;
    }

    @Override
    public List<S> delegates(SparkContext<?> context) {
        return getSelector().apply(targets,context);
    }
}
