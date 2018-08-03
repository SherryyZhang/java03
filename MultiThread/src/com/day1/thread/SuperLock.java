package com.day1.thread;

public class SuperLock {

	public static void main(String[] args) {
		Sub s = new Sub();
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				s.oprateionSup();
			}
		});
		t.start();	
	}
	
	static class Main {
		public int i = 10;
		public synchronized void oprateionSup() {
			try {
				i--;
				System.out.println("Main print i = "+i);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
	}
	static class Sub extends Main {
		public synchronized void oprateionSup() {
			try {
				while(i > 0) {
					i--;
					System.out.println("Sub print i = "+i);
					Thread.sleep(99);
					super.oprateionSup();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
	}

}
