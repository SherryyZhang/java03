package com.day1.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.*;
import java.util.concurrent.TimeUnit;

public class UseThreadPoolMethod1 {

	public static void main(String[] args) {
		//�޽�����߳����ǵ�һ��=������
		//�н���п����õ�����߳���
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
		//jdk�־����ԣ��׳��쳣����������
		//callerRunsPolicyֱ�ӵ��ñ��������߳�
		//DiscardOldestPolicy �������ϵ�����
		//DiscardPolicy ����������
		pool.execute(m6);
		pool.shutdown();
		
		
	}

}
