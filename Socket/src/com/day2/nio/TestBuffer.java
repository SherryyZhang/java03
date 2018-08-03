package com.day2.nio;

import java.nio.IntBuffer;

public class TestBuffer {

	public static void main(String[] args) {
//数据是从通道读入缓冲区，从缓冲区写入到通道中的。缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存。
		//基本操作
		IntBuffer ib = IntBuffer.allocate(10);
		ib.put(1);//position 0->1
		ib.put(2);//position 1->2
		ib.put(3);//position 2->3
		ib.flip();//把位置复位为0
		
		System.out.println("使用flip复位 "+ib);
		System.out.println("capacity "+ib.capacity());
		System.out.println("limit "+ib.limit());
		
		System.out.println("获取下标为1--"+ib.get(1));
		System.out.println("get(index)方法，position位置不变"+ib);
		
		ib.put(1, 4);//position 1->4
		
		System.out.println("put(index,value),position位置不变"+ib);
		
		for (int i=0; i<ib.limit(); i++) {
			System.out.print(ib.get()+"\t");//get,position后移一位
		}
	}

}
