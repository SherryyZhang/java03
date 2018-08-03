package bhz.netty.runtime;

import java.io.Serializable;
import java.util.HashMap;

public class RequestInfo implements Serializable {

	private HashMap<String, Object> memMap;
	private HashMap<String, Object> cpuMap;
	private String ip;

	public HashMap<String, Object> getMemMap() {
		return memMap;
	}
	public void setMemMap(HashMap<String, Object> memMap) {
		this.memMap = memMap;
	}
	public HashMap<String, Object> getCpuMap() {
		return cpuMap;
	}
	public void setCpuMap(HashMap<String, Object> cpuMap) {
		this.cpuMap = cpuMap;
	}
	public String getHostAddr() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

}
