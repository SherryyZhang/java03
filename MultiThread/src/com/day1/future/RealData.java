package com.day1.future;

public class RealData implements Data {

	private String string;
	public RealData(String string) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----"+string);
		this.string = "��ʵ���";
	}

	@Override
	public String getRequest() {
		System.out.println("������ʵ����");
		return string;
	}

}
