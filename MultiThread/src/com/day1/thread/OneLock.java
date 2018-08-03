package com.day1.thread;
//¶ÔÏóËø
import java.lang.Thread;
public class OneLock {

	public static void main(String[] args) {

		Thread t = new TestThread();
		Thread t1 = new Thread(t,"t1");
		Thread t2 = new Thread(t,"t2");
		Thread t3 = new Thread(t,"t3");
		Thread t4 = new Thread(t,"t4");
		Thread t5 = new Thread(t,"t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
	
	

}
class  TestThread extends Thread{
	private static int i =0;
	//synchronized¼ÏËø
	@Override
	public synchronized void run() {
		i++;
		System.out.println("get");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(TestThread.currentThread().getName()+"count is ---"+i);
	}
	
}
