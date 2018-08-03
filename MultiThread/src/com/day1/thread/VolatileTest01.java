package com.day1.thread;

public class VolatileTest01 extends Thread {
	//volatile具备可见性，不具备原子性
	//AtomicBoolean具备原子性
	private boolean isRunning = true;
	private void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public void run() {
		System.out.println("进入run method ");
		while(isRunning) {
			
		}
		System.out.println("线程停止");
	}
	public static void main(String[] args) {

		VolatileTest01 rt = new VolatileTest01();
		rt.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rt.setRunning(false);
		System.out.println("is false");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(rt.isRunning);
		
	}

}
