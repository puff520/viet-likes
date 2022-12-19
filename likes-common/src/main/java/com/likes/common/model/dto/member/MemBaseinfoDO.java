package com.likes.common.model.dto.member;

import java.math.BigDecimal;
import java.util.Date;

public class MemBaseinfoDO {

    private String uniqueId;

    private String accno;

    private String nickname;

    private String memname;

    private String mobileno;

    private Integer sex;

    private String idcardno;

    private String idcardfront;

    private String idcardback;

    private String headimg;

    private String idcardfronturl;

    private String idcardbackurl;

    private String wechat;

    private String headimgurl;

    /**
     * 关注数
     */
    private Integer attentionnum;
    /**
     * 粉丝数
     */
    private Long fansnum;

    private Integer collectionnum;// 收藏数
    private Integer resourcesnum;// 资源数
    private String memlevel;
    private String refRecomcode;
    private Integer isanchor;

    private String acclogin;

    private BigDecimal goldnum;

    /**
     * 用户id
     */
    private Long memid;

    /**
     * 说明: 出生日期
     */
    private Date birthday;

    /**
     * 描述
     */
    private String describes;

    /**
     * 出生日期字符串(前端展示)
     */
    private String birth;

    /**
     * 登录类型
     */
    private Integer logintype;

    /**
     * 是否被邀请过
     */
    private Boolean invitationStatus;

    public Boolean getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(Boolean invitationStatus) {
        this.invitationStatus = invitationStatus;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
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

    public Integer getCollectionnum() {
        return collectionnum;
    }

    public void setCollectionnum(Integer collectionnum) {
        this.collectionnum = collectionnum;
    }

    public Integer getResourcesnum() {
        return resourcesnum;
    }

    public void setResourcesnum(Integer resourcesnum) {
        this.resourcesnum = resourcesnum;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public String getRefRecomcode() {
        return refRecomcode;
    }

    public void setRefRecomcode(String refRecomcode) {
        this.refRecomcode = refRecomcode;
    }

    public Integer getIsanchor() {
        return isanchor;
    }

    public void setIsanchor(Integer isanchor) {
        this.isanchor = isanchor;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }
	/*private List<MemCertification> certifications;

	public List<MemCertification> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<MemCertification> certifications) {
		this.certifications = certifications;
	}*/

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getIdcardfronturl() {
        return idcardfronturl;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public void setIdcardfronturl(String idcardfronturl) {
        this.idcardfronturl = idcardfronturl;
    }

    public String getIdcardbackurl() {
        return idcardbackurl;
    }

    public void setIdcardbackurl(String idcardbackurl) {
        this.idcardbackurl = idcardbackurl;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public String getIdcardfront() {
        return idcardfront;
    }

    public void setIdcardfront(String idcardfront) {
        this.idcardfront = idcardfront;
    }

    public String getIdcardback() {
        return idcardback;
    }

    public void setIdcardback(String idcardback) {
        this.idcardback = idcardback;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public Long getFansnum() {
        return fansnum;
    }

    public void setFansnum(Long fansnum) {
        this.fansnum = fansnum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Integer getLogintype() {
        return logintype;
    }

    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
