package uk.gov.dvla.utils;

import org.apache.log4j.Logger;


public final class LogHelper {
	
	private static Logger Log = Logger.getLogger("devpinoyLogger");
	
	private LogHelper() {}
	
	public static void debug(String msg) {
		Log.debug(msg);
	}
	
	public static void info(String msg) {
		Log.info(msg);
	}
	
	public static void fatal(String msg) {
		Log.fatal(msg);
	}

}
