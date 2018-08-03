package com.day2.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//真正通信一般用框架，不用这些。aio是异步非阻塞的
public class Server {

	private ExecutorService es;
	private AsynchronousChannelGroup threadGroup;
	public AsynchronousServerSocketChannel assc;
	public static void main(String[] args) {

		Server server = new Server(8888);
	}
	public Server(int port) {
		es = Executors.newCachedThreadPool();
		try {
			threadGroup = AsynchronousChannelGroup.withCachedThreadPool(es, 1);
			assc = AsynchronousServerSocketChannel.open(threadGroup);
			assc.bind(new InetSocketAddress(port));
			System.out.println("server start in --->"+port);
			
			assc.accept(this, new ServerCompletionHandler());
			Thread.sleep(Integer.MAX_VALUE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
