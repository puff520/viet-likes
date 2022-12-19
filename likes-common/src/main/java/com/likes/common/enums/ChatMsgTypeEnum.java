package com.likes.common.enums;

/**
 * 消息类型枚举
 */
public enum ChatMsgTypeEnum {

    NORMAL(1, "普通消息"),
    OPERATE(2, "操作消息"),
    NOTICE(3, "公告提示"),
    SM_GIFT(4, "赠送小礼物"),
    BG_GIFT(5, "赠送大礼物"),
    FORBID_CHAT(6, "禁言消息"),
    FORBID_PLAY(7, "禁播消息"),
    LOGIN(8, "登录消息"),
    CANCEL_ADMIN(9, "取消设置管理员"),
    SET_ADMIN(10, "设置管理员"),
    ANCHOR_OFFLINE(11, "主播下线通知"),
    ANCHOR_WECHAT_MSG(12, "主播发送微信通知"),
    LOTTERY_WIN(13, "彩票中奖"),
    ORDER_FOLLOW(14, "跟投"),
    FORBID_OUT(15, "踢出"),
    CHAT_STATUS(16, "聊天室状态"),
    ANCHOR_FORCE_OFFLINE(17, "主播强制下线通知"),
    ERR_MSG(120, "错误提示"),
    NOTALK_LEVEL(18, "未达到发言等级"),
    EXIT_ROOM(19, "会员退出直播房间"),
    RELIEVE_FORBID_CHAT(20, "解除禁言消息"),
    LIVE_TOAST(22, "吐司提醒");


    private Integer value;
    private String desc;

    ChatMsgTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
