package com.likes.common.model.vo.roominfo;

import com.likes.common.model.dto.InfSysnoticeDO;
import com.likes.common.model.response.UserResp;

import java.util.Date;
import java.util.List;

/**
 * @ClassName roomInfoVO
 * @Description 封装直播间房间信息
 * @Author ping
 * @Date 2020/6/18 17:51
 * @Version 1.0
 **/
public class RoomInfoVO {

    /**
     * 彩种编号
     */
    private Integer lotkindid;
    /**
     * 彩种名称
     */
    private String lotname;
    /**
     * 封盘时间
     */
    private Integer stoptime;
    /**
     * 彩种CODE
     */
    private Integer lotcode;
    /**
     * h5地址
     */
    private String h5Short;
    /**
     * APP 下载地址
     */
    private String appdownurl;
    /**
     * 点赞数
     */
    private Long praisenum;
    /**
     * 分享数
     */
    private Long sharenum;
    /**
     * 浏览数
     */
    private Long viewnum;
    /**
     * 房间主题
     */
    private String roomtheme;
    /**
     * 房间封面
     */
    private String coverurl;
    /**
     * 用户ID
     */
    private Long memid;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String headimgurl;
    /**
     * 直播间邀请码
     */
    private String recomcode;
    /**
     * 会员邀请码
     */
    private String invitecode;
    /**
     * 邀请URL
     */
    private String inviturl;
    /**
     * 是否关注
     */
    private String isattention;
    /**
     * 刷新房间频率
     */
    private String frequency;
    /**
     * 用户等级特效
     */
    private String memlevelSvgaUrl;
    /**
     * 用户等级
     */
    private String memlevel;
    /**
     * 房间系统公告
     */
    private List<InfSysnoticeDO> roomnotemsg;
    /**
     * 当前房间收入
     */
    private Double roomRevenue;
    /**
     * 当前房间热度
     */
    private Double roomHeat;
    /**
     * 是否禁言 0 是 9 否
     */
    private Integer Isforbidden;
    /**
     * 禁言过期时间
     */
    private Long expirationTime;
    /**
     * 当前服务时间
     */
    private Long nowTime;

    /**
     * 等级榜
     */
    private List<UserResp> top;

    /**
     * 是否房管 0是 9否
     */
    private Integer isusermanage;

    public Integer getLotkindid() {
        return lotkindid;
    }

    public void setLotkindid(Integer lotkindid) {
        this.lotkindid = lotkindid;
    }

    public String getLotname() {
        return lotname;
    }

    public void setLotname(String lotname) {
        this.lotname = lotname;
    }

    public Integer getStoptime() {
        return stoptime;
    }

    public void setStoptime(Integer stoptime) {
        this.stoptime = stoptime;
    }

    public Integer getLotcode() {
        return lotcode;
    }

    public void setLotcode(Integer lotcode) {
        this.lotcode = lotcode;
    }

    public String getH5Short() {
        return h5Short;
    }

    public void setH5Short(String h5Short) {
        this.h5Short = h5Short;
    }

    public String getAppdownurl() {
        return appdownurl;
    }

    public void setAppdownurl(String appdownurl) {
        this.appdownurl = appdownurl;
    }

    public Long getPraisenum() {
        return praisenum;
    }

    public void setPraisenum(Long praisenum) {
        this.praisenum = praisenum;
    }

    public Long getSharenum() {
        return sharenum;
    }

    public void setSharenum(Long sharenum) {
        this.sharenum = sharenum;
    }

    public Long getViewnum() {
        return viewnum;
    }

    public void setViewnum(Long viewnum) {
        this.viewnum = viewnum;
    }

    public String getRoomtheme() {
        return roomtheme;
    }

    public void setRoomtheme(String roomtheme) {
        this.roomtheme = roomtheme;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getRecomcode() {
        return recomcode;
    }

    public void setRecomcode(String recomcode) {
        this.recomcode = recomcode;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public String getInviturl() {
        return inviturl;
    }

    public void setInviturl(String inviturl) {
        this.inviturl = inviturl;
    }

    public String getIsattention() {
        return isattention;
    }

    public void setIsattention(String isattention) {
        this.isattention = isattention;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getMemlevelSvgaUrl() {
        return memlevelSvgaUrl;
    }

    public void setMemlevelSvgaUrl(String memlevelSvgaUrl) {
        this.memlevelSvgaUrl = memlevelSvgaUrl;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public List<InfSysnoticeDO> getRoomnotemsg() {
        return roomnotemsg;
    }

    public void setRoomnotemsg(List<InfSysnoticeDO> roomnotemsg) {
        this.roomnotemsg = roomnotemsg;
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

    public Integer getIsforbidden() {
        return Isforbidden;
    }

    public void setIsforbidden(Integer isforbidden) {
        Isforbidden = isforbidden;
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

    public List<UserResp> getTop() {
        return top;
    }

    public void setTop(List<UserResp> top) {
        this.top = top;
    }

    public Integer getIsusermanage() {
        return isusermanage;
    }

    public void setIsusermanage(Integer isusermanage) {
        this.isusermanage = isusermanage;
    }
}
