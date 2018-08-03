package com.day2.socket0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	final static String ADDRESS = "localhost";
	final static int PORT = 8888;
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader bf = null;
		PrintWriter pw = null;
		
		try {
			socket = new Socket(ADDRESS, PORT);
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(),true);
			
			pw.println("data");
			String response = bf.readLine();
			System.out.println("Client---"+response);
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
