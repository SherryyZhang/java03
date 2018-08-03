package com.day1.concurrentutil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) {

		ExecutorService es =Executors.newCachedThreadPool();
		//ֻ��5���߳�ͬ�·���
		//Semaphore���Կ���ϵͳ��������ʹ��acquire��release.
		final Semaphore semp = new Semaphore(5);
		//20���ͻ��˷���
		for(int i = 0; i<20; i++) {
			final int No = i;
			Runnable run = new Runnable() {
				
				@Override
				public void run() {
					
					try {
						semp.acquire();
						System.out.println("accessing--"+No);
						Thread.sleep(2000);
						semp.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			es.submit(run);
		}		
		es.shutdown();
	}

}
