package com.day1.thread;

public class VolatileTest {
	public static void main(String[] args) {

		ObjLock o = new ObjLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				o.changeV();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				o.changeV();
			}
		});
		t1.start();
		t2.start();
	}

}
class ObjLock {
	//volatile变量在多个线程间可见
	volatile int v;
	public void changeV()  {
		v++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(v);
	}
}
