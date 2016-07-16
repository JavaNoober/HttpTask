package com.xiaoqi.httptask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

public class HttpParamsSettings {
	
	public static final int GET = 0;
	public static final int POST = 1;
	private String url;
	private int method = 0;
	private ArrayList<BasicNameValuePair> lstParams;
	
	public HttpParamsSettings(){
		lstParams = new ArrayList<BasicNameValuePair>();
	}
	
	public void put(String key,String value){
		try {
			lstParams.add(new BasicNameValuePair(key,URLEncoder.encode(value, "UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BasicNameValuePair> getParamsList(){
		return lstParams;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}
	
	
}
