package com.day2.socket0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	final static int PORT = 8888;
	static ServerSocket server = null;
	public static void main(String[] args) {
		try {
			server = new ServerSocket(PORT);

			System.out.println("server is ready");
			Socket socket = server.accept();
			new Thread(new ServerHandler(socket)).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(server!=null) {
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		server = null;
		
	}

}
