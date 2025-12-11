/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.example.notice.loadder;


import com.glory.spark.core.component.loader.TaskLoader;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.spark.core.domain.SparkTypeDesc;
import com.glory.spark.example.notice.NoticeConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YY
 * @date : 2025/11/5
 * @descprition :
 *
 */
@Component
public class NoticeTaskLoader implements TaskLoader {
    @Override
    public <T> List<SparkTaskDesc> load(SparkContext<T> context) {
        return List.of(generate(context.getTypeDesc(),0),generate(context.getTypeDesc(),1));
    }

    private SparkTaskDesc generate(SparkTypeDesc typeDesc,int index){
        SparkTaskDesc taskDesc = new SparkTaskDesc();
//        BeanUtils.copyProperties(typeDesc,taskDesc);
        taskDesc.setTaskCode("notice_task_"+index);
        taskDesc.setTaskName("notice task");
		taskDesc.setSparkCode(typeDesc.getSparkCode());
		taskDesc.setType(typeDesc.getType());
		taskDesc.setContext(typeDesc.getContext());
        return taskDesc;
    }

    @Override
    public List<String> supportTypes() {
        return List.of(NoticeConfig.NOTICE_TYPE);
    }
}
