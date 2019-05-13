package com.stys.ipfs.threadpool;





/**
 * 线程池 配置
 */
public class ThreadsPoolConfig {

    /**
     * 是否开启自定义线程池
     */
    private static final  boolean  custom=true;
    /**
     * 核心线程数
     */
    private static final int corePoolSize=10;
    /**
     * 线程池最大线程数
     */
    private static final int maximumPoolSize=10;
    /**
     * 空闲线程存活时间（对核心线程无效）
     */
    private static final long keepAliveTime=100;
    /**
     * 任务队列大小，0时为无界队列
     */
    private static final int workQueue=0;
    

	public static boolean isCustom() {
		return custom;
	}

	public static int getCorePoolSize() {
		return corePoolSize;
	}

	public static int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public static long getKeepAliveTime() {
		return keepAliveTime;
	}

	public static int getWorkQueue() {
		return workQueue;
	}

    
}