package com.day1.concurrentutil;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReentrantReadWriteLockTest {
//¶Á¹²Ïí£¬Ð´»¥³â
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private ReadLock rl = lock.readLock();
	private WriteLock wl = lock.writeLock();
	public void read() {
		try {
			rl.lock();
			System.out.println("read begin.."+Thread.currentThread().getName());
			Thread.sleep(3000);
			System.out.println("read over.."+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rl.unlock();
		}
	}
	
	public void write() {
		try {
			wl.lock();
			System.out.println("write begin.."+Thread.currentThread().getName());
			Thread.sleep(3000);
			System.out.println("write over.."+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			wl.unlock();
		}
	}
	
	
	public static void main(String[] args) {

		final ReentrantReadWriteLockTest t = new ReentrantReadWriteLockTest();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.read();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.write();
			}
		},"t2");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.read();
			}
		},"t3");
		
		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.write();
			}
		},"t4");
		
		t1.start();
		t3.start();

		t2.start();
		t4.start();
		
	}

}
