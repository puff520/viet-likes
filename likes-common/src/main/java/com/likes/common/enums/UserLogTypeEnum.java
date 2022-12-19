package com.likes.common.enums;

/**
 * 登录类型枚举
 */
public enum UserLogTypeEnum {
	
    /**
     * 冻结账号
     */
    FREEZE(1, "冻结账号"),
    /**
     * 封锁心水
     */
    BLOCK(2, "封锁心水"),
    /**
     * 批量封锁心水
     */
    MATCHBLOCK(3, "批量封锁心水"),
    /**
     * 解除冻结账号
     */
    RESFREEZE(4, "解除冻结账号"),
    /**
     * 解除封锁心水
     */
    RESBLOCK(5, "解除封锁心水"),
    /**
     * 批量解除封锁心水
     */
    MATCHRESBLOCK(6, "批量解除封锁心水"),
    /**
     * 删除帖子
     */
    DELETERECOMEND(7, "删除帖子"),
    /**
     * 批量删除帖子
     */
    MATCHDELETERECOMEND(8, "批量删除帖子"),
    /**
     * 批量删除心水推荐评论
     */
    MATCHDELETEXSPL(9, "批量删除心水推荐评论"),
    /**
     * 删除心水推荐评论
     */
    DELETEXSPL(10, "删除心水推荐评论"),

    /**
     * 批量解除心水限制
     */
    UNMATCHBLOCK(11, "批量解除心事限制")
    ;

    UserLogTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static UserLogTypeEnum valueOfCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (UserLogTypeEnum type : UserLogTypeEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
