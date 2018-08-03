package com.day1.workers;

public class Task {
	
	private String name;
	private String id;
	private int price;

	public Task(String name, String id, int price) {
		super();
		this.name = name;
		this.id = id;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public Object getPrice() {
		return price;
	}
	
}
