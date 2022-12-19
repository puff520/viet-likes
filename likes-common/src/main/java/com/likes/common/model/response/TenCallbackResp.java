package com.likes.common.model.response;

import org.apache.commons.lang.StringUtils;

/**
 * @author 阿布 tencentlive callback bean
 */
public class TenCallbackResp {

    private int appid; // 用户 APPID;
    private String app;// 推流域名
    private String appname;// 推流路径;
    private String stream_id; // 直播流名称
    private String channel_id; // 同直播流名称
    private int event_time;// 事件消息产生的 UNIX 时间戳
    private String sequence;// 消息序列号，标识一次推流活动，一次推流活动会产生相同序列号的推流和断流消息
    private String node;// 直播接入点的 IP
    private String user_ip;// 用户推流 IP
    private String stream_param;// 用户推流 URL 所带参数
    private String push_duration;
    private int errcode;// 推断流错误码
    // 1 recv rtmp deleteStream 主播端主动断流
    // 2 recv rtmp closeStream 主播端主动断流
    // 3 recv() return 0 主播端主动断开 TCP 连接
    // 4 recv() return error 主播端 TCP 连接异常
    // 7 rtmp message large than 1M 收到流数据异常
    // 其他 直播服务内部异常 如需处理请联系腾讯商务人员或者提交工单
    private String errmsg;// 推断流错误描述

    private String acctoken;
    // 事件通知签名
    private String sign;
    // 事件通知信息类型：推流事件为1；断流事件为0；录制事件为100；截图事件为200。
    private int event_type;
    // 事件通知签名过期 UNIX 时间戳
    private Long t;

    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getEvent_type() {
        return event_type;
    }

    public void setEvent_type(int event_type) {
        this.event_type = event_type;
    }

    public String getAcctoken() {
        if (!StringUtils.isEmpty(stream_param)) {
            return stream_param.split("&acctoken=")[1];
        }
        return acctoken;
    }

    public void setAcctoken(String acctoken) {
        this.acctoken = acctoken;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getStream_id() {
        return stream_id;
    }

    public void setStream_id(String stream_id) {
        this.stream_id = stream_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public int getEvent_time() {
        return event_time;
    }

    public void setEvent_time(int event_time) {
        this.event_time = event_time;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getStream_param() {
        return stream_param;
    }

    public void setStream_param(String stream_param) {
        this.stream_param = stream_param;
    }

    public String getPush_duration() {
        return push_duration;
    }

    public void setPush_duration(String push_duration) {
        this.push_duration = push_duration;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "TenCallbackResp [appid=" + appid + ", app=" + app + ", appname=" + appname + ", stream_id=" + stream_id + ", channel_id=" + channel_id + ", event_time="
                + event_time + ", sequence=" + sequence + ", node=" + node + ", user_ip=" + user_ip + ", stream_param=" + stream_param + ", push_duration=" + push_duration
                + ", errcode=" + errcode + ", errmsg=" + errmsg + ", acctoken=" + acctoken + ", sign=" + sign + ", event_type=" + event_type + ", t=" + t + "]";
    }

}
