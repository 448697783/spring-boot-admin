package admin.config;


import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;

@Configuration
@ConditionalOnClass({ StatsDClient.class,NonBlockingStatsDClient.class})
@ConditionalOnProperty(prefix = "spring.stats",name = "enabled", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "spring.stats",ignoreUnknownFields = true)
@EnableConfigurationProperties(StatsdAutoConfiguration.class)
@Order(value=Ordered.LOWEST_PRECEDENCE)
public class StatsdAutoConfiguration{
	private String projectName;
	private String ip;
	private int port;
	private int hostPort;
	private String hostAddress; 
	private String hostMessage;
	@Bean
	public StatsdUtil getNonBlockingStatsDClient(ServerProperties serverProperties) {
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress().replace(".", "-");
		} catch (UnknownHostException e) {
		}
		hostPort = serverProperties.getPort();
		hostMessage = hostAddress+":"+hostPort;
		return new StatsdUtil();
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public class StatsdUtil{
		private StatsDClient statsDClient;
		public StatsdUtil(){
			statsDClient = new NonBlockingStatsDClient(getProjectName(), getIp(), getPort());
		}
		public void recordExecutionTime(String aspect, int timeInMs) {
			statsDClient.recordExecutionTime(aspect+"."+hostMessage, timeInMs);
		}

		public void incrementCounter(String aspect) {
			statsDClient.incrementCounter(aspect+"."+hostMessage);
		}
		
		public void sendAgent(String aspect, int timeInMs) {
			recordExecutionTime(aspect, timeInMs);
			incrementCounter(aspect);
		}
	}

}
