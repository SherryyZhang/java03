package com.day1.concurrentutil;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	static class Runner implements Runnable {

		private String name;
		private CyclicBarrier cb;
		
		public Runner(String name, CyclicBarrier cb) {
			super();
			this.name = name;
			this.cb = cb;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(1000*new Random().nextInt(5));
				System.out.println(name +"start---");	
				//几次await()，往下走
				//多个线程等待
				cb.await();
				System.out.println(name +"go---");	
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {

		CyclicBarrier cb = new CyclicBarrier(3);
		ExecutorService es = Executors.newFixedThreadPool(3);
		es.submit(new Runner("1", cb));
		es.submit(new Runner("2", cb));
		es.submit(new Runner("3", cb));
		es.shutdown();
	}

}
