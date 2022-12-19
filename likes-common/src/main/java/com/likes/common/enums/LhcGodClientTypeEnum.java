package com.likes.common.enums;

/**
 * 客户端版本类型枚举
 *
 */
public enum LhcGodClientTypeEnum {

	/**
	 * 耀眼黑
	 */
	OBSIDIAN_BLACK(1, "Obsidian black"),

	/**
	 * 珍珠白
	 */
	PEARL_WHITE(2, "pearl white");

	private Integer code;
	private String name;


	LhcGodClientTypeEnum(Integer code, String name) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


}
