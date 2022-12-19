package com.likes.common.model.request;

import com.likes.common.model.common.PageBaseInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UsersRequest extends PageBaseInfo {

    private String accno;
    private String email;
    private String password;
    private String oldPassword;
    private String newPwd;
    private String newPwdConfirm;
    private String acclogin;
    private String memorgin;
    private String nickname;
    private String memlevel;

    // 短信验证码
    private String smscode;
    // 邀请码
    private String invitecode;
    // 发送类型
    private Integer sendtype;
    private Integer checkstatus;
    private String headimg;
    private String from;

    // 选择地区
    private String areacode;

    // 倒计时 秒
    private Integer countDown;

    private String masstatus;

    private Double latitude;
    private Double longitude;

    private String birthday;

    private Integer sex;

    private Long memid;

    private Date updateTime;

    private String account;

    private Integer isattention;

    private String describes;
    private String registerIp;


    private String consumedate;

    private String startTime;

    private String endTime;
    private String wechat;
    private String deviceCode;

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /* 1 贴文 2 短视频 */
    private Integer type;
    private List<String> rmtypelist;

    private String updateUser;

    /**
     * 贴文/短视频 id
     */
    private String idListStr;
    private List<Long> idList;

    private Double goldnum;

    private List<String> accnolist;

    private List<Integer> changetypeList;
    private String title;

    private BigDecimal noWithdrawalAmount;

    private String clintipadd;

    private String serverIp;

    /**
     * 总消费(打码量)
     */
    private double consumeAmount;

    /**
     * 平台码
     */
    private String platformCode;

    private String imageCode;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    /**
     * 设备
     */
    private String clientPhoneModel;

    public String getClientPhoneModel() {
        return clientPhoneModel;
    }

    public void setClientPhoneModel(String clientPhoneModel) {
        this.clientPhoneModel = clientPhoneModel;
    }

    public double getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public String getClintipadd() {
        return clintipadd;
    }

    public void setClintipadd(String clintipadd) {
        this.clintipadd = clintipadd;
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public BigDecimal getNoWithdrawalAmount() {
        return noWithdrawalAmount;
    }

    public void setNoWithdrawalAmount(BigDecimal noWithdrawalAmount) {
        this.noWithdrawalAmount = noWithdrawalAmount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public String getConsumedate() {
        return consumedate;
    }

    public void setConsumedate(String consumedate) {
        this.consumedate = consumedate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMemorgin() {
        return memorgin;
    }

    public void setMemorgin(String memorgin) {
        this.memorgin = memorgin;
    }

    public String getIdListStr() {
        return idListStr;
    }

    public void setIdListStr(String idListStr) {
        this.idListStr = idListStr;
    }

    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getChangetypeList() {
        return changetypeList;
    }

    public void setChangetypeList(List<Integer> changetypeList) {
        this.changetypeList = changetypeList;
    }

    public List<String> getRmtypelist() {
        return rmtypelist;
    }

    public void setRmtypelist(List<String> rmtypelist) {
        this.rmtypelist = rmtypelist;
    }

    public Double getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(Double goldnum) {
        this.goldnum = goldnum;
    }


    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<String> getAccnolist() {
        return accnolist;
    }

    public void setAccnolist(List<String> accnolist) {
        this.accnolist = accnolist;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public Integer getIsattention() {
        return isattention;
    }

    public void setIsattention(Integer isattention) {
        this.isattention = isattention;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMasstatus() {
        return masstatus;
    }

    public void setMasstatus(String masstatus) {
        this.masstatus = masstatus;
    }

    public Integer getCountDown() {
        return countDown;
    }

    public void setCountDown(Integer countDown) {
        this.countDown = countDown;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public Integer getSendtype() {
        return sendtype;
    }

    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getNewPwdConfirm() {
        return newPwdConfirm;
    }

    public void setNewPwdConfirm(String newPwdConfirm) {
        this.newPwdConfirm = newPwdConfirm;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }
}
