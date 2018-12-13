package com.blog.demo;

import android.util.Log;

public class LogUtil {
	public final static String LOGTAG = "demo";
	private static StringBuilder log = new StringBuilder();
	
	public static void log(String logtag, String msg) {
		Log.i(LOGTAG + "/" + logtag, msg);
		log.append(logtag + ": \t" + msg + "\n");
	}
	
	public static void clearLog() {
		log = new StringBuilder();
	}
	
	public static String getLog() {
		return log.toString();
	}
}
