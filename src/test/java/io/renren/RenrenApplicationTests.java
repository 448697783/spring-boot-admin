package io.renren;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.junit.Test;

import admin.common.httpclient.HttpClientUtil;
import admin.common.httpclient.common.HttpConfig;
import admin.common.httpclient.common.HttpHeader;
import admin.common.httpclient.exception.HttpProcessException;

public class RenrenApplicationTests {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", "1");
		map.put("limit", "2");
		map.put("id", "4333");

		String url ="https://api.weixin.qq.com/wxa/getpaidunionid?access_token=12E538FA23B654F893FBC26770DC68C8FD700211&openid=OPENID&transaction_id";
		final HttpConfig config = HttpConfig.custom()
				.url(url) // 设置请求的url
				.map(map) // 设置请求参数，没有则无需设置
				.encoding("UTF-8") // 设置请求和返回编码，默认就是Charset.defaultCharset()
				// .client(client) //如果只是简单使用，无需设置，会自动获取默认的一个client对象
				// .inenc("utf-8") //设置请求编码，如果请求返回一直，不需要再单独设置
				// .inenc("utf-8") //设置返回编码，如果请求返回一直，不需要再单独设置
//				.json("json字符串") // json方式请求的话，就不用设置map方法，当然二者可以共用。
		// .context(HttpCookies.custom().getContext())
		// //设置cookie，用于完成携带cookie的操作
		// .out(new FileOutputStream("保存地址")) //下载的话，设置这个方法,否则不要设置
		// .files(new String[]{"d:/1.txt","d:/2.txt"})
		// //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
		;

		// 使用方式：
		for (int i = 0; i < 10; i++) {
			Thread th = new Thread(new Runnable() {
				
				@Override
				public void run() {
					String result1;
					try {
						for (int j = 0; j < 1000; j++) {
							
							result1 = HttpClientUtil.post(config);
							if("SUCCESS".equals( result1)) {
								System.out.println(result1);
							}
						}
					} catch (HttpProcessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // get请求
					
				}
			}) ;
			th.start();
			
		}

		// HttpClientUtil.down(config); //下载，需要调用config.out(fileOutputStream对象)
		// HttpClientUtil.upload(config); //上传，需要调用config.files(文件路径数组)

		// 如果指向看是否访问正常
		// String result3 = HttpClientUtil.head(config); // 返回Http协议号+状态码
		// int statusCode = HttpClientUtil.status(config);//返回状态码

	}

	@Test
	public void save() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", "1");
		map.put("limit", "2");
		map.put("actNumber", "5");
		map.put("id", "10");
		map.put("name", "1234567");
		map.put("productId", "1000");
		map.put("createdOn", "2017-01-01 10:10:10");

		
		String url ="http://127.0.0.1:8081/active/active/save";
		HttpConfig config = HttpConfig.custom()
				.url(url) // 设置请求的url
				.map(map) // 设置请求参数，没有则无需设置
				.encoding("utf-8") // 设置请求和返回编码，默认就是Charset.defaultCharset()
				// .client(client) //如果只是简单使用，无需设置，会自动获取默认的一个client对象
				// .inenc("utf-8") //设置请求编码，如果请求返回一直，不需要再单独设置
				// .inenc("utf-8") //设置返回编码，如果请求返回一直，不需要再单独设置
//				.json("json字符串") // json方式请求的话，就不用设置map方法，当然二者可以共用。
		// .context(HttpCookies.custom().getContext())
		// //设置cookie，用于完成携带cookie的操作
		// .out(new FileOutputStream("保存地址")) //下载的话，设置这个方法,否则不要设置
		// .files(new String[]{"d:/1.txt","d:/2.txt"})
		// //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
		;

		// 使用方式：
		String result1;
		try {
			result1 = HttpClientUtil.post(config);
			System.out.println("ret:"+result1);
		} catch (HttpProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // get请求
	}
	/** 
	 * @Title: update 
	 * @Description: TODO(这里用一句话描述这个方法的作用)  void    返回类型 
	 * 
	 */
	@Test
	public void update() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actNumber", "5");
		map.put("id", "10");
		map.put("name", "1234567");
		map.put("productId", "1000");


		Header[] headers=HttpHeader.custom().contentType("application/json")
				.build();

		String url ="http://127.0.0.1:8081/active/active/update";
		HttpConfig config = HttpConfig.custom()
				.url(url) // 设置请求的url
				.map(map) // 设置请求参数，没有则无需设置
				.encoding("utf-8") // 设置请求和返回编码，默认就是Charset.defaultCharset()
//				.headers(headers)
				// .client(client) //如果只是简单使用，无需设置，会自动获取默认的一个client对象
				// .inenc("utf-8") //设置请求编码，如果请求返回一直，不需要再单独设置
				// .inenc("utf-8") //设置返回编码，如果请求返回一直，不需要再单独设置
//				.json("json字符串") // json方式请求的话，就不用设置map方法，当然二者可以共用。
		// .context(HttpCookies.custom().getContext())
		// //设置cookie，用于完成携带cookie的操作
		// .out(new FileOutputStream("保存地址")) //下载的话，设置这个方法,否则不要设置
		// .files(new String[]{"d:/1.txt","d:/2.txt"})
		// //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
		;

		// 使用方式：
		String result1;
		try {
			result1 = HttpClientUtil.post(config);
			System.out.println("ret:"+result1);
		} catch (HttpProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // get请求
	}

}
