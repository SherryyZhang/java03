package com.day2.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

	 
	public static void main(String[] args) throws InterruptedException {

		//1,两个线程组
		//连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//实际处理
		EventLoopGroup workGroup = new NioEventLoopGroup();
		//2，设置ServerBootstrap
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workGroup)
		//使用NioServerSocketChannel通道
		.channel(NioServerSocketChannel.class)
		//使用childHandler绑定具体事件处理器
		.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				sc.pipeline().addLast(new ServerHandler());
			}
		});
		//option()是提供给NioServerSocketChannel用来接收进来的连接。childOption()是提供给由父管道ServerChannel接收到的连接
		//.option(ChannelOption.SO_BACKLOG, 128)
		//在这不需要，没有子通道
		//.childOption(ChannelOption.SO_KEEPALIVE, true);
		//3，绑定端口
		ChannelFuture cf = b.bind(8888).sync();
		cf.channel().closeFuture().sync();
		bossGroup.shutdownGracefully();
		workGroup.shutdownGracefully();
		
		
	}

}
