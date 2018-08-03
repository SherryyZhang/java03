package com.day1.ThreadPool;

public class MyTask implements Runnable {
	private int taskID;
	private String taskName;


	public MyTask(int taskID, String taskName) {
		super();
		this.taskID = taskID;
		this.taskName = taskName;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public void run() {

		try {
			System.out.println("run---"+this.taskID);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
