package com.day1.future;

public class FutureClient {

	public Data request(final String string) {
		//FutureData
		final FutureData fdata = new FutureData();
		//����һ���߳�ȥ������ʵ����
		new Thread(new Runnable() {
			//�����ʵ���󣬷��ظ�����
			@Override
			public void run() {
				RealData rd = new RealData(string);
				fdata.setRealData(rd);
			}
		}).start();
		
		return fdata;
	}

}
