package com.likes.common.model.response;

import com.likes.common.util.robin.Node;

import java.util.List;

public class SysLiveserverResp {

	private String region;

	private List<Node> children;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

}