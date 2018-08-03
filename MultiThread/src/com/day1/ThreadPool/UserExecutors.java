package com.day1.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserExecutors {

	public static void main(String[] args) {

		ExecutorService pool = Executors.newFixedThreadPool(10);
		pool = Executors.newScheduledThreadPool(10);
		
		
	}

}
