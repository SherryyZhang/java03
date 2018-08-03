package com.day1.thread;

import java.sql.Time;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

	//1，一个集合
	private final LinkedList<Object> list = new LinkedList<Object>();
	//2，计数器
	private AtomicInteger count = new AtomicInteger(0);
	//3，上下限
	private int minSize = 0;
	private int maxSize;
	//构造方法
	public MyQueue(int size) {
		this.maxSize = size;
	}
	//初始化一个对象，用于加锁
	//CountDownLatch可以释放锁，不需要synchronized关键字
	private final Object lock = new Object();
	//put
	public void put(Object obj) {
		synchronized (lock) {
			while (count.get() >= maxSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
			count.incrementAndGet();
			System.out.println("put it in list ---"+obj);
			lock.notify();
		}
	}
	
	//take
	public Object take() {
		Object o = null;
		synchronized(lock) {
			while (count.get()==this.minSize) {
				try {
					lock.wait();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}		
			}
			o = list.removeFirst();
			count.decrementAndGet();
			lock.notify();
		}
		return o;
		
	}
	public int getCount() {
		return count.get();
	}
	public static void main(String[] args) {

		MyQueue mq = new MyQueue(5);
		mq.put("1");
		mq.put("2");
		mq.put("3");
		mq.put("4");
		mq.put("5");
		System.out.println(mq.getCount());
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				mq.put("6");
				mq.put("7");
			}
		},"t1");
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Object o1 = mq.take();
				System.out.println("remove---"+o1);
				Object o2 = mq.take();
				System.out.println("remove---"+o2);
			}
		},"t2");
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}

}
