package com.day1.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThread01 {

	public static void main(String[] args) {

		Temp t = new Temp();
		ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
		ScheduledFuture<?> f = s.scheduleWithFixedDelay(t, 0, 3, TimeUnit.SECONDS);
	}

}
class Temp extends Thread {
	public void run() {
		System.out.println("run");
	}
}
