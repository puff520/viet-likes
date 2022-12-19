package com.likes.common.model.vo;

import com.alibaba.fastjson.JSONObject;

public class ThirdChatGroupVo {
    /** 聊天室标识码 */
    private String chatGroupCode;

    /** 聊天室中文描述 */
    private String chatGroupName;

    /** 副标题 */
    private String subTitle;

    /** 聊天室号，群号，群id */
    private String gid;

    /** 接口签名 */
    private String sign;

    /** 头像 */
    private String head;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getChatGroupCode() {
        return chatGroupCode;
    }

    public void setChatGroupCode(String chatGroupCode) {
        this.chatGroupCode = chatGroupCode;
    }

    public String getChatGroupName() {
        return chatGroupName;
    }

    public void setChatGroupName(String chatGroupName) {
        this.chatGroupName = chatGroupName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
