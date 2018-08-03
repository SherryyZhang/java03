package com.day1.thread;
//��������������
public class MultiLock {
//static��Ϊ�༶�����
	private static int num =0;
	public synchronized void printNum(String tag) {
		try {
			if ("a".equals(tag)) {
				num = 100;				
				System.out.println("tag "+tag+" is set to 100");
				Thread.sleep(1000);
			} else {
				num = 200;
				System.out.println("tag "+tag+" is set to 200");
			}
			System.out.println("tag "+tag+"  num is ----"+num);
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {

		//��������
		final MultiLock m1 = new MultiLock();
		final MultiLock m2 = new MultiLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				m1.printNum("a");
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				m2.printNum("b");
			}
		});
		
		t1.start();
		t2.start();
		
		
		
	}

}
