package com.day1.future;

public class Main {

	public static void main(String[] args) {
		//client
		FutureClient fc = new FutureClient();
		//����һ�����
		Data data = fc.request("�������");
		System.out.println("����ɹ�");
		System.out.println("do something ...");
		//������Ĵ�����
		String result = data.getRequest();
		System.out.println("result==="+result);
	}

}
