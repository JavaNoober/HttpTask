package com.xiaoqi.httptask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;

import org.apache.commons.httpclient.HttpClient;

public abstract class MyAsyncTask extends AsyncTask<Void, Void, Response> {
	
	private int method;
	private String url;
	private HttpParamsSettings valuePairParams;
	
	public MyAsyncTask(HttpParamsSettings valuePairParams){
		this.valuePairParams = valuePairParams;
		method = valuePairParams.getMethod();
		url = valuePairParams.getUrl();
	}
	
	public MyAsyncTask(){

	}
	
	public abstract void onSuccess(Response response);
	
	public void onFail(Response response){
		
	};
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected Response doInBackground(Void... params) {
		Response response;
		if(method == 0){
			response = getContentbyGet();
		}else{
			response = getContentbyPost();
		}
		return response;
	}

	@Override
	protected void onPostExecute(Response response) {
		super.onPostExecute(response);
		if(response.isSuccess()){
			onSuccess(response);
		}else{
			onFail(response);
		}
	}
	
//	/**
//	 * Get��ʽ��������
//	 *
//	 * @param url
//	 * @param valuePairParams
//	 * @return
//	 */
//	private Response getContent() {
//		Response response = new Response();
//		HttpClient client = new DefaultHttpClient();
//		String param = URLEncodedUtils.format(valuePairParams.getParamsList(), HTTP.UTF_8);
//		HttpGet get = new HttpGet(url+"?"+param);
//		String result = "";
//		try {
//			HttpResponse httpResponse = client.execute(get);
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				result = EntityUtils.toString(httpResponse.getEntity());
//				response.setResult(result);
//				response.setStatusCode(200);
//				response.setResponseState(true);
//			}else{
//				result = httpResponse.getStatusLine().getReasonPhrase();
//				response.setResult(result);
//				response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
//				response.setResponseState(false);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setResponseState(false);
//			response.setResult(e.getMessage());
//		}finally{
//			client.getConnectionManager().shutdown();
//		}
//
//		return response;
//	}

	/**
	 * Post��ʽ��������
	 *
	 * @param url
	 * @param valuePairParams
	 * @return
	 */
	private Response getContentByPost() {
		Response response = new Response();
		String result = "";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(valuePairParams.getParamsList(), HTTP.UTF_8));
			HttpResponse httpResponse = client.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() == 200){
				result = EntityUtils.toString(httpResponse.getEntity());
				response.setResult(result);
				response.setStatusCode(200);
				response.setResponseState(true);
			}else{
				result = httpResponse.getStatusLine().getReasonPhrase();
				response.setResult(result);
				response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
				response.setResponseState(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseState(false);
			response.setResult(e.getMessage());
		}finally{
			client.getConnectionManager().shutdown();
		}
		return response;
	}
	
	//TODO
	private Response getContentbyGet(){
		Response response = new Response();
		HttpURLConnection  connection = null;
		try {
			String param = URLEncodedUtils.format(valuePairParams.getParamsList(), HTTP.UTF_8);
			URL currentUrl = new URL(url+"?"+param);
			connection = (HttpURLConnection) currentUrl.openConnection();
			connection.setRequestProperty("Charsert", "UTF-8");  
			connection.setRequestMethod("GET");
			StringBuffer sb = new StringBuffer();
			if(connection.getResponseCode() == 200){
				response.setStatusCode(200);
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));  
				String str;
				while((str=reader.readLine())!=null){
					 sb.append(str).append("\n");  
				}
				response.setResult(sb.toString());
				response.setResponseState(true);
			}else{
				response.setStatusCode(connection.getResponseCode());
				response.setResult(connection.getResponseMessage());
				response.setResponseState(false);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			response.setResponseState(false);
			response.setResult(e.getMessage());
			response.setResponseState(false);
		} catch (IOException e) {
			e.printStackTrace();
			response.setResponseState(false);
			response.setResult(e.getMessage());
			response.setResponseState(false);
		}finally{
			if(connection!=null){
				connection.disconnect();
			}
		}
		return response;
	}
	
	//TODO
	private Response getContentbyPost(){
		Response response = new Response();
		HttpURLConnection  connection = null;
		try {
			String param = URLEncodedUtils.format(valuePairParams.getParamsList(), HTTP.UTF_8);
			URL currentUrl = new URL(url);
			connection = (HttpURLConnection) currentUrl.openConnection();
			connection.setRequestProperty("encoding","UTF-8");
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);  
			connection.setDoInput(true);  
			OutputStream os = connection.getOutputStream();       
	        os.write(param.getBytes("utf-8" ));       
	        os.close();  
			
			StringBuffer sb = new StringBuffer();
			if(connection.getResponseCode() == 200){
				response.setStatusCode(200);
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));  
				String str;
				while((str=reader.readLine())!=null){
					 sb.append(str).append("\n");  
				}
				response.setResult(sb.toString());
				response.setResponseState(true);
			}else{
				response.setStatusCode(connection.getResponseCode());
				response.setResult(connection.getResponseMessage());
				response.setResponseState(false);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			response.setResponseState(false);
			response.setResult(e.getMessage());
			response.setResponseState(false);
		} catch (IOException e) {
			e.printStackTrace();
			response.setResponseState(false);
			response.setResult(e.getMessage());
			response.setResponseState(false);
		}finally{
			if(connection!=null){
				connection.disconnect();
			}
		}
		return response;
	}
	
}
