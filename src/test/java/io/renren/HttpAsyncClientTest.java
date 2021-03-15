package io.renren;

import java.util.concurrent.CountDownLatch;

import admin.common.httpclient.HttpClientUtil;
import admin.common.httpclient.exception.HttpProcessException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;

public class HttpAsyncClientTest {
	public static void main(String[] args) throws Exception {
	        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
	        PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);
	        cm.setMaxTotal(1000);
	        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom().setConnectionManager(cm).build();
	        httpAsyncClient.start();
	        String[] urisToGet = {
	                "http://test.bizm2.jd.com/dashBorad/detail?component=table&bizType=settleFailInfo",
	                "GET https://api.weixin.qq.com/wxa/getpaidunionid?access_token=ACCESS_TOKEN&openid=OPENID",

	        };
	
	        final CountDownLatch latch = new CountDownLatch(urisToGet.length);
	        for (final String uri: urisToGet) {
	            final HttpPost httpget = new HttpPost(uri);
				httpget.setHeader("cookie","__jdu=16112321519681323592463; shshshfpb=lQDyh1h4Utub%2Fb53zJa1inw%3D%3D; shshshfpa=b79e2de8-4e38-5700-34f0-75e52008b952-1600999759; __jdv=154268707|direct|-|none|-|1614069614301; areaId=1; ipLoc-djd=1-2809-0-0; sso.jd.com=BJ.e3371e2651034f8698b64421084ddc42; PCSYCityID=CN_110000_110100_110112; __jda=122270672.16112321519681323592463.1611232152.1614736974.1614739633.54; __jdc=122270672; shshshfp=aba1f52099a530a4eb48eff1fa2eb260; shshshsID=c2d2b041a7a4fc8f7d6f6aca6a5580f0_2_1614740516231; 3AB9D23F7A4B3C9B=WZLU7V2WXQLDDYLCH3GNG46B7LXZTK5Y7NSHMMXG7Q5LBECNKB3YVSERA5NKNMH3RE6CRPIE6VQJVXZP7JSV6HPIQ4; __jdb=122270672.19.16112321519681323592463|54.1614739633");
	            httpAsyncClient.execute(httpget, new FutureCallback<HttpResponse>() {
	
	                public void completed(final HttpResponse response) {
	                    latch.countDown();
						try {
							System.out.println(httpget.getURI()+"结果："+response.getStatusLine().getStatusCode());
							
							System.out.println(HttpClientUtil.fmt2String(response,"UTF-8"));
						} catch (HttpProcessException e) {
							e.printStackTrace();
						}
					}
	
	                public void failed(final Exception ex) {
	                    latch.countDown();
	                    System.out.println("1111111");
	                }
	
	                public void cancelled() {
	                    latch.countDown();
	                    System.out.println(" cancelled");
	                }
	
	            });
	        }
	        latch.await();
	    }
}

