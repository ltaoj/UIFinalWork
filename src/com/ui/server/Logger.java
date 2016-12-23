package com.ui.server;

public class Logger {

	public static void info(String info,Class name){
		System.out.println(name.getName() + ":" + info);
	}
}
