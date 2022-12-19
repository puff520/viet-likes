package com.likes.common.model.dto;

/**
 * 
 * @author 阿布 牛人榜
 *
 */
public class GreatPerson {

	private String nickname;
	// 类型 : 1才气值 2胜率 3财富值
	private int gptype;
	// 胜率% /才气/财富值 #.##
	private String gpval;
	private String headimg;

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGptype() {
		return gptype;
	}

	public void setGptype(int gptype) {
		this.gptype = gptype;
	}

	public String getGpval() {
		return gpval;
	}

	public void setGpval(String gpval) {
		this.gpval = gpval;
	}

}
