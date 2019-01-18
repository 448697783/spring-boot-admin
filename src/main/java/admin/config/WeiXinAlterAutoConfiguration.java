package admin.config;


import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import admin.common.aspect.ContextLog;
import admin.common.exception.BusinessException;
import admin.common.httpclient.HttpClientUtil;
import admin.common.httpclient.common.HttpConfig;
import admin.common.httpclient.common.Utils;
import admin.common.httpclient.exception.HttpProcessException;



@Configuration
@ConditionalOnProperty(prefix = "spring.weixin",name = "enabled", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "spring.weixin",ignoreUnknownFields = true)
@EnableConfigurationProperties(WeiXinAlterAutoConfiguration.class)
@Order(value=Ordered.LOWEST_PRECEDENCE)
public class WeiXinAlterAutoConfiguration{
//	private static int agentid=1000002;
	private String corpid;
	private String corpsecret;
	private String accessTokenUrl;
	private String seedMessageUrl;
	//https://www.cnblogs.com/shirui/p/7402128.html
	@Bean
	public WeixinAlterUtil getWeixinAlertUtil() {
		return new WeixinAlterUtil();
	}
	
	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public String getCorpsecret() {
		return corpsecret;
	}

	public void setCorpsecret(String corpsecret) {
		this.corpsecret = corpsecret;
	}

	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}
	
	public TextMessage getTestTextMessage() {
		return new TextMessage();
	}
	public Text getTestText() {
		return new Text();
	}
	public static void main(String[] args) {
		WeiXinAlterAutoConfiguration config = new WeiXinAlterAutoConfiguration();
		Utils.debug();
		WeixinAlterUtil weixinAlertUtil = config.getWeixinAlertUtil();
//		weixinAlertUtil.initToken();
		
		TextMessage message = config.getTestTextMessage();
		message.setMsgtype("text");
//		message.setAgentid(agentid);
		
		message.setToparty("2");
		message.setTouser("wanggivemeakiss");
		Text text = config.getTestText();
		text.setContent("测试");
		message.setText(text);
		message.setSafe(0);
		String sendAlter = weixinAlertUtil.send(message);
		
		System.out.println(sendAlter);
		
	}
	/**
	 * @return the seedMessageUrl
	 */
	public String getSeedMessageUrl() {
		return seedMessageUrl;
	}

	/**
	 * @param seedMessageUrl the seedMessageUrl to set
	 */
	public void setSeedMessageUrl(String seedMessageUrl) {
		this.seedMessageUrl = seedMessageUrl;
	}
	public class WeixinAlterUtil{
		private String token="";
		private long start = 0L-System.currentTimeMillis();
		public WeixinAlterUtil(){
		}
		
		public void initToken() {
			try {
				if(System.currentTimeMillis()-start>3600000){
					HttpConfig config = HttpConfig.custom().url(accessTokenUrl+"?corpid="+corpid+"&corpsecret="+corpsecret);
					String returnInfo = HttpClientUtil.get(config);
					WeiXinReturnInfo parseObject = JSONObject.parseObject(returnInfo, new TypeReference<WeiXinReturnInfo>() {});
					if(0==parseObject.getErrcode()) {
						token = parseObject.getAccess_token();
						start = System.currentTimeMillis();
						ContextLog.debug("获取token", token, "initToken", this.getClass());
					}else {
						start = 0L-System.currentTimeMillis();
						throw new BusinessException("获取微信token异常"+parseObject.getErrmsg(),this.getClass());
					}
				}
			} catch (HttpProcessException e) {
				start = 0L-System.currentTimeMillis();
				throw new BusinessException("获取微信token异常",e);
			}
		}
		
		
		public String send(BaseMessage baseMessage) {
			initToken();
			Object json = JSONObject.toJSON(baseMessage);
			String resulet="";
			HttpConfig config = HttpConfig.custom().url(getSeedMessageUrl()+"?access_token="+token).json(json.toString());
			try {
				resulet = HttpClientUtil.post(config);
				start = System.currentTimeMillis();
			} catch (HttpProcessException e) {
				throw new BusinessException("请求微信异常",e);
			}
			return resulet;
		}
	}
	
	
	public static class WeiXinReturnInfo {
		private int errcode;
		private String errmsg;
		private String access_token;
		/**
		 * @return the errcode
		 */
		public int getErrcode() {
			return errcode;
		}
		/**
		 * @param errcode the errcode to set
		 */
		public void setErrcode(int errcode) {
			this.errcode = errcode;
		}
		/**
		 * @return the errmsg
		 */
		public String getErrmsg() {
			return errmsg;
		}
		/**
		 * @param errmsg the errmsg to set
		 */
		public void setErrmsg(String errmsg) {
			this.errmsg = errmsg;
		}
		/**
		 * @return the access_token
		 */
		public String getAccess_token() {
			return access_token;
		}
		/**
		 * @param access_token the access_token to set
		 */
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
	}
	/**
	 * 
	 * @author honghuiwang
	 *
	 */
	public static class BaseMessage {
	    // 否 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向该企业应用的全部成员发送
	    private String touser;  
	    // 否 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	    private String toparty;  
	    // 否 标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	    private String totag;  
	    // 是 消息类型 
	    private String msgtype; 
	    // 是 企业应用的id，整型。可在应用的设置页面查看
	    private int agentid;
	    
	    
	    public String getTouser() {
	        return touser;
	    }
	    public void setTouser(String touser) {
	        this.touser = touser;
	    }
	    public String getToparty() {
	        return toparty;
	    }
	    public void setToparty(String toparty) {
	        this.toparty = toparty;
	    }
	    public String getTotag() {
	        return totag;
	    }
	    public void setTotag(String totag) {
	        this.totag = totag;
	    }
	    public String getMsgtype() {
	        return msgtype;
	    }
	    public void setMsgtype(String msgtype) {
	        this.msgtype = msgtype;
	    }
	    public int getAgentid() {
	        return agentid;
	    }
	    public void setAgentid(int agentid) {
	        this.agentid = agentid;
	    }
	}

	/**
	 * 文本
	 * @author wanghonghui
	 *
	 */
	public static class Text {
	    //是    消息内容，最长不超过2048个字节
	    private String content;

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }
	}
	
	/**
	 * 文本
	 * @author shirayner
	 *
	 */
	public static class TextMessage extends BaseMessage {
		public TextMessage() {}
	    //文本
	    private Text text;
	    //否     表示是否是保密消息，0表示否，1表示是，默认0
	    private int safe;
	    
	    public Text getText() {
	        return text;
	    }
	    public void setText(Text text) {
	        this.text = text;
	    }
	    public int getSafe() {
	        return safe;
	    }
	    public void setSafe(int safe) {
	        this.safe = safe;
	    }
	}

	/**
	 * 图片/语音/文件 媒体文件id，可以调用上传临时素材接口获取
	 * @author honghuiwang
	 *
	 */
	public class Media {
	    //是    图片/语音/文件 媒体文件id，可以调用上传临时素材接口获取
	    private String media_id;

	    public String getMedia_id() {
	        return media_id;
	    }

	    public void setMedia_id(String media_id) {
	        this.media_id = media_id;
	    }    
	}
	
	
	/**
	 * 图片类消息
	 * @author honghuiwang
	 *
	 */
	public class ImgMessage extends BaseMessage {  
	    //图片
	    private Media image ;
	    //否     表示是否是保密消息，0表示否，1表示是，默认0
	    private int safe;
	    
	    public Media getImage() {
	        return image;
	    }
	    public void setImage(Media image) {
	        this.image = image;
	    }
	    public int getSafe() {
	        return safe;
	    }
	    public void setSafe(int safe) {
	        this.safe = safe;
	    }
	}
	
	/**
	 * 语音消息
	 * @author honghuiwang
	 *
	 */
	public class VoiceMessage extends BaseMessage {  
	    //语音
	    private Media voice ;
	    //否     表示是否是保密消息，0表示否，1表示是，默认0
	    private int safe;
	    
	    public Media getVoice() {
	        return voice;
	    }
	    public void setVoice(Media voice) {
	        this.voice = voice;
	    }
	    public int getSafe() {
	        return safe;
	    }
	    public void setSafe(int safe) {
	        this.safe = safe;
	    }
	}
	 /**
	  * 文件消息
	  * @author honghuiwang
	  *
	  */
	public class FileMessage extends BaseMessage {  
	    //文件
	    private Media file ;
	    //否     表示是否是保密消息，0表示否，1表示是，默认0
	    private int safe;
	   
	    public Media getFile() {
	        return file;
	    }
	    public void setFile(Media file) {
	        this.file = file;
	    }
	    public int getSafe() {
	        return safe;
	    }
	    public void setSafe(int safe) {
	        this.safe = safe;
	    }
	}  
	/**
	 * 视频
	 * @author wanghonghui
	 *
	 */
	public class Video {
	    //是     视频媒体文件id，可以调用上传临时素材接口获取
	    private String media_id;    
	    //否     视频消息的标题，不超过128个字节，超过会自动截断
	    private String title;
	    //否     视频消息的描述，不超过512个字节，超过会自动截断
	    private String description;
	    
	    public String getMedia_id() {
	        return media_id;
	    }
	    public void setMedia_id(String media_id) {
	        this.media_id = media_id;
	    }
	    public String getTitle() {
	        return title;
	    }
	    public void setTitle(String title) {
	        this.title = title;
	    }
	    public String getDescription() {
	        return description;
	    }
	    public void setDescription(String description) {
	        this.description = description;
	    }    
	}
	
	/**
	 * 视频消息
	 * @author honghuiwang
	 *
	 */
	public class VideoMessage extends BaseMessage {
	    //视频
	    private Video video ;
	    //否     表示是否是保密消息，0表示否，1表示是，默认0
	    private int safe;
	    
	    public Video getVideo() {
	        return video;
	    }
	    public void setVideo(Video video) {
	        this.video = video;
	    }
	    public int getSafe() {
	        return safe;
	    }
	    public void setSafe(int safe) {
	        this.safe = safe;
	    }
	}
	
	/**
	 * 文本卡片
	 * @author honghuiwang
	 *
	 */
	public class Textcard {
	    //是 标题，不超过128个字节，超过会自动截断
	    private String title;    
	    //是    描述，不超过512个字节，超过会自动截断
	    private String description;    
	    //是    点击后跳转的链接。
	    private String url;
	    public String getTitle() {
	        return title;
	    }
	    public void setTitle(String title) {
	        this.title = title;
	    }
	    public String getDescription() {
	        return description;
	    }
	    public void setDescription(String description) {
	        this.description = description;
	    }
	    public String getUrl() {
	        return url;
	    }
	    public void setUrl(String url) {
	        this.url = url;
	    }    
	}
	
	
	  
	/**
	 * 文本卡片消息
	 * @author honghuiwang
	 *
	 */
	public class TextcardMessage extends BaseMessage {  
	    //文本
	    private Textcard textcard;
	    
	    //btntxt    否    按钮文字。 默认为“详情”， 不超过4个文字，超过自动截断。
	    
	    public Textcard getTextcard() {
	        return textcard;
	    }

	    public void setTextcard(Textcard textcard) {
	        this.textcard = textcard;
	    }
	}
	
	

	/**
	 * 文章
	 * @author honghuiwang
	 *
	 */
	public class Article {
	    //是    标题，不超过128个字节，超过会自动截断
	    private String title;    
	    //否    描述，不超过512个字节，超过会自动截断
	    private String description;    
	    //是    点击后跳转的链接。
	    private String url;    
	    //否    图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080。
	    private String picurl;
	    public String getTitle() {
	        return title;
	    }
	    public void setTitle(String title) {
	        this.title = title;
	    }
	    public String getDescription() {
	        return description;
	    }
	    public void setDescription(String description) {
	        this.description = description;
	    }
	    public String getUrl() {
	        return url;
	    }
	    public void setUrl(String url) {
	        this.url = url;
	    }
	    public String getPicurl() {
	        return picurl;
	    }
	    public void setPicurl(String picurl) {
	        this.picurl = picurl;
	    }    
	}
	
	public class News {
	     //文章列表
	     private List<Article> articles;

	    public List<Article> getArticles() {
	        return articles;
	    }

	    public void setArticles(List<Article> articles) {
	        this.articles = articles;
	    }
	}
	

	  
	/**
	 * 图文消息
	 * @author honghuiwang
	 *
	 */
	public class NewsMessage extends BaseMessage {  
	    //图文
	    private News news;

	    public News getNews() {
	        return news;
	    }

	    public void setNews(News news) {
	        this.news = news;
	    }
	}
	
}
