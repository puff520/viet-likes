package com.likes.common.model.vo.gift;


import java.math.BigDecimal;

/**
 * @ClassName SendGiftVO
 * @Description 发送礼物封装要发送礼物的消息数据
 * @Author ping
 * @Date 2020/6/9 17:51
 * @Version 1.0
 **/
public class SendGiftVO {

    /**
     * 礼物数量
     */
    private Long giftnums;
    /**
     * 消息流
     */
    private String stream;
    /**
     * 礼物类型
     */
    private Integer type;
    /**
     * 账户标识
     */
    private String accno;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String headimg;
    /**
     * 用户等级
     */
    private String memlevel;
    /**
     * 房间ID
     */
    private Long roomid;
    /**
     * 礼物名称
     */
    private String giftname;
    /**
     * 礼物动效
     */
    private String giftjson;
    /**
     * 礼物图标
     */
    private String gifticon;
    /**
     * 发送人昵称
     */
    private String sendnickname;
    /**
     * 接收人昵称
     */
    private String recnickname;
    /**
     * 用户金币数
     */
    private BigDecimal usergoldnum;
    /**
     * 是否当前送礼物人是否房间管理员
     */
    private Integer ismanage;
    /**
     * 暂且不用
     */
    private String wx;

    /**
     * 房间收入(此时直播间)
     */
    private Double roomRevenue;
    /**
     * 房间热度(此时直播间)
     */
    private Double roomHeat;

    /**
     * 是否联动设备
     */
    private Integer isLinkageDevice;
    /**
     * 震动序列
     */
    private String vibrationSeq;
    /*
     * 账号类型
     * */
    private String logintype;

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public Integer getIsLinkageDevice() {
        return isLinkageDevice;
    }

    public void setIsLinkageDevice(Integer isLinkageDevice) {
        this.isLinkageDevice = isLinkageDevice;
    }

    public String getVibrationSeq() {
        return vibrationSeq;
    }

    public void setVibrationSeq(String vibrationSeq) {
        this.vibrationSeq = vibrationSeq;
    }

    public Long getGiftnums() {
        return giftnums;
    }

    public void setGiftnums(Long giftnums) {
        this.giftnums = giftnums;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname;
    }

    public String getGiftjson() {
        return giftjson;
    }

    public void setGiftjson(String giftjson) {
        this.giftjson = giftjson;
    }

    public String getGifticon() {
        return gifticon;
    }

    public void setGifticon(String gifticon) {
        this.gifticon = gifticon;
    }

    public String getSendnickname() {
        return sendnickname;
    }

    public void setSendnickname(String sendnickname) {
        this.sendnickname = sendnickname;
    }

    public String getRecnickname() {
        return recnickname;
    }

    public void setRecnickname(String recnickname) {
        this.recnickname = recnickname;
    }


    public Integer getIsmanage() {
        return ismanage;
    }

    public void setIsmanage(Integer ismanage) {
        this.ismanage = ismanage;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public BigDecimal getUsergoldnum() {
        return usergoldnum;
    }

    public void setUsergoldnum(BigDecimal usergoldnum) {
        this.usergoldnum = usergoldnum;
    }

    public Double getRoomRevenue() {
        return roomRevenue;
    }

    public void setRoomRevenue(Double roomRevenue) {
        this.roomRevenue = roomRevenue;
    }

    public Double getRoomHeat() {
        return roomHeat;
    }

    public void setRoomHeat(Double roomHeat) {
        this.roomHeat = roomHeat;
    }
}
