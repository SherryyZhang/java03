package com.day1.future;

public class FutureClient {

	public Data request(final String string) {
		//FutureData
		final FutureData fdata = new FutureData();
		//启动一个线程去加载真实数据
		new Thread(new Runnable() {
			//获得真实对象，返回给代理
			@Override
			public void run() {
				RealData rd = new RealData(string);
				fdata.setRealData(rd);
			}
		}).start();
		
		return fdata;
	}

}
