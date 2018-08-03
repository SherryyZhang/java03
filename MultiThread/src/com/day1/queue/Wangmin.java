package com.day1.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Wangmin implements Delayed {
	private String name;
	private String id;
	private long endTime;
	
	public Wangmin(String name, String id, long endTime) {
		
		super();
		this.name = name;
		this.id = id;
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}	

	public long getEndTime() {
		return endTime;
	}

	//�Ƚ�
	@Override
	public int compareTo(Delayed o) {
		Wangmin w = (Wangmin)o;
		return this.getDelay(TimeUnit.SECONDS)-w.getDelay(TimeUnit.SECONDS)>0?1:0;
	}

	//�ж�ʱ��,ʱ�䵽�˲��ܻ�ȡ��Ԫ��
	@Override
	public long getDelay(TimeUnit unit) {
		return endTime-System.currentTimeMillis();
	}

}
