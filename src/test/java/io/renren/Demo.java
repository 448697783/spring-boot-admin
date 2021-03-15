package io.renren;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import admin.common.httpclient.HttpClientUtil;
import admin.common.httpclient.builder.HCB;
import admin.common.httpclient.common.HttpConfig;
import admin.common.httpclient.common.SSLs.SSLProtocolVersion;
import admin.common.httpclient.exception.HttpProcessException;
//import io.github.yedaxia.apidocs.Docs;
//import io.github.yedaxia.apidocs.DocsConfig;

/** 
 * 使用简单介绍
 * 
 * @author arron
 * @date 2016年11月7日 下午2:36:16 
 * @version 1.0 
 */
public class Demo {

	public static void main(String[] args) throws HttpProcessException, FileNotFoundException {
		for(int i=0;i<100;i++) {
			double a = Math.random()*100+1;
			System.out.println(a);
		System.out.println(100>=a);
		}
		ConcurrentHashMap a = new ConcurrentHashMap();

//		DocsConfig config = new DocsConfig();
//		config.setProjectPath("D:\\tool\\eclipse\\wodkspace\\dlb_order\\order.web\\src"); // root project path
//		config.setProjectName("springboot"); // project name
//		config.setApiVersion("V1.0");       // api version
//		config.setDocsPath("D:\\tool\\"); // api docs target path
//		config.setAutoGenerate(Boolean.TRUE);  // auto generate
//		Docs.buildHtmlDocs(config); // execute to generate
	}
}
