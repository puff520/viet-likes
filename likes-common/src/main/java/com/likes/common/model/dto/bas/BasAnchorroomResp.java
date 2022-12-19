package com.likes.common.model.dto.bas;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BasAnchorroomResp {
    private Long roomid;

    private Long lotkindid;

    private Long channelid;

    private String accno;

    private String roomname;

    private String roomtheme;

    private String cover;

    private String coverurl;

    private String manageaccno;

    private Long praisenum;

    private Long sharenum;

    private Long viewnum;

    private Long onlinenum;

    private Integer roomstatus;

    private Integer istalk;

    private String clientid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlinedate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offlinedate;

    private String nickname;
    private Double hotnum;
    // 主播总收入
    private Integer allquantity;
    private String channelname;
    // 房管
    private List<String> managelist;

    private String lotteryname;

    private Integer toolstatus;
    /**
     * 累计直播时长（昨天以前）
     */
    private double totalTime;

    /**
     * 今日直播时长(今天)
     */
    private double todayTime;

    /**
     * 昨日直播时长(昨日)
     */
    private double yesterDayTime;

    /**
     * 最后一次观看人次
     */
    private Integer renci;

    /**
     * 最后一次收入
     */
    private BigDecimal zuihoushouru;

    /**
     * 字段: bas_anchorroom.client_ip<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 直播ip
     *
     * @mbggenerated
     */
    private String clientIp;

    /**
     * 字段: bas_anchorroom.client_address<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 80<br/>
     * 说明: 房间地址
     *
     * @mbggenerated
     */
    private String clientAddress;

    /**
     * 字段: order_bet_record.bet_amount<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 投注金额
     *
     * @mbggenerated
     */
    private BigDecimal betAmount;

    /**
     * 所属家族
     */
    private String familyman;

    /**
     * 所属家族名称
     */
    private String familyname;

    /**
     * 登录手机号
     */
    private String mobileno;
    /**
     * 礼物收入
     */
    private double giftAmt;

    /**
     * 累计直播礼物收入
     * */
    private double totalGiftAmt;

    /**
     * 今日直播礼物收入(今天)
     */
    private double todayGiftAmt;

    /**
     * 昨日直播礼物收入(昨日)
     */
    private double yesterDayGiftAmt;

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public double getGiftAmt() {
        return giftAmt;
    }

    public void setGiftAmt(double giftAmt) {
        this.giftAmt = giftAmt;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getFamilyman() {
        return familyman;
    }

    public void setFamilyman(String familyman) {
        this.familyman = familyman;
    }

    public Integer getToolstatus() {
        return toolstatus;
    }

    public void setToolstatus(Integer toolstatus) {
        this.toolstatus = toolstatus;
    }

    public Long getLotkindid() {
        return lotkindid;
    }

    public void setLotkindid(Long lotkindid) {
        this.lotkindid = lotkindid;
    }

    public String getLotteryname() {
        return lotteryname;
    }

    public void setLotteryname(String lotteryname) {
        this.lotteryname = lotteryname;
    }

    public List<String> getManagelist() {
        return managelist;
    }

    public void setManagelist(List<String> managelist) {
        this.managelist = managelist;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public Integer getAllquantity() {
        return allquantity;
    }

    public void setAllquantity(Integer allquantity) {
        this.allquantity = allquantity;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Double getHotnum() {
        return hotnum;
    }

    public void setHotnum(Double hotnum) {
        this.hotnum = hotnum;
    }

    public String getCoverurl() {
        return cover;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getRoomtheme() {
        return roomtheme;
    }

    public void setRoomtheme(String roomtheme) {
        this.roomtheme = roomtheme;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getManageaccno() {
        return manageaccno;
    }

    public void setManageaccno(String manageaccno) {
        this.manageaccno = manageaccno;
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

    public Long getOnlinenum() {
        return onlinenum;
    }

    public void setOnlinenum(Long onlinenum) {
        this.onlinenum = onlinenum;
    }

    public Integer getRoomstatus() {
        return roomstatus;
    }

    public void setRoomstatus(Integer roomstatus) {
        this.roomstatus = roomstatus;
    }

    public Integer getIstalk() {
        return istalk;
    }

    public void setIstalk(Integer istalk) {
        this.istalk = istalk;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public Date getOnlinedate() {
        return onlinedate;
    }

    public void setOnlinedate(Date onlinedate) {
        this.onlinedate = onlinedate;
    }

    public Date getOfflinedate() {
        return offlinedate;
    }

    public void setOfflinedate(Date offlinedate) {
        this.offlinedate = offlinedate;
    }

    public Integer getRenci() {
        return renci;
    }

    public void setRenci(Integer renci) {
        this.renci = renci;
    }

    public BigDecimal getZuihoushouru() {
        return zuihoushouru;
    }

    public void setZuihoushouru(BigDecimal zuihoushouru) {
        this.zuihoushouru = zuihoushouru;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getTodayTime() {
        return todayTime;
    }

    public void setTodayTime(double todayTime) {
        this.todayTime = todayTime;
    }

    public double getYesterDayTime() {
        return yesterDayTime;
    }

    public void setYesterDayTime(double yesterDayTime) {
        this.yesterDayTime = yesterDayTime;
    }

    public double getTotalGiftAmt() {
        return totalGiftAmt;
    }

    public void setTotalGiftAmt(double totalGiftAmt) {
        this.totalGiftAmt = totalGiftAmt;
    }

    public double getTodayGiftAmt() {
        return todayGiftAmt;
    }

    public void setTodayGiftAmt(double todayGiftAmt) {
        this.todayGiftAmt = todayGiftAmt;
    }

    public double getYesterDayGiftAmt() {
        return yesterDayGiftAmt;
    }

    public void setYesterDayGiftAmt(double yesterDayGiftAmt) {
        this.yesterDayGiftAmt = yesterDayGiftAmt;
    }
}
