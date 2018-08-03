package com.day1.thread;
//脏读
//未加锁的方法可以单独执行
public class ReadError {

	private String username = "z";
	private String password = "p";
	public synchronized void setValue(String username,String password) {
		this.username = username;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.password = password;
		System.out.println("setValue,username = "+username+",password = "+password);
	}
	public void getValue() {
		System.out.println("getValue,username = "+username+",password = "+password);
	}
	public static void main(String[] args) throws InterruptedException {

		final ReadError t = new ReadError();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.setValue("1", "1");
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.setValue("2", "2");
			}
		});
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t.getValue();
		
	}

}
 