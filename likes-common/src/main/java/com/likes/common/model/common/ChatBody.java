package com.likes.common.model.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.likes.common.enums.ChatMsgTypeEnum;
import com.likes.common.model.LoginUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ChatBody {

    // 房间stream 为空表示发送所有房间
    private String stream;
    // 会员标识符
    private String accno;
    // 会员暱称
    private String nickname;
    // 头像
    private String headimg;
    // 会员等级
    private String memlevel;
    // 消息内容
    private String message;
    //参见 ChatMsgTypeEnum
    private Integer type;
    // 禁言禁入时间 分钟
    private Integer forbidtime;

    /** 主播房间id，必须 */
    private Long roomid;

    // 礼物名称
    private String giftname;
    // 礼物图标
    private String gifticon;
    // 礼物数量
    private Integer giftnums;
    // 礼物赠送人暱称
    private String sendnickname;
    // 礼物接收者暱称（主播）
    private String recnickname;
    // 房间管理员 0是 9否
    private Integer ismanage;
    // 是否禁言 0是 9否
    // private Integer isforbidden;
    // 礼物动画json名称
    private String giftjson;

    private String levelSvgaUrl;
    //参数
    private String param;
    //开启聊天室 0开启聊天 9关闭聊天
    private Integer istalk;

    // 是否机器人 0是 9否
    private Integer isRoboter;

    /**
     * 是否联动设备
     */
    private Integer isLinkageDevice;
    /**
     * 震动序列
     */
    private String vibrationSeq;

    /**
     * 禁言过期时间
     */
    private Long expirationTime;
    /**
     * 当前服务时间
     */
    private Long nowTime;

    /**
     * 房间收入
     */
    private Double roomRevenue;

    public Integer getIsTrail() {
        return isTrail;
    }

    public void setIsTrail(Integer isTrail) {
        this.isTrail = isTrail;
    }

    /**
     * 房间热度
     */
    private Double roomHeat;

    private Integer isLiverUser;
    // 是否试玩账号 0 是  9 否
    private Integer isTrail;


    public Integer getIsRoboter() {
        return isRoboter;
    }

    public void setIsRoboter(Integer isRoboter) {
        this.isRoboter = isRoboter;
    }

    public ChatBody(LoginUser user) {
        super();
        if (user != null) {
            this.accno = user.getAccno();
            this.nickname = user.getNickname();
            this.memlevel = user.getMemlevel();
            this.levelSvgaUrl = user.getLevelSvgaUrl();
            this.headimg = user.getHeadimgurl();
        }
    }

    public ChatBody(ChatUser user) {
        super();
        if (user != null) {
            this.accno = user.getAccno();
            this.nickname = user.getNickname();
            this.memlevel = user.getMemlevel();
            this.ismanage = user.getIsmanage();
            this.levelSvgaUrl = user.getLevelSvgaUrl();
            this.headimg = user.getHeadimgurl();
        }
    }

    public ChatBody(String accno, String nickname, String memlevel, String message, Integer type, Integer forbidtime) {
        super();
        this.accno = accno;
        this.nickname = nickname;
        this.memlevel = memlevel;
        this.message = message;
        this.type = type;
        this.forbidtime = forbidtime;
        this.levelSvgaUrl = levelSvgaUrl;
    }

    /**
     * 校验发送指定房间的消息
     *
     * @return
     */
    @JSONField(serialize = false)
    @JsonIgnore
    public boolean isValidRoomMsg() {
        boolean isValidAccno = isNotEmpty(this.getAccno());
        //boolean isValidStream = isNotEmpty(this.getStream());
        boolean isValidRoomId = (null != this.getRoomid() && this.getRoomid() > 0);
        boolean isValidMessage = isNotEmpty(this.getMessage());
        boolean isValidType = null != this.getType();
        boolean isErrMsg = isValidType && ChatMsgTypeEnum.ERR_MSG.getValue().equals(this.getType()) && isValidMessage;
        boolean isAnchorOffline = isValidType && ChatMsgTypeEnum.ANCHOR_OFFLINE.getValue().equals(this.getType()) && isValidRoomId;
        boolean isAnchorForceOffline = isValidType && ChatMsgTypeEnum.ANCHOR_FORCE_OFFLINE.getValue().equals(this.getType()) && isValidRoomId;
        return (isValidAccno && isValidRoomId && isValidMessage && isValidType)
                || isErrMsg || isAnchorOffline || isAnchorForceOffline;
    }

    /**
     * 校验发送所有房间的消息
     *
     * @return
     */
    @JSONField(serialize = false)
    @JsonIgnore
    public boolean isValidErrMsg() {
        boolean isValidAccno = isNotEmpty(this.getAccno());
        boolean isValidMessage = isNotEmpty(this.getMessage());
        boolean isValidType = null != this.getType();
        boolean isErrMsg = isValidType && ChatMsgTypeEnum.ERR_MSG.getValue().equals(this.getType()) && isValidMessage;
        return (isValidAccno && isValidMessage && isValidType) || isErrMsg;
    }

    @JSONField(serialize = false)
    @JsonIgnore
    private boolean isNotEmpty(String str) {
        return null != str && !"".equals(str.trim());
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getGiftjson() {
        return giftjson;
    }

    public void setGiftjson(String giftjson) {
        this.giftjson = giftjson;
    }

    public Integer getIsmanage() {
        return ismanage;
    }

    public void setIsmanage(Integer ismanage) {
        this.ismanage = ismanage;
    }

    public ChatBody() {
        super();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getGifticon() {
        return gifticon;
    }

    public void setGifticon(String gifticon) {
        this.gifticon = gifticon;
    }

    public Integer getGiftnums() {
        return giftnums;
    }

    public void setGiftnums(Integer giftnums) {
        this.giftnums = giftnums;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname;
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

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public Integer getForbidtime() {
        return forbidtime;
    }

    public void setForbidtime(Integer forbidtime) {
        this.forbidtime = forbidtime;
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

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIstalk() {
        return istalk;
    }

    public void setIstalk(Integer istalk) {
        this.istalk = istalk;
    }

    public String getLevelSvgaUrl() {
        return levelSvgaUrl;
    }

    public void setLevelSvgaUrl(String levelSvgaUrl) {
        this.levelSvgaUrl = levelSvgaUrl;
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

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Long getNowTime() {
        return nowTime;
    }

    public void setNowTime(Long nowTime) {
        this.nowTime = nowTime;
    }

    public Integer getIsLiverUser() {
        return isLiverUser;
    }

    public void setIsLiverUser(Integer isLiverUser) {
        this.isLiverUser = isLiverUser;
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
}
