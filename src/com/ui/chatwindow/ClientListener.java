package com.ui.chatwindow;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.ui.server.Logger;
import com.ui.server.ServerInfo;


public class ClientListener implements Runnable{


	private static String picture;
	private Socket socket;
	private static String username;
	private String password;
	public ChatController chatcontroller;

    private static ObjectOutputStream output;
    private OutputStream os;
    private ObjectInputStream input;
    private InputStream is;

    // 构造函数
    public ClientListener(String picture,String username,String password,ChatController chatcontroller) {
    	this.picture = picture;
    	this.username = username;
    	this.password = password;
    	this.chatcontroller = chatcontroller;
    }
	@Override
	public void run() {
		// 连接服务器
		try {
			socket = new Socket(ServerInfo.address,ServerInfo.port);

			os = socket.getOutputStream();
			output = new ObjectOutputStream(os);
			is = socket.getInputStream();
			input = new ObjectInputStream(is);

		} catch (Exception e) {
			Logger.info("连接服务器失败", this.getClass());
		}

		Logger.info("已连接至远程服务器" + socket.getInetAddress()
				+ "连接端口号:" + socket.getPort(),this.getClass());
		// 向服务器发送第一条信息

	}

}
