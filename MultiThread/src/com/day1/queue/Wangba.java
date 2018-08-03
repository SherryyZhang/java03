package com.day1.queue;

import java.util.concurrent.DelayQueue;

public class Wangba implements Runnable {

	private DelayQueue<Wangmin> queue = new DelayQueue<Wangmin>();
	
	public boolean yingye = true;
	public void shangji(String name,String id,int money) {
		Wangmin man = new Wangmin(name, id, 1000*money+System.currentTimeMillis());
		System.out.println(man.getName()+"��ʼ����������");
		this.queue.add(man);
	}
	
	public void xiaji(Wangmin man) {
		System.out.println(man.getName()+"ֹͣ����");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Wangmin man = queue.take();
				xiaji(man);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {

		try {
			System.out.println("��ʼӪҵ");
			Wangba wb = new Wangba();
			Thread t1 = new Thread(wb);
			t1.start();	

			wb.shangji("1", "1", 3);
			wb.shangji("2", "2", 3);
			wb.shangji("3", "3", 3);
			
		} catch (Exception e) {
		}
	}	

}
