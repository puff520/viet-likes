package com.likes.common.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @author 阿布
 *
 */
@Component
@ConfigurationProperties(prefix = "srs.servers")
public class SrsEntity {
	// 服务器名称
	private String serverName;
	// 服务器地址
	private String serverUrl;
	// 服务器权重
	private Integer weight;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
