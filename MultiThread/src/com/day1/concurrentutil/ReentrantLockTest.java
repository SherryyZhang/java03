package com.day1.concurrentutil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void method1() {
		try {
			lock.lock();
			System.out.println("lock1");
			Thread.sleep(3000);
			condition.signal();
			System.out.println("signal1");	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void method2() {
		try {
			lock.lock();
			System.out.println("lock2");
			Thread.sleep(3000);
			System.out.println("await2");
			condition .await();//object await()
			System.out.println("go on 2");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {

		final ReentrantLockTest t = new ReentrantLockTest();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.method1();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.method2();
			}
		},"t2");
		
		t2.start();
		t1.start();
		
	}

}
