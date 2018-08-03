package com.day1.future;

public class FutureData implements Data {

	private RealData rd;
	private boolean isReady = false;
	
	public synchronized void setRealData(RealData rd) {
		if(isReady) {
			return;
		}
		this.rd = rd;
		isReady = true;
		notify();
	}
	
	@Override
	public synchronized String getRequest() {
		while(!isReady) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return rd.getRequest();
	}

}
