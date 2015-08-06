package com.mzy.moban.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: ifeng.com</p>
 * @author :huwq
 * @version 1.0
 * <p>--------------------------------------------------------------------</p>
 * <p>date                   author                    reason             </p>
 * <p>2011-6-17              huwq               create the class      </p>
 * <p>--------------------------------------------------------------------</p>
 */

public class HttpUtil {

	private static Log log = LogFactory.getLog(HttpUtil.class);





	/**
	 * @param @param  reqEntity
	 * @param @param  url
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 * @Description:文件上传
	 */
	public static String uploadFile(MultipartEntity reqEntity, String url) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		Scanner scanner = null;
		StringBuffer str = null;
		try {
			httppost.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();

			scanner = new Scanner(resEntity.getContent());
			str = new StringBuffer();
			while (scanner.hasNextLine()) {
				str.append(scanner.nextLine());
				str.append("\n");
			}

			EntityUtils.consume(resEntity);
		} catch (Exception e) {
			log.error("uploadFile : " + url + "|" + e.getMessage());
			throw e;
		} finally {
			scanner.close();
			httpclient.getConnectionManager().shutdown();
		}
		return str.toString();
	}

	/**
	 * 下载文件
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBinaryFile(String url) throws IOException {
		InputStream raw = null;
		InputStream in = null;
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			//String contentType = uc.getContentType();
			int contentLength = uc.getContentLength();
			raw = uc.getInputStream();
			in = new BufferedInputStream(raw);
			byte[] data = new byte[contentLength];
			int bytesRead = 0;
			int offset = 0;
			while (offset < contentLength) {
				bytesRead = in.read(data, offset, data.length - offset);
				if (bytesRead == -1) break;
				offset += bytesRead;
			}

			if (offset != contentLength) {
				log.error("getBinaryFile-->Only read " + offset + " bytes;Expected " + contentLength + " bytes.");
				return null;
			}
			return data;

		} catch (IOException e) {
			log.error("getBinaryFile : " + url + "|" + e.getMessage());
			throw e;
		} finally {
			if (raw != null) {
				raw.close();
			}
			if (in != null) {
				in.close();
			}
		}
	}


	public static void main(String[] args) throws Exception {
		HttpHost proxy =new HttpHost("localhost",8888,"http");
		org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);




		for(int i=0;i<20;i++) {
			String userName = "15652307027"+"abc"+i;
			String passWord = "uc7";

			JSONObject t = new JSONObject();
			t.put("username", userName);
			t.put("password", passWord);
			String q = JSON.toJSONString(t);
			StringEntity entity = new StringEntity(q);
			List<org.apache.http.NameValuePair> nvps = new ArrayList<org.apache.http.NameValuePair>();
//String params="{\"username\": \"1111ccc1\",\"password\":\"1111222\"";
//		nvps.add(new BasicNameValuePair("client_id","YXA6j5T1AClOEeWkyF1cHW5b9Q"));
//		nvps.add(new BasicNameValuePair("client_secret","YXA68zWtPEgn_TpOI_CyclY18D8Bwxs"));
//		nvps.add(new BasicNameValuePair("grant_type","client_credentials"));
//		nvps.add(new BasicNameValuePair("data",params));
			String encoding = "UTF-8";


			HttpPost post = new HttpPost("http://a1.easemob.com/mengzy/ucar/users");

			post.setHeader("Content-Type", "application/json");
			post.setHeader("Accept-Charset", encoding);
			String param = URLEncodedUtils.format(nvps, encoding);
			post.setEntity(entity);
			System.out.println("executing request " + post.getURI() + post.getEntity().getContentType());
			HttpResponse response = httpClient.execute(post);
			int status = response.getStatusLine().getStatusCode();
			String responseString = EntityUtils.toString(response.getEntity());     //返回服务器响应的HTML代码
			JSONObject w = JSONObject.parseObject(responseString);

			if (status != 200) {
				System.out.print("fail---->");
				System.out.println(w.get("error_description"));
			} else {
				System.out.println("success");
				System.out.println(responseString);
			}

		}




		httpClient.getConnectionManager().shutdown();

	}
}



