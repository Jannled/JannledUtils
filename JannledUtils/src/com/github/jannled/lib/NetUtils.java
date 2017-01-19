package com.github.jannled.lib;

import java.io.IOException;
import java.net.InetAddress;

public class NetUtils
{
	private NetUtils()
	{
		
	}
	
	/**
	 * Get the answer time of the host
	 * @param target The host to ping to
	 * @return The answer time in milliseconds, or -1 if the timeout gets reached
	 */
	public static int ping(InetAddress target)
	{
		return ping(target, 300);
	}
	
	/**
	 * Get the answer time of the host
	 * @param target The host to ping to
	 * @param timeout Time after the ping should be seen as lost
	 * @return The answer time in milliseconds, or -1 if the timeout gets reached
	 */
	public static int ping(InetAddress target, int timeout)
	{
		long ping = -1;
		try
		{
			long start = System.currentTimeMillis();
			InetAddress.getByName("google.de").isReachable(timeout);
			ping = System.currentTimeMillis() - start;
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return (int) ping;
	}
}
