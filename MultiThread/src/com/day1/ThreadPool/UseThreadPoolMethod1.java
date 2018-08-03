package com.day1.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.*;
import java.util.concurrent.TimeUnit;

public class UseThreadPoolMethod1 {

	public static void main(String[] args) {
		//无界队列线程数是第一个=参数，
		//有界队列可以用到最大线程数
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1, 
				2,
				60,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
				new CallerRunsPolicy()
				);
		MyTask m1 = new MyTask(1, "task1");
		MyTask m2 = new MyTask(2, "task2");
		MyTask m3 = new MyTask(3, "task3");
		MyTask m4 = new MyTask(4, "task4");
		MyTask m5 = new MyTask(5, "task5");
		MyTask m6 = new MyTask(6, "task6");
		//pool---2(1,5)
		//queue--3(2,3,4)
		//rejected(6)
		pool.execute(m1);
		pool.execute(m2);
		pool.execute(m3);
		pool.execute(m4);
		pool.execute(m5);
		//jdk局决策略，抛出异常，继续运行
		//callerRunsPolicy直接调用被丢弃的线程
		//DiscardOldestPolicy 丢弃最老的请求
		//DiscardPolicy 丢弃不处理
		pool.execute(m6);
		pool.shutdown();
		
		
	}

}
