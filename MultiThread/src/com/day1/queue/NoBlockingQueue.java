package com.day1.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NoBlockingQueue {
//���������У��߲��������ܸߣ��Ƚ��ȳ�
	public static void main(String[] args) {
//�޽��
		
		ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<String>();
		System.out.println(clq.add("1"));
		System.out.println(clq.offer("2"));
		System.out.println(clq.offer("3"));
		System.out.println(clq.offer("4"));
		System.out.println(clq.poll());
		System.out.println(clq.size());
		System.out.println(clq.peek());
		System.out.println(clq.size());
		System.out.println(clq.remove("4"));
		System.out.println(clq.size());
		
		/*
		 * 
		 * */
		
		
	}

}
