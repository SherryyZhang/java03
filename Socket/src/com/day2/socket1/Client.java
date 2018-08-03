package com.day2.socket1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{

	final static String ADDRESS = "localhost";
	final static int PORT = 8888;
	public static void main(String[] args) {
		Client c = new Client();
		Thread t1 = new Thread(c,"c1");
		Thread t2 = new Thread(c,"c2");
		t1.start();
		t2.start();
	}


	@Override
	public void run() {
		Socket socket = null;
		BufferedReader bf = null;
		PrintWriter pw = null;
		
		try {
			socket = new Socket(ADDRESS, PORT);
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(),true);
			
			pw.println(Thread.currentThread().getName()+"data");
			String response = bf.readLine();
			System.out.println(Thread.currentThread().getName()+"---Client---"+response);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bf != null)
					bf.close();
				pw.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

}
