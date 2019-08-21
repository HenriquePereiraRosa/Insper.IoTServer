package com.insper.iotserver.exceptionhandler.util;

public class ErrorMsg {

	private String userMsg;
	private String devMsg;

	public ErrorMsg(String userMsg, String devMsg) {
		this.userMsg = userMsg;
		this.devMsg = devMsg;
	}

	public String getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}

	public String getDevMsg() {
		return devMsg;
	}

	public void setDevMsg(String devMsg) {
		this.devMsg = devMsg;
	}
}
