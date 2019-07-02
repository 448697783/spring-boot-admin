

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.itextpdf.text.pdf.BaseFont;

@Service
public class PDFService {
	
    public static void createPDF()   {
    	 final WebClient mWebClient = new WebClient();
    	 mWebClient.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
    	 mWebClient.setJavaScriptTimeout(100000);//设置JS执行的超时时间
    	 mWebClient.getOptions().setThrowExceptionOnScriptError(false); //此行必须要加

    	 mWebClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置支持AJAX
    	 mWebClient.getOptions().setCssEnabled(true); //禁用css支持  
    	 mWebClient.getOptions().setTimeout(1000000);
    	 mWebClient.getOptions().setDoNotTrackEnabled(false);
    	HtmlPage mHtmlPage = null;
		try {
			mWebClient.getJavaScriptEngine();
			mWebClient.waitForBackgroundJavaScript(600*1000);
//			mHtmlPage = mWebClient.getPage("http://127.0.0.1:8080/grant/2760f1f842cc4545b74be2610d5177f7");
			
		    WebRequest request=new WebRequest(new java.net.URL("http://127.0.0.1:8080/#/preview?fkOrderId=20190617104448590129714753048576&cardno=232301199001272234"));
		    //模拟浏览器打开一个目标网址
		    mHtmlPage = mWebClient.getPage(request);
		    	System.out.println(mHtmlPage.asXml());

		    //为了获取js执行的数据 线程开始沉睡等待
		     Thread.sleep(1000);//这个线程的等待 因为js加载需要时间的
		    //以xml形式获取响应文本
		    String xml = mHtmlPage.asXml();
		    //并转为Document对象return
			mWebClient.waitForBackgroundJavaScript(10000);  
			Thread.sleep(3000);//主要是这个线程的等待 因为js加载也是需要时间的
		} catch (FailingHttpStatusCodeException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	mWebClient.closeAllWindows();?
		
    	WebResponse webResponse = mHtmlPage.getWebResponse();
    	String contentAsString = webResponse.getContentAsString();
//    	System.out.println(mHtmlPage.getWebResponse().getContentAsString());
    	ByteArrayOutputStream  output = new ByteArrayOutputStream();
    	ByteArrayInputStream input = null;
    	try {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(mHtmlPage.getWebResponse().getContentAsString());
			// step 3 解决中文支持
	        ITextFontResolver fontResolver = renderer.getFontResolver();
	        if("linux".equals(getCurrentOperatingSystem())){
	            fontResolver.addFont("/usr/share/fonts/chiness/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        }else{
	            fontResolver.addFont("c:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
	        }
			
			renderer.layout();
			renderer.createPDF(output,true);
//			String url = "company/"+ShiroUtils.getDeptId()+"/"+createPDFVO.getFkOrderId()+"_"+createPDFVO.getId()+".pdf";
			
			input = new ByteArrayInputStream(output.toByteArray());
//			OSSUtils.uploadPDF(input, url);
			
//			return createPDFVO.ok(OSSUtils.getUrl(url));
    	}catch (Exception e) {
    		e.getSuppressed();
    	}
    	finally {
    		mWebClient.close();


    		if(output!=null) {
    			try {
    				output.close();
    			} catch (IOException e) {
    			}
    		}
			if(input!=null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
//    	return  createPDFVO.ok("");
	}
    public static String getCurrentOperatingSystem(){
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("---------当前操作系统是-----------" + os);
        return os;
    }
}

