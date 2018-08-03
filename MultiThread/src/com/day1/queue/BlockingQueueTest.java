package com.day1.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {

	public static void main(String[] args) {

		//有界
		ArrayBlockingQueue<String> aq = new ArrayBlockingQueue<>(5);
		//无界
		LinkedBlockingQueue<String> lq = new LinkedBlockingQueue<>(); 
		try {
			//等待多久放弃
			System.out.println(aq.offer("1", 1, TimeUnit.SECONDS));
			aq.add("2");
			aq.put("3"); 
			aq.add("4");
			aq.put("5");
			//error
			//aq.add("6");
			//waiting
			//aq.put("6");
			//false
			//aq.offer("6", 1, TimeUnit.SECONDS);
			//aq.offer("6");
			System.out.println(aq.size());
			List<String> list =new ArrayList<>();
			//取出元素放在list中
			aq.drainTo(list,3);
			for( String a : list) {
				System.out.println(a);
			}
			//不允许加元素
			SynchronousQueue<String> sq = new SynchronousQueue<String>();
			

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
