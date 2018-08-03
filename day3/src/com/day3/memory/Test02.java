package com.day3.memory;

import java.util.Vector;

public class Test02 {
//-Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:/Test03.dump
	public static void main(String[] args) {

		Vector v = new Vector();
		for (int i = 0; i<5; i++) {
			v.add(new Byte[1*1024*1024]);
		}
	}

}
