package bhz.netty.runtime;

import java.util.HashMap;
import java.util.Map.Entry;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter{

	private static HashMap<String, String> AUTH_IP_MAP =new HashMap<String, String>();
	private static final String SUCCESS_KEY ="auth_success_key";
	
	static {
		AUTH_IP_MAP.put("172.16.173.33", "1234");
	}
	
	private boolean auth(ChannelHandlerContext ctx, Object msg) {
		String [] ret = ((String)msg).split(",");
		String auth = AUTH_IP_MAP.get(ret[0]);
		if(auth != null && auth.equals(ret[1])) {
			ctx.writeAndFlush(SUCCESS_KEY);
			return true;
		} else {
			ctx.writeAndFlush("failure!");
			return false;
		}
		
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof String) {
			if(auth(ctx, msg)) {	
				System.out.println("---------------------------auth----------------------  \n"+msg);
			} 
		} else if(msg instanceof RequestInfo) {
			RequestInfo info = (RequestInfo)msg;
			System.out.println("---------------------------Server----------------------  \n"+info.getHostAddr());
			System.out.println(info.getCpuMap());
			HashMap<String, Object> cpu = info.getCpuMap();
			for(Entry<String, Object> cinfo : cpu.entrySet()) {
				System.out.println(cinfo.getKey()+"------"+cinfo.getValue());
			}

			HashMap<String, Object> mem = info.getMemMap();
			for(Entry<String, Object> minfo : mem.entrySet()) {
				System.out.println(minfo.getKey()+"------"+minfo.getValue());
			}
			ctx.writeAndFlush("-----------------重连-------------");
		} else {
			ctx.writeAndFlush("---------------异常--------------");
		}
			
		
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	
	
}
