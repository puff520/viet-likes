package com.likes.common.model.vo.roominfo;

/**
 * @ClassName EndLiveVO
 * @Description 结束直播封装数据
 * @Author ping
 * @Date 2020/6/18 17:51
 * @Version 1.0
 **/
public class EndLiveVO {

    /**
     * 直播观看人次
     */
    private Integer seenum;
    /**
     * 最后收入
     */
    private Double quantity;
    /**
     * 最后热度
     */
    private Double hotnum;
    /**
     * 房间封面
     */
    private String coverurl;
    /**
     * 是否关注
     */
    private String isattention;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户id
     */
    private Long memid;
    /**
     * 用户性别
     */
    private Integer sex;
    /**
     * 用户头像
     */
    private String headimgurl;
    /**
     * 本场直播关注数
     */
    private Integer attentionnum;
    /**
     * 直播上线时间
     */
    private Long onlinedate;
    /**
     * 直播下线时间
     */
    private Long offlinedate;
    /**
     * 直播下线时间 展示前端结束页
     */
    private String offlinedateStr;

    public Integer getSeenum() {
        return seenum;
    }

    public void setSeenum(Integer seenum) {
        this.seenum = seenum;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getHotnum() {
        return hotnum;
    }

    public void setHotnum(Double hotnum) {
        this.hotnum = hotnum;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getIsattention() {
        return isattention;
    }

    public void setIsattention(String isattention) {
        this.isattention = isattention;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getAttentionnum() {
        return attentionnum;
    }

    public void setAttentionnum(Integer attentionnum) {
        this.attentionnum = attentionnum;
    }

    public Long getOnlinedate() {
        return onlinedate;
    }

    public void setOnlinedate(Long onlinedate) {
        this.onlinedate = onlinedate;
    }

    public Long getOfflinedate() {
        return offlinedate;
    }

    public void setOfflinedate(Long offlinedate) {
        this.offlinedate = offlinedate;
    }

    public String getOfflinedateStr() {
        return offlinedateStr;
    }

    public void setOfflinedateStr(String offlinedateStr) {
        this.offlinedateStr = offlinedateStr;
    }
}
