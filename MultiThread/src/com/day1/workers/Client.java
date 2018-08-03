package com.day1.workers;

public class Client {

	public static void main(String[] args) {

		Master master = new Master(new Worker(), 10);
		for(int i = 0; i<=100; i++) {
			Task task = new Task(Integer.toString(i), Integer.toString(i), i);
			master.submit(task);
		}
		master.execute();
	}

}
 