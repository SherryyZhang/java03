package com.day2.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

	public static void main(String[] args) {

		EventLoopGroup workGroup = new NioEventLoopGroup();
		Bootstrap b= new Bootstrap();
		b.group(workGroup).channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel sc) throws Exception {

				sc.pipeline().addLast(new ClientHandler());
			}
		});
		ChannelFuture cf;
		try {
			cf = b.connect("localhost",8888).sync();
			//buf
			cf.channel().write(Unpooled.copiedBuffer("1111".getBytes()));
			//flush
			cf.channel().flush();
			
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		workGroup.shutdownGracefully();
	}

}
