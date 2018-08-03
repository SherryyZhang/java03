package com.day2.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

import javax.swing.table.TableColumn;

public class Client extends Thread{

	private AsynchronousSocketChannel asc;
	
	public static void main(String[] args) throws InterruptedException {

		try {
			Client c = new Client();
			c.connect();
			Client c2 = new Client();
			c2.connect();
			Client c3 = new Client();
			c3.connect();
			
			new Thread(c,"c").start();
			new Thread(c2,"c2").start();
			new Thread(c3,"c3").start();
			
			Thread.sleep(2000);
			
			c.write("c");
			c2.write("c2");
			c3.write("c3");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Client() throws IOException {
		asc = AsynchronousSocketChannel.open();
		
	}
	public void connect() {

		asc.connect(new InetSocketAddress("localhost", 8888));
	}
	public void write(String response) {
		asc.write(ByteBuffer.wrap(response.getBytes()));
		read();
	}

	private void read() {
		ByteBuffer bfc = ByteBuffer.allocate(1024);
		try {
			asc.read(bfc).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		bfc.flip();
		byte[] respByte = new byte[bfc.remaining()];
		bfc.get(respByte);
		try {
			System.out.println(new String(respByte,"utf-8").trim());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
