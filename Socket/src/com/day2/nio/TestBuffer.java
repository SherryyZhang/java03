package com.day2.nio;

import java.nio.IntBuffer;

public class TestBuffer {

	public static void main(String[] args) {
//�����Ǵ�ͨ�����뻺�������ӻ�����д�뵽ͨ���еġ���������������һ�����д�����ݣ�Ȼ����Դ��ж�ȡ���ݵ��ڴ档
		//��������
		IntBuffer ib = IntBuffer.allocate(10);
		ib.put(1);//position 0->1
		ib.put(2);//position 1->2
		ib.put(3);//position 2->3
		ib.flip();//��λ�ø�λΪ0
		
		System.out.println("ʹ��flip��λ "+ib);
		System.out.println("capacity "+ib.capacity());
		System.out.println("limit "+ib.limit());
		
		System.out.println("��ȡ�±�Ϊ1--"+ib.get(1));
		System.out.println("get(index)������positionλ�ò���"+ib);
		
		ib.put(1, 4);//position 1->4
		
		System.out.println("put(index,value),positionλ�ò���"+ib);
		
		for (int i=0; i<ib.limit(); i++) {
			System.out.print(ib.get()+"\t");//get,position����һλ
		}
	}

}
