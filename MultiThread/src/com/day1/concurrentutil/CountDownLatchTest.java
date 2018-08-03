package com.day1.concurrentutil;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) {
		
		final CountDownLatch countDown = new CountDownLatch(2);
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("t1 await---");
					//��������ֵ����0ʱ������ʾ���е��߳��Ѿ����������Ȼ���ڱ����ϵȴ����߳̾Ϳ��Իָ�ִ������
					//һ���̵߳ȴ��������̷߳���֪ͨ
					countDown.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("t2 init---");
					Thread.sleep(3000);
					System.out.println("wait 3 seconds---");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
	
			@Override
			public void run() {
				try {
					System.out.println("t3 init---");
					Thread.sleep(4000);
					System.out.println("wait 4 seconds---");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		t1.start();
		t2.start();
		t3.start();
	}

}
