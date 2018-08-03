package com.day2.nio;

import java.nio.IntBuffer;

public class TestBuffer03 {

	public static void main(String[] args) {
		//其他方法
		IntBuffer ib1 = IntBuffer.allocate(10);
		int [] arr = new int[] {1,2,5};
		ib1.put(arr);
		System.out.println(ib1);
		//复制
		IntBuffer ib2 = ib1.duplicate();
		System.out.println(ib2);
		
		//设置position
		ib1.position(1);
		System.out.println(ib1);
		
		System.out.println(ib1.remaining());//可读数据
		int[] arr2 = new int[ib1.remaining()];
		ib1.get(arr2);
		for (int i:arr2) {
			System.out.print(i+",");
		}
	}

}
