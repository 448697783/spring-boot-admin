package io.renren;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;

import admin.common.httpclient.HttpClientUtil;
import admin.common.httpclient.common.HttpConfig;
import admin.common.httpclient.common.HttpHeader;
import admin.common.httpclient.exception.HttpProcessException;
import admin.common.utils.DateUtils;
import admin.common.utils.SignUtil;

/** 
 * 使用简单介绍
 * 
 * @author arron
 * @date 2016年11月7日 下午2:36:16 
 * @version 1.0 
 */
public class Csi {

	static String url = "http://api2.zelkova.cn/index.php";
	public static void main(String[] args) throws HttpProcessException, FileNotFoundException {
		String token ="n88nso34vf4kt6trtsjs4c0g76";
		Header[] headers 	= HttpHeader.custom()
				.build();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("partnerId", "1489196600");
		map.put("token", token);
		String format = DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN);
		map.put("timestamp",System.currentTimeMillis());
		String sign = SignUtil.sign(map, "");
		map.put("sign", sign);
		HttpConfig config = HttpConfig.custom()
					.headers(headers)	//设置headers，不需要时则无需设置
					.url(url+"?op=pQueryPartnerILockList")					//设置请求的url
					.map(map)			//设置请求参数，没有则无需设置
					.encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
					;

//使用方式：
//String result1 = HttpClientUtil.get(config);		//get请求
String result2 = HttpClientUtil.post(config);	//post请求
System.out.println(result2);
		gettoken();
		
	}
	private static void gettoken() throws HttpProcessException {
		Header[] headers 	= HttpHeader.custom()
											.build();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("partnerId", "1489196600");
		map.put("passwd", "zrx123");
		String format = DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN);
		map.put("timestamp",System.currentTimeMillis());
		String sign = SignUtil.sign(map, "");
		map.put("sign", sign);
		HttpConfig config = HttpConfig.custom()
											.headers(headers)	//设置headers，不需要时则无需设置
											.url(url+"?op=pLogin")					//设置请求的url
											.map(map)			//设置请求参数，没有则无需设置
											.encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
											;
		
		//使用方式：
//		String result1 = HttpClientUtil.get(config);		//get请求
		String result2 = HttpClientUtil.post(config);	//post请求
		System.out.println(result2);
	}
}
