package com.day3.memory;

public class Test01 {

	public static void main(String[] args) {
		
		//-XX:+PrintGC -Xms5m -Xmx5m -XX:+PrintGCDetails
		//-Xmn1m -XX:SurvivorRatio=eden/from -XX:NewRatio=老生代/新生代
		
		System.out.println("maxmemory----"+Runtime.getRuntime().maxMemory());
		System.out.println("freeMemory---"+Runtime.getRuntime().freeMemory());
		System.out.println("totalMemory--"+Runtime.getRuntime().totalMemory());
		
		Byte[] b1 = new Byte[1024*1024];
		
		System.out.println("maxmemory----"+Runtime.getRuntime().maxMemory());
		System.out.println("freeMemory---"+Runtime.getRuntime().freeMemory());
		System.out.println("totalMemory--"+Runtime.getRuntime().totalMemory());
		
		Byte[] b2 = new Byte[4*1024*1024];
		System.out.println("maxmemory----"+Runtime.getRuntime().maxMemory());
		System.out.println("freeMemory---"+Runtime.getRuntime().freeMemory());
		System.out.println("totalMemory--"+Runtime.getRuntime().totalMemory());
		
	}

}
