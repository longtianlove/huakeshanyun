package com.stys.ipfs.threadpool;

import javax.annotation.PreDestroy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程管理器 给成单例全局使用
 */
public class HandlerThreadsPool { 

    public ExecutorService  executorService;
    
    private static HandlerThreadsPool threadsPool;
    
    private HandlerThreadsPool() {
    	if(ThreadsPoolConfig.isCustom()){
            BlockingQueue<Runnable> queue=null;
            if(ThreadsPoolConfig.getWorkQueue()>0){
                queue=new LinkedBlockingQueue<Runnable>(ThreadsPoolConfig.getWorkQueue()); // 一般使用 LinkedBlockingQueue 队列
            }else{
                queue=new LinkedBlockingQueue<Runnable>();
            }
            // 配置线程池信息
            this.executorService=new ThreadPoolExecutor(
            		ThreadsPoolConfig.getCorePoolSize(), 
            		ThreadsPoolConfig.getMaximumPoolSize(), 
            		ThreadsPoolConfig.getKeepAliveTime(), 
                    TimeUnit.SECONDS, 
                    queue);
        }else{
            this.executorService = Executors.newCachedThreadPool();
        }
    }
    
    public static HandlerThreadsPool getInstance() {
		if(threadsPool == null) {
			synchronized(HandlerThreadsPool.class) {
				if(threadsPool == null) {
					threadsPool = new HandlerThreadsPool();
				}
			}
		}
		return threadsPool;
	}

     /*
　　　* 创建线程，对线程处理事件
     */
    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }
    
        
    /*
　　 * 对象销毁时，销毁线程
　　 */
    @PreDestroy
    public void stop() {
        executorService.shutdown();
    }
    
    public boolean awaitTermination(long timeout,TimeUnit unit){
		try {
			return executorService.awaitTermination(timeout, unit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
    }
    
}