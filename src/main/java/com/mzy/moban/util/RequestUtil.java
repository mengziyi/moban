package com.mzy.moban.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p></p>
 * Copyright © 2013 Phoenix New Media Limited All Rights Reserved. 
 * @author xiongfeng
 * Aug 7, 2013
 */
public final class RequestUtil {
	
	private RequestUtil(){}
	
	/**
	 * 获取一个请求的所有请求参数
	 * @param req 请求
	 * @return 请求所有的参数
	 */
	public static Map<String, String> getParams(HttpRequest req) {
		String uri = req.getUri();
		Map<String, String> parameters = new HashMap<String, String>();
		if(req.getMethod() == HttpMethod.GET ) {
			QueryStringDecoder query = new QueryStringDecoder(uri);
			Map<String, List<String>> map = query.getParameters();
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				parameters.put(entry.getKey(), entry.getValue().get(0));
			}
		}else if(req.getMethod() == HttpMethod.POST) {
			ChannelBuffer content = req.getContent();
			if (content.readable()) {
			   String param = content.toString(Charset.forName("UTF-8"));
			   QueryStringDecoder queryStringDecoder = new
					   QueryStringDecoder("/?"+param);
			   Map<String, List<String>> map = queryStringDecoder.getParameters();
				for (Map.Entry<String, List<String>> entry : map.entrySet()) {
					parameters.put(entry.getKey(), entry.getValue().get(0));
				}
			}
		}
		
		return parameters;
	}
	

	public static String getURIPath(HttpRequest req) {
		String uri = req.getUri();
		int index = uri.indexOf('?');
		String path = uri;
		if (index != -1) {
			path = uri.substring(0, index);
		}
		return path;
	}
	



	public static void main(String[] args) throws IOException {
		String url = "http://localhost:8080/ipush/interf/subscribe!android.jhtml";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		String content = "snsnsns 03";
		String appKey = "90e47c9c-6099-4c11-875a-7a61bae68cbc";
		long time = System.currentTimeMillis();
		final String tokenKey = "ipush";
		final String masterSecret = "43239b23-8f0d-430e-bb10-eb17f5944ca1";
		// String devices = "888888888888888880,888888888888888881,888888888888888882,888888888888888883,888888888888888884,888888888888888885,888888888888888886,888888888888888887,888888888888888888,888888888888888889";
		String devices = "359478054833863,359478054833861,355799055274920,864593020111760,863802014328525";

		//        content += i;
		String extra = "{\"id\":\"1234567\",\"type\":\"doc\"}";
		String sound = "sub.caf";
		String token = MD5.encode(appKey + time + tokenKey + masterSecret);
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("appKey", new StringBody(appKey, Charset.forName("UTF-8")));
		reqEntity.addPart("time", new StringBody(time + "", Charset.forName("UTF-8")));
		reqEntity.addPart("token", new StringBody(token, Charset.forName("UTF-8")));
		reqEntity.addPart("device", new StringBody(devices, Charset.forName("UTF-8")));
		reqEntity.addPart("type", new StringBody("1", Charset.forName("UTF-8")));
		reqEntity.addPart("title", new StringBody(content, Charset.forName("UTF-8")));
		reqEntity.addPart("content", new StringBody(content, Charset.forName("UTF-8")));
		reqEntity.addPart("extra", new StringBody(extra, Charset.forName("UTF-8")));
		reqEntity.addPart("serverSideExpiredTime", new StringBody("100", Charset.forName("UTF-8")));
		reqEntity.addPart("important", new StringBody("1", Charset.forName("UTF-8")));
		reqEntity.addPart("styleId", new StringBody("1", Charset.forName("UTF-8")));
		httppost.setEntity(reqEntity);
		System.out.println("执行: " + httppost.getRequestLine());
		HttpResponse response = httpclient.execute(httppost);
		System.out.println("statusCode is " + response.getStatusLine().getStatusCode());
		HttpEntity resEntity = response.getEntity();
		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());
		if (resEntity != null) {
			System.out.println("返回长度: " + resEntity.getContentLength());
			System.out.println("返回类型: " + resEntity.getContentType());
			InputStream in = resEntity.getContent();
			//FileUtil.getStringFromInputStream(in);
		}
		if (resEntity != null) {
			resEntity.consumeContent();
		}
	}
}
