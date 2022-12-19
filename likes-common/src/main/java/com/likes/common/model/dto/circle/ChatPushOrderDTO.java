package com.likes.common.model.dto.circle;

import com.likes.common.model.common.PageBaseInfo;

public class ChatPushOrderDTO extends PageBaseInfo {
	// 标识code
	private String groupCode;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

}
