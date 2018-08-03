package com.day1.workers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

	private ConcurrentLinkedQueue<Task> workerQueue;
	private ConcurrentHashMap<String, Object> resultMap;

	@Override
	public void run() {
		while(true) {
			Task input = this.workerQueue.poll();
			if(input==null) {
				break;
			}
			//result
			Object output = handler(input);
			System.out.println(Thread.currentThread().getName()+"---"+input.getName());
			resultMap.put(input.getId(), output);
		}
	}

	private Object handler(Task input) {
		Object output = null;
		try {
			Thread.sleep(1000);
			output = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	public void setWorkerQueue(ConcurrentLinkedQueue<Task> workerQueue) {
		this.workerQueue = workerQueue;
	}

	public void setWorkerResult(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
