package com.xiaoqi.httptask;

public class Response {
	private String result;
	private int statusCode;
	private boolean isSuccess;
	
	public String getResult(){
		return result;
	}
	
	public void setResult(String result){
		this.result = result;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setResponseState(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
}
