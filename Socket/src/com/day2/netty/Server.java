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

		//1,�����߳���
		//����
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//ʵ�ʴ���
		EventLoopGroup workGroup = new NioEventLoopGroup();
		//2������ServerBootstrap
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workGroup)
		//ʹ��NioServerSocketChannelͨ��
		.channel(NioServerSocketChannel.class)
		//ʹ��childHandler�󶨾����¼�������
		.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				sc.pipeline().addLast(new ServerHandler());
			}
		});
		//option()���ṩ��NioServerSocketChannel�������ս��������ӡ�childOption()���ṩ���ɸ��ܵ�ServerChannel���յ�������
		//.option(ChannelOption.SO_BACKLOG, 128)
		//���ⲻ��Ҫ��û����ͨ��
		//.childOption(ChannelOption.SO_KEEPALIVE, true);
		//3���󶨶˿�
		ChannelFuture cf = b.bind(8888).sync();
		cf.channel().closeFuture().sync();
		bossGroup.shutdownGracefully();
		workGroup.shutdownGracefully();
		
		
	}

}
