package com.day2.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ServerCompletionHandler implements
		CompletionHandler<AsynchronousSocketChannel, Server> {

	@Override
	public void completed(AsynchronousSocketChannel result, Server attachment) {
		//当有下一个客户端介入时，保证多个客户端都能进行异步阻塞
		attachment.assc.accept(attachment, this);
		read(result);
	}

	private void read(final AsynchronousSocketChannel asc) {

		ByteBuffer bf = ByteBuffer.allocate(1024);
		//异步的
		asc.read(bf,bf,new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer i, ByteBuffer attachment) {
				
				attachment.flip();
				System.out.println("server接收到数据长度-->"+i);
				String resultData = new String(attachment.array()).trim();
				System.out.println("server接收到消息--->"+resultData);
				String response = "server response-->"+resultData;
				write(asc, response);
				
				
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				exc.printStackTrace();
			}

		});
		
	}
	
	private void write(AsynchronousSocketChannel asc, String response) {
		ByteBuffer bf2 = ByteBuffer.allocate(1024);
		bf2.put(response.getBytes());
		bf2.flip();
		try {
			asc.write(bf2).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void failed(Throwable exc, Server attachment) {

		exc.printStackTrace();
	}

}
