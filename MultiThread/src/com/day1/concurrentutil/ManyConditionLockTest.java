package com.day1.concurrentutil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ManyConditionLockTest {

	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	public void method1() {
		try {
			lock.lock();
			System.out.println("lock1");
			Thread.sleep(1000);
			System.out.println("wait 1");
			condition1.await();
			System.out.println("go on 1");	
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
			System.out.println("await2");
			condition1.await();//object await()
			System.out.println("go on 2");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void method3() {
		try {
			lock.lock();
			System.out.println("lock3");
			System.out.println("await3");
			condition2.await();//object await()
			System.out.println("go on 3");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void method4() {
		try {
			lock.lock();
			System.out.println("lock4");
			Thread.sleep(2000);
			condition1.signalAll();
			System.out.println("signal all 4");	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void method5() {
		try {
			lock.lock();
			System.out.println("lock5");
			Thread.sleep(1000);
	 		condition2.signal();
			System.out.println("signal all 5");	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	
	public static void main(String[] args) {

		final ManyConditionLockTest t = new ManyConditionLockTest();
		
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
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.method3();
			}
		},"t3");
		
		Thread t4 = new Thread(new Runnable() {
	
			@Override
			public void run() {
				t.method4();
			}
		},"t4");
		
		Thread t5 = new Thread(new Runnable() {
		
			@Override
			public void run() {
				t.method5();
			}
		},"t5");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}

}
