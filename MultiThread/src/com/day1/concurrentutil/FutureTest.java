package com.day1.concurrentutil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest implements Callable<String> {
	//futureģʽ��������Ӧ���ĳ��ϣ����ϵͳ������
	private String para;
	public FutureTest(String para) {
		super();
		this.para = para;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		String result = this.para+"�������";
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		String queryStr = "query";
		//����FutureTask��������ʵ������࣬����ʵ����Callable�ӿ�
		FutureTask< String> ft = new FutureTask<String>(new FutureTest(queryStr));
		FutureTask< String> ft2 = new FutureTask<String>(new FutureTest(queryStr));
		ExecutorService es = Executors.newFixedThreadPool(2);
		//submit execute
		//submit�з���ֵ�����Խ���Callable����
		Future f = es.submit(ft);
		Future f2 = es.submit(ft2);
		System.out.println("�������---"+f.get());
		//����������
		Thread.sleep(3000);
		System.out.println("111");
		System.out.println("data---"+ft.get());
		System.out.println("data---"+ft2.get());
		System.out.println("222");
		es.shutdown();
		
	}

}
