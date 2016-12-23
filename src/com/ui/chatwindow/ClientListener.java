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

    // ���캯��
    public ClientListener(String picture,String username,String password,ChatController chatcontroller) {
    	this.picture = picture;
    	this.username = username;
    	this.password = password;
    	this.chatcontroller = chatcontroller;
    }
	@Override
	public void run() {
		// ���ӷ�����
		try {
			socket = new Socket(ServerInfo.address,ServerInfo.port);

			os = socket.getOutputStream();
			output = new ObjectOutputStream(os);
			is = socket.getInputStream();
			input = new ObjectInputStream(is);

		} catch (Exception e) {
			Logger.info("���ӷ�����ʧ��", this.getClass());
		}

		Logger.info("��������Զ�̷�����" + socket.getInetAddress()
				+ "���Ӷ˿ں�:" + socket.getPort(),this.getClass());
		// ����������͵�һ����Ϣ

	}

}
