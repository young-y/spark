/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.component.controller.impl;


import com.glory.spark.core.component.loader.SparkTaskGenerator;
import com.glory.spark.core.context.SparkContext;
import com.glory.spark.core.delegate.assembler.AssemblerDelegate;
import com.glory.spark.core.delegate.filter.TaskFilterDelegate;
import com.glory.spark.core.delegate.integrator.IntegratorDelegate;
import com.glory.spark.core.delegate.loader.LoaderDelegate;
import com.glory.spark.core.delegate.storage.StorageDelegate;
import com.glory.spark.core.domain.SparkResult;
import com.glory.spark.core.domain.SparkTaskDesc;
import com.glory.data.jpa.domain.type.ResultStatus;
import com.glory.spark.core.domain.type.TaskStatus;
import com.glory.spark.core.exception.ExceptionManager;
import com.glory.spark.core.snapshot.listener.SnapshotListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author : YY
 * @date : 2025/10/30
 * @descprition :
 *
 */
public class DefaultTypeController extends AbstractTypeController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private LoaderDelegate loaderDelegate;
    private TaskFilterDelegate taskFilterDelegate;
    private AssemblerDelegate assemblerDelegate;
    private StorageDelegate storageDelegate;
    private IntegratorDelegate integratorDelegate;
    private ExceptionManager manager;
    private List<SnapshotListener> snapshotListeners;
	private SparkTaskGenerator taskGenerator;
    @Override
    public <T, E> SparkResult<E> process(SparkContext<T> context) {
        List<SparkTaskDesc> list = loadSparkTasks(context);//load task information by sparkCode and type
        SparkResult<E> result = SparkResult.Success(context);
        if (!CollectionUtils.isEmpty(list)){
            for (SparkTaskDesc task:list){
                if (task.isSync()){//sync task
                    doProcess(task, result);
                }else {//async task,ignored result
                    CompletableFuture.runAsync(()->  doProcess(task,result),executor);
                }
            }
        }else {
            logger.debug("Spark Type[{}] load task is empty.",context.getType());
        }
        return result;
    }

    /**
     *
     * @param task  task description
     * @param result type result
     * @param <T>  spark context parameter type
     * @param <E>  result element type
     */
    private <T, E> void doProcess(SparkTaskDesc task, SparkResult<E> result) {
        SparkContext<T> taskContext = task.copy();//clone a task level context
        try{
            if(taskFilterDelegate.filter(taskContext)){//task filter,eg:effective date,compensate status.
                snapshotListener().captureTask(taskContext);//add a snapshot detail log
                SparkResult<E> taskResult = assemblerDelegate.assemble(taskContext);//assemble format data
                if (null != taskResult && ResultStatus.Success == taskResult.getStatus()){//assemble success
                    taskResult.setContext(taskContext);
                    storageDelegate.stored(taskResult);//storage data
					task.updateDetailStatus(TaskStatus.Success,null);
                    try {
                        integratorDelegate.run(taskResult);//handle integration
                    } catch (Exception e) {
                        logger.warn("Spark Integrator {} has exception:", task.identity(),e);
                        taskResult.addMessage(e.getMessage());
                    }
                    result.updateResult(taskResult);
                }else {// set result status is success  and log message if assemble result is null or fail
					task.updateDetailStatus(TaskStatus.Success,String.format("Task[%s] result is null.", task.identity()));
                }
            }
        }catch (Exception e){
            logger.warn("process {} has exception:", task.identity(),e);
			task.updateDetailStatus(TaskStatus.Fail,e.getMessage());
            manager.handle(taskContext,e);
            result.setStatus(ResultStatus.Fail);
        }finally {
			snapshotListener().updateTask(task);
		}
    }

	private <T> List<SparkTaskDesc> loadSparkTasks(SparkContext<T> context){
		if (!StringUtils.hasLength(context.getTaskCode())){
			//if the taskCode has already been specified,there is no need load again.
			return loaderDelegate.load(context);
		}
		return taskGenerator.generate(context);
	}

	private SnapshotListener snapshotListener(){
		return snapshotListeners.getFirst();
	}
	@Autowired
	public void setSnapshotListeners(List<SnapshotListener> snapshotListeners) {
		this.snapshotListeners = snapshotListeners;
	}

	@Autowired
    public void setManager(ExceptionManager manager) {
        this.manager = manager;
    }

    @Autowired
    public void setIntegratorDelegate(IntegratorDelegate integratorDelegate) {
        this.integratorDelegate = integratorDelegate;
    }
    @Autowired
    public void setLoaderDelegate(LoaderDelegate loaderDelegate) {
        this.loaderDelegate = loaderDelegate;
    }
    @Autowired
    public void setTaskFilterDelegate(TaskFilterDelegate taskFilterDelegate) {
        this.taskFilterDelegate = taskFilterDelegate;
    }
    @Autowired
    public void setAssemblerDelegate(AssemblerDelegate assemblerDelegate) {
        this.assemblerDelegate = assemblerDelegate;
    }

    @Autowired
    public void setStorageDelegate(StorageDelegate storageDelegate) {
        this.storageDelegate = storageDelegate;
    }

	@Autowired
	public void setTaskGenerator(SparkTaskGenerator taskGenerator) {
		this.taskGenerator = taskGenerator;
	}
}
