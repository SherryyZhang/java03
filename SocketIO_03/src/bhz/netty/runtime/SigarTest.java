package bhz.netty.runtime;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Who;

public class SigarTest {

	public static void main(String[] args) throws SigarException {

		SigarTest st = new SigarTest();
		Sigar s = new Sigar();
		s.getMem();
		s.getSwap();
		s.getWhoList();
		s.getFileSystemList();
		s.getCpuInfoList();
		s.getCpuPercList();
		st.cpu(s.getCpuPerc());
		
	}
	public void cpu(CpuPerc cpu) {
		System.out.println(CpuPerc.format(cpu.getUser()));
		System.out.println(CpuPerc.format(cpu.getSys()));
		System.out.println(CpuPerc.format(cpu.getWait()));
		System.out.println(CpuPerc.format(cpu.getNice()));
		System.out.println(CpuPerc.format(cpu.getIdle()));
		System.out.println(CpuPerc.format(cpu.getCombined()));
	}
	
	public void os() {
		OperatingSystem os = OperatingSystem.getInstance();
		System.out.println(os.getArch());
	}
	public void memory(Mem mem) {
		
	}
	public void who(Who who) {
		
	}
	

}
