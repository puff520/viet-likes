package com.likes.common.model;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author abu
 * @version 1.0
 * @Description APP用户登录用户信息
 * @revise
 * @time 2018年6月6日 下午4:51:12
 */
public class LoginUser  implements Serializable {

    private String acctoken;
    /**
     * 用户id
     */
    private Long memid;
    /**
     * 会员标识号
     */
    private String accno;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 真是姓名
     */
    private String memname;
    /**
     * 手机号码
     */
    private String mobileno;
    /**
     * 会员等级
     */
    private String memlevel;

    /**
     * 会员等级序号
     */
    private Integer levelSeq;

    /**
     * 头像图片地址
     */
    private String headimgurl;
    /**
     * 会员账号--登录用
     */
    private String acclogin;
    /**
     * 账号类型：  普通会员1      主播2   家族长3   运营后台管理员8    第三方登录7   服务注册中心管理员9  聚合站点后台管理员10   // 11 试玩账号
     */
    private Integer logintype;

    /**
     * 登录ip地址
     */
    private String clintipadd;
    /**
     * 推荐码
     */
    private String recomcode;
    // 缓存图片
    private List<String> cacheimages;
    // 登录国家
    private String region;
    // 上级
    private String higher;


    // 后台
    private String bduserid;
    private String bdusername;
    private String phoneno;
    private String email;
    private Long sysroleid;
    private String rolename;
    private String levelSvgaUrl;
    private JSONArray functions;
    /**
     * 主表id
     */
    private Long loginid;

    /**
     * 登录来源 IOS 安卓 h5 web
     */
    private String sourceType;

    /**
     * 是否已被要请 true 邀请过, false 没有
     */
    private Boolean invitationStatus;

    /**
     * 主播端平台配置信息
     */
    private Map<String, Object> basPlatformInfo;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Long getSysroleid() {
        return sysroleid;
    }

    public void setSysroleid(Long sysroleid) {
        this.sysroleid = sysroleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public JSONArray getFunctions() {
        return functions;
    }

    public void setFunctions(JSONArray functions) {
        this.functions = functions;
    }

    public List<String> getCacheimages() {
        return cacheimages;
    }

    public void setCacheimages(List<String> cacheimages) {
        this.cacheimages = cacheimages;
    }


    public String getClintipadd() {
        return clintipadd;
    }

    public void setClintipadd(String clintipadd) {
        this.clintipadd = clintipadd;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public String getRecomcode() {
        return recomcode;
    }

    public void setRecomcode(String recomcode) {
        this.recomcode = recomcode;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public String getAcctoken() {
        return acctoken;
    }

    public void setAcctoken(String acctoken) {
        this.acctoken = acctoken;
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

    public String getMemname() {
        return memname;
    }

    public void setMemname(String memname) {
        this.memname = memname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public Integer getLogintype() {
        return logintype;
    }

    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }

    public String getBduserid() {
        return bduserid;
    }

    public void setBduserid(String bduserid) {
        this.bduserid = bduserid;
    }

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getLoginid() {
        return loginid;
    }

    public void setLoginid(Long loginid) {
        this.loginid = loginid;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Boolean getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(Boolean invitationStatus) {
        this.invitationStatus = invitationStatus;
    }

    public String getLevelSvgaUrl() {
        return levelSvgaUrl;
    }

    public void setLevelSvgaUrl(String levelSvgaUrl) {
        this.levelSvgaUrl = levelSvgaUrl;
    }

    public Map<String, Object> getBasPlatformInfo() {
        return basPlatformInfo;
    }

    public void setBasPlatformInfo(Map<String, Object> basPlatformInfo) {
        this.basPlatformInfo = basPlatformInfo;
    }

    public Integer getLevelSeq() {
        return levelSeq;
    }

    public void setLevelSeq(Integer levelSeq) {
        this.levelSeq = levelSeq;
    }

    public String getHigher() {
        return higher;
    }

    public void setHigher(String higher) {
        this.higher = higher;
    }
}
