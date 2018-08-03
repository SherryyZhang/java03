package com.day1.thread;

public class ThreadLocalTest {
//使用空间换时间
	public static ThreadLocal<String> th = new ThreadLocal<String>();
	public void setTh(String value) {
		th.set(value);
	}
	public void getTH() {
		System.out.println(Thread.currentThread().getName()+"   "+this.th.get());
	}
	 
	public static void main(String[] args) {
 
		final ThreadLocalTest ct = new ThreadLocalTest();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {

				ct.setTh("zhang");
				ct.getTH();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					ct.getTH();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		}, "t2");
		t1.start();
		t2.start();
	}

}
