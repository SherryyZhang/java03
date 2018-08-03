package com.day1.future;

public class Main {

	public static void main(String[] args) {
		//client
		FutureClient fc = new FutureClient();
		//返回一个结果
		Data data = fc.request("请求参数");
		System.out.println("请求成功");
		System.out.println("do something ...");
		//返回真的处理结果
		String result = data.getRequest();
		System.out.println("result==="+result);
	}

}
