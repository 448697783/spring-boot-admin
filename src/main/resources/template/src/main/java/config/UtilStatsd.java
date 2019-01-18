package com.sohu.wallet.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sohu.wallet.beans.config.StatsdConfig;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;

@Component
public class UtilStatsd implements ApplicationListener<EmbeddedServletContainerInitializedEvent>{
	private StatsDClient statsdc;

	private StatsdConfig cofnig;
	
	private String projectName;
	private int serverPort; 
	
	@Autowired
	private UtilStatsd(StatsdConfig cofnig) {
			this.cofnig = cofnig;
	}
	public void recordExecutionTime(String aspect, int timeInMs) {
		statsdc.recordExecutionTime(aspect+"."+cofnig.getHostIp(), timeInMs);
	}

	public void incrementCounter(String aspect) {
		statsdc.incrementCounter(aspect+"."+cofnig.getHostIp());
	}
	@Override
	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
		this.serverPort = event.getEmbeddedServletContainer().getPort();
		projectName = cofnig.getProjectName()+"."+IPUtils.getAddress()+"."+serverPort;
		statsdc = new NonBlockingStatsDClient(cofnig.getProjectName(), cofnig.getIp(), cofnig.getPort());
	}

}
