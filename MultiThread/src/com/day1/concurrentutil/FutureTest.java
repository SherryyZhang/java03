package com.day1.concurrentutil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest implements Callable<String> {
	//future模式适用于响应慢的场合，提高系统吞吐量
	private String para;
	public FutureTest(String para) {
		super();
		this.para = para;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		String result = this.para+"处理完成";
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		String queryStr = "query";
		//构建FutureTask，传入真实处理的类，该类实现了Callable接口
		FutureTask< String> ft = new FutureTask<String>(new FutureTest(queryStr));
		FutureTask< String> ft2 = new FutureTask<String>(new FutureTest(queryStr));
		ExecutorService es = Executors.newFixedThreadPool(2);
		//submit execute
		//submit有返回值，可以接受Callable对象
		Future f = es.submit(ft);
		Future f2 = es.submit(ft2);
		System.out.println("请求完毕---"+f.get());
		//处理别的事情
		Thread.sleep(3000);
		System.out.println("111");
		System.out.println("data---"+ft.get());
		System.out.println("data---"+ft2.get());
		System.out.println("222");
		es.shutdown();
		
	}

}
