package com.day1.workers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	//task
	private ConcurrentLinkedQueue<Task> workerQueue = new ConcurrentLinkedQueue<Task>();
	//workers object
	private HashMap<String, Thread> workersMap = new HashMap<String, Thread>();
	//result
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();
	//
	public Master(Worker worker,int workerCount) {
		worker.setWorkerQueue(this.workerQueue);
		worker.setWorkerResult(this.resultMap);
		for(int i = 0; i<workerCount; i++) {
			workersMap.put("worker"+i, new Thread(worker,"worker"+i));
			
		}
	}
	
	//submit
	public void submit(Task task) {
		this.workerQueue.add(task);
	}
	//start
	public void execute() {
		for(Map.Entry<String, Thread> ms : workersMap.entrySet()) {
			ms.getValue().start();
		}
	}
	
}
