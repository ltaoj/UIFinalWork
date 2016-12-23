package com.ui.messages;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable{

	private String username;
	private String picture;
	private String msg;
	private MessageType msgType;
	private Status status;
	private byte[] voiceMsg;
    private ArrayList<User> list;
    private ArrayList<User> users;
    int count;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public MessageType getMsgType() {
		return msgType;
	}
	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public byte[] getVoiceMsg() {
		return voiceMsg;
	}
	public void setVoiceMsg(byte[] voiceMsg) {
		this.voiceMsg = voiceMsg;
	}
	public ArrayList<User> getList() {
		return list;
	}
	public void setList(ArrayList<User> list) {
		this.list = list;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	public int getOnlineCount() {
		return count;
	}
	public void setOnlineCount(int count) {
		this.count = count;
	}
}
