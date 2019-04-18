package admin.modules.alter;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import admin.common.annotation.CtrlLog;
import admin.common.utils.DateUtils;
import admin.common.utils.R;
import admin.config.WeiXinAlterAutoConfiguration.Text;
import admin.config.WeiXinAlterAutoConfiguration.TextMessage;
import admin.config.WeiXinAlterAutoConfiguration.WeiXinReturnInfo;
import admin.config.WeiXinAlterAutoConfiguration.WeixinAlterUtil;
import admin.modules.alter.AlterController.GrafanAlterEntity.EvalMatche;




/**
 * ${comments}
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2018-04-26 15:10:19
 */
//@RestController
public class AlterController {
	@Autowired
	private WeixinAlterUtil weixinAlterUtil;
	
	@Value("${spring.weixin.agentid}")
	private int agentid;
	@Value("${spring.weixin.qbUsers}")
	private String qbUsers;
	@Value("${spring.mail.qbMailUsers}")
	private String qbMailUsers;
	/**
	 * 列表
	 */
	@RequestMapping("/qianbao/waring")
	@CtrlLog
	public R list(@RequestBody(required=false)  GrafanAlterEntity grafanAlterEntity,String username,String password){
		String state = grafanAlterEntity.getState();
		StringBuilder content = new StringBuilder();
		if("alerting".equals(state)) {
			content.append("状态:").append("告警").append("\n")
			.append("监控名称:").append(grafanAlterEntity.getRuleName()).append("\n")
			.append("时间:").append(DateUtils.format(new Date(),"MM-dd HH:mm:ss")).append("\n")
			.append("标准:").append(grafanAlterEntity.getMessage()).append("\n")
			.append("实际如下").append("\n");
			EvalMatche[] evalMatches = grafanAlterEntity.getEvalMatches();
			for (int i = 0; i < evalMatches.length; i++) {
				content.append("*").append(evalMatches[i].getMetric()).append("-->").append(evalMatches[i].getValue()).append("\n");
			}
		}else {
			content.append("状态:").append("解除告警").append("\n")
			.append("监控名称:").append(grafanAlterEntity.getRuleName()).append("\n")
			.append("时间:").append(DateUtils.format(new Date(),"MM-dd HH:mm:ss")).append("\n")
			.append("标准:").append(grafanAlterEntity.getMessage()).append("\n");
		}
		TextMessage testMessage = new TextMessage();
		testMessage.setMsgtype("text");
		testMessage.setAgentid(agentid);
		testMessage.setToparty("2");
		testMessage.setTouser(qbUsers);
		Text text = new Text();
		text.setContent(content.toString());
		testMessage.setText(text);
		testMessage.setSafe(0);
		String send = weixinAlterUtil.send(testMessage);
		
		
		WeiXinReturnInfo parseObject = JSONObject.parseObject(send, new TypeReference<WeiXinReturnInfo>() {});
		if(0==parseObject.getErrcode()) {
			return R.ok();
		}else {
			return R.error(parseObject.getErrmsg());
		}
	}
	
	
	public static class GrafanAlterEntity{
		private EvalMatche[] evalMatches;
		private String message;
		private int ruleId;
		private String ruleName;
		private String ruleUrl;
		private String state;
		public static class EvalMatche{
			private float value;
			private String String;
			private String metric;
			public String getString() {
				return String;
			}
			public void setString(String string) {
				String = string;
			}
			public String getTags() {
				return tags;
			}
			public void setTags(String tags) {
				this.tags = tags;
			}
			private String tags;
			public float getValue() {
				return value;
			}
			public void setValue(float value) {
				this.value = value;
			}
			/**
			 * @return the metric
			 */
			public String getMetric() {
				return metric;
			}
			/**
			 * @param metric the metric to set
			 */
			public void setMetric(String metric) {
				this.metric = metric;
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			return sb.append("GrafanAlterEntity [evalMatches=").append(Arrays.toString(evalMatches)).append(", message=").append(message)
					.append(", ruleId=").append(ruleId).append(", ruleName=").append( ruleName).append( ", ruleUrl=").append( ruleUrl).append("]").toString();
		}
		public EvalMatche[] getEvalMatches() {
			return evalMatches;
		}
		public void setEvalMatches(EvalMatche[] evalMatches) {
			this.evalMatches = evalMatches;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public int getRuleId() {
			return ruleId;
		}
		public void setRuleId(int ruleId) {
			this.ruleId = ruleId;
		}
		public String getRuleName() {
			return ruleName;
		}
		public void setRuleName(String ruleName) {
			this.ruleName = ruleName;
		}
		public String getRuleUrl() {
			return ruleUrl;
		}
		public void setRuleUrl(String ruleUrl) {
			this.ruleUrl = ruleUrl;
		}
		/**
		 * @return the state
		 */
		public String getState() {
			return state;
		}
		/**
		 * @param state the state to set
		 */
		public void setState(String state) {
			this.state = state;
		}
	}
}
