package bhz.netty.runtime;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.hyperic.sigar.*;


public class ClientHandler extends ChannelHandlerAdapter{
	
	private  static final String SUCCESS_KEY = "auth_success_key";
	private InetAddress addr;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);;
	private ScheduledFuture<?> heartBeat;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		String key = "1234";
		String auth = ip+","+key;	
		ctx.writeAndFlush(auth);
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			if(msg instanceof String) {
				String ret = (String)msg;
				if(SUCCESS_KEY.equals(ret)) {
					heartBeat = this.scheduler.scheduleWithFixedDelay(new HeartBeatTask(ctx),0,2,TimeUnit.SECONDS);		
				}
				System.out.println(msg);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ReferenceCountUtil.release(msg);
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

class HeartBeatTask implements Runnable {

	private ChannelHandlerContext ctx;
	public HeartBeatTask(ChannelHandlerContext ctx) {		
		this.ctx =ctx; 			
	}

	@Override
	public void run() {

		try {
			RequestInfo info = new RequestInfo();
			info.setIp(InetAddress.getLocalHost().getHostAddress());
			Sigar s = new Sigar();
			
			CpuPerc cpu;
			cpu = s.getCpuPerc();
			HashMap<String, Object> cpuMap = new HashMap<String, Object>();
			cpuMap.put("combined", cpu.getCombined());
			cpuMap.put("user", cpu.getUser());
			cpuMap.put("sys", cpu.getSys());
			cpuMap.put("wait", cpu.getWait());
			cpuMap.put("idle", cpu.getIdle());
			
			Mem mem = s.getMem();
			HashMap<String, Object> memMap = new HashMap<String, Object>();
			memMap.put("total", mem.getTotal());
			memMap.put("used", mem.getUsed());
			memMap.put("free", mem.getFree());
			
			info.setCpuMap(cpuMap);
			info.setMemMap(memMap);
			ctx.writeAndFlush(info);
			
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

