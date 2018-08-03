package com.day2.socket1;

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
			Socket socket = null;
			HandlerExecutorPool e = new HandlerExecutorPool(50,1000);
			while(true) {
				socket = server.accept();
				e.execute(new ServerHandler(socket));
			}
			
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
