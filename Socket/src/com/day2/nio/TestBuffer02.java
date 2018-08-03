package com.day2.nio;

import java.nio.IntBuffer;

public class TestBuffer02 {

	public static void main(String[] args) {

		//swap方法使用
		int [] arr = new int[] {1,2,5};
		IntBuffer ib = IntBuffer.wrap(arr);
		System.out.println(ib);
		IntBuffer ib2 = IntBuffer.wrap(arr,0,2);
		System.out.println(ib2);
	}

}
