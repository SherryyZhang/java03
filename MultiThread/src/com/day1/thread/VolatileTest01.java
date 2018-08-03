package com.day1.thread;

public class VolatileTest01 extends Thread {
	//volatile�߱��ɼ��ԣ����߱�ԭ����
	//AtomicBoolean�߱�ԭ����
	private boolean isRunning = true;
	private void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public void run() {
		System.out.println("����run method ");
		while(isRunning) {
			
		}
		System.out.println("�߳�ֹͣ");
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
