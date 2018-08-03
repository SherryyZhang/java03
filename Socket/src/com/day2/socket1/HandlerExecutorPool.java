package com.day2.socket1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerExecutorPool {

	private ExecutorService es;
	public HandlerExecutorPool(int i, int j) {
		this.es = new ThreadPoolExecutor(
				Runtime.getRuntime().availableProcessors(),
				i, 120L,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(j));
		
	}

	public void execute(Runnable task) {
		this.es.execute(task);
	}

}
