package com.day1.queue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueMain {
	public static void main(String[] args) throws Exception {
		// ���Ϊ true����ȴ��߳��� FIFO ��˳�������ʣ�����˳����δָ���ġ�
		// SynchronousQueue<Integer> sc =new SynchronousQueue<>(true);//fair
		SynchronousQueue<Integer> sc = new SynchronousQueue<>(); // Ĭ�ϲ�ָ���Ļ���false������ƽ��
		new Thread(() -> { //�������̣߳�ʹ�õ���lambdaд������Ҫʹ��JDK1.8
			while (true) {
				try {
					sc.put(1);
					//��ָ��Ԫ����ӵ��˶��У����б�Ҫ��ȴ���һ���߳̽�������
					// System.out.println("sc.offer(new Random().nextInt(50)): "+sc.offer(new Random().nextInt(50))); 
					// �����һ���߳����ڵȴ��Ա����ָ��Ԫ�أ���ָ��Ԫ�ز��뵽�˶��С����û�еȴ��������ݵ��߳���ֱ�ӷ���false
					// System.out.println("sc.offer(2,5,TimeUnit.SECONDS):
					// "+sc.offer(2,5,TimeUnit.SECONDS));//���û�еȴ����̣߳���ȴ�ָ����ʱ�䡣�ڵȴ�ʱ�仹û�н������ݵ��̵߳Ļ���ֱ�ӷ���false
					System.out.println("��Ӳ����������...");//�ǲ�����ϣ���������ӻ��ȡԪ�سɹ�!
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(() -> {
			while (true) {
				try {
					System.out.println("-----------------> sc.take: " + sc.take());
					System.out.println("-----------------> ��ȡ�����������...");//�ǲ�����ϣ���������ӻ��ȡԪ�سɹ�!
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
