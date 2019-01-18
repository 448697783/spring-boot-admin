package io.renren;

import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
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
	        cm.setMaxTotal(100);
	        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom().setConnectionManager(cm).build();
	        httpAsyncClient.start();
	        String[] urisToGet = {
	                "http://wallet-test.sohupay.com/wallet/check.pay",
	                "http://www.so.com/",
	                "http://www.qq.com/",
	        };
	
	        final CountDownLatch latch = new CountDownLatch(urisToGet.length);
	        for (final String uri: urisToGet) {
	            final HttpGet httpget = new HttpGet(uri);
	            httpAsyncClient.execute(httpget, new FutureCallback<HttpResponse>() {
	
	                public void completed(final HttpResponse response) {
	                    latch.countDown();
	                    System.out.println(httpget.getURI());
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

