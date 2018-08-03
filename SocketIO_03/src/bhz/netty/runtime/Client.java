package bhz.netty.runtime;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


/**
 * Best Do It
 */
public class Client {
	
	
	public static void main(String[] args) throws Exception{
		
		EventLoopGroup cGroup = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(cGroup)
		 .channel(NioSocketChannel.class)
		 .handler(new LoggingHandler(LogLevel.INFO))
		 .handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
					sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
					//超时handler（当服务器端与客户端在指定时间以上没有任何进行通信，则会关闭响应的通道，主要为减小服务端资源占用）
					//sc.pipeline().addLast(new ReadTimeoutHandler(5)); 
					sc.pipeline().addLast(new ClientHandler());
				}
	    });
		
		ChannelFuture cf = b.connect("127.0.0.1", 8888).sync();
		cf.channel().closeFuture().sync();

		cGroup.shutdownGracefully();
		
		
	}
	
	
	
}
