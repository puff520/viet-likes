package com.likes.common.model.vo.member;

public class AttentionUserVO {

    /**
     * 说明: 粉丝数量
     */
    private Long fansnum;

    /**
     * 说明: 呢称
     */
    private String nickname;

    /**
     * 说明: 用户id
     */
    private Long memid;

    /**
     * 说明: 会员标识号
     */
    private String accno;

    /**
     * 说明: 头像
     */
    private String headimgurl;
    /**
     * 说明: 关注数
     */
    private Integer attentionnum;

    /**
     * 说明: 是否已关注
     */
    private String isattention;

    /**
     * 说明: 性别 1男 2女 4保密
     */
    private Integer sex;

    /**
     * 说明: 描述
     */
    private String describes;

    /**
     * 说明: 当前等级
     */
    private String memlevel;

    /**
     * 说明: 美艳设置(前端已经不需要,去掉查询数据库)
     */
    private String beauty;

    /**
     * 说明: 美艳设置(前端已经不需要,去掉查询数据库)
     */
    private String filter;

    /**
     * 说明: 统计房间总收入
     */
    private Double goldnum;

    /**
     * 说明: 如果是当前房间得主播 就显示主播收到得 总乐比数 和 礼物数
     */
    private Double consumptiongoldnum;

    /**
     * 说明: 如果是当前房间得主播 就显示主播收到得 总乐比数 和 礼物数
     */
    private Integer missgiftnum;

    /**
     * 是否被禁
     */
    private Integer isforbidden;

    private Integer logintype;

    public Integer getLogintype() {
        return logintype;
    }

    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }

    /**
     * 是否被禁
     */
    private Integer ismanage;

    public Long getFansnum() {
        return fansnum;
    }

    public void setFansnum(Long fansnum) {
        this.fansnum = fansnum;
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

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
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

    public String getIsattention() {
        return isattention;
    }

    public void setIsattention(String isattention) {
        this.isattention = isattention;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public String getBeauty() {
        return beauty;
    }

    public void setBeauty(String beauty) {
        this.beauty = beauty;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Double getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(Double goldnum) {
        this.goldnum = goldnum;
    }

    public Double getConsumptiongoldnum() {
        return consumptiongoldnum;
    }

    public void setConsumptiongoldnum(Double consumptiongoldnum) {
        this.consumptiongoldnum = consumptiongoldnum;
    }

    public Integer getMissgiftnum() {
        return missgiftnum;
    }

    public void setMissgiftnum(Integer missgiftnum) {
        this.missgiftnum = missgiftnum;
    }

    public Integer getIsforbidden() {
        return isforbidden;
    }

    public void setIsforbidden(Integer isforbidden) {
        this.isforbidden = isforbidden;
    }

    public Integer getIsmanage() {
        return ismanage;
    }

    public void setIsmanage(Integer ismanage) {
        this.ismanage = ismanage;
    }
}
