package com.example.base;

import java.lang.management.ManagementFactory;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.zaxxer.hikari.HikariPoolMXBean;

public class HikariPoolMXBeanUtils {

	private HikariPoolMXBeanUtils() {
	}
	static HikariPoolMXBean poolProxy = null;

	public static HikariPoolMXBean register() throws MalformedObjectNameException {
		if (poolProxy == null) {
			MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
			ObjectName poolName = new ObjectName("com.zaxxer.hikari:type=Pool (test)");
			poolProxy = JMX.newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);
		}

		return poolProxy;
	}

	public static void logHikariPoolStates() throws MalformedObjectNameException {
		register();

		String poolString = String.format("Pool stats (total=%s, active=%s, idle=%s, waiting=%s)",
				poolProxy.getTotalConnections(), poolProxy.getActiveConnections(), poolProxy.getIdleConnections(),
				poolProxy.getThreadsAwaitingConnection());

		System.out.println(poolString);
	}
}
