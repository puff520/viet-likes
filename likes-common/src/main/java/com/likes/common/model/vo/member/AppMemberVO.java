package com.likes.common.model.vo.member;


import com.likes.common.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lzy
 * @create 2018-08-22 14:50
 **/
public class AppMemberVO {

    private Integer id;

    /**
     * 说明: 账号
     */
    private String account;
    
    private String phone;

    /**
     * 说明: 真实姓名
     */
    private String realName;

    /**
     * 说明: 昵称
     */
    private String nickname;

    /**
     * 说明: 推广码
     */
    private String promotionCode;

    /**
     * 说明: 累计投注（元）
     */
    private BigDecimal betAmount;

    /**
     * 说明: 累计充值（元）
     */
    private BigDecimal payAmount;

    /**
     * 说明: 累计提现（元）
     */
    private BigDecimal withdrawalAmount;

    /**
     * 说明: 当前余额（元）
     */
    private BigDecimal balance;

    /**
     * 说明: 待开奖金额（元）
     */
    private BigDecimal waitAmount;
    /**
     * 说明: 不可提余额（元）
     */
    private BigDecimal noWithdrawalAmount;
    /**
     * 说明: 注册时间 yyyy-MM-dd HH:mm:ss
     */
    private String createTime;

    /**
     * 说明: 注册ip
     */
    private String registerIp;

    /**
     * 说明: 注册地址
     */
    private String registerAddress;

    /**
     * 说明: 分享人,对应app_member的account
     */
    private String shareAccount;

    /**
     * 说明: 登录时间 yyyy-MM-dd HH:mm:ss
     */
    private String loginTime;

    /**
     * 说明: 登录ip
     */
    private String loginIp;

    /**
     * 说明: 登录地址
     */
    private String loginAddress;

    /**
     * 说明: 登录类型
     */
    private String loginClient;

    /**
     * 充值层级
     */
    private String topUpGrade;

    /**
     * 登录状态:是否在线
     */
    private Integer loginStatus;

    /**
     * 说明: 对应vip_grade的id
     */
    private Integer vipId;

    /**
     * vip等级名称
     */
    private String vip;

    /**
     * 说明: 投注状态: 0,不允许;1,允许
     */
    private Integer betStatus;

    /**
     * 说明: 返水状态: 0,不允许;1,允许
     */
    private Integer backwaterStatus;

    /**
     * 说明: 聊天状态: 0,不允许;1,允许
     */
    private Integer chatStatus;

    /**
     * 说明: 晒单状态: 0,不允许;1,允许
     */
    private Integer shareOrderStatus;

    /**
     * 说明: 冻结状态: 0,不冻结;1,冻结
     */
    private Integer freezeStatus;

    /**
     * 说明: 心水推荐状态: 0,不允许;1,允许
     */
    private Integer lhcxsStatus;

    private String address;

    /**
     * 登录设备
     */
    private String loginEquipment;

    /** 登录次数 */
    private int loginCount;

    private String remark;

    private Long payLevelId;

    /** 用户类型 */
    private Integer userType;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 说明: 登录时间 yyyy-MM-dd HH:mm:ss
     */
    private String offlineTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLhcxsStatus() {
        return lhcxsStatus;
    }

    public void setLhcxsStatus(Integer lhcxsStatus) {
        this.lhcxsStatus = lhcxsStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getNoWithdrawalAmount() {
        return noWithdrawalAmount;
    }

    public void setNoWithdrawalAmount(BigDecimal noWithdrawalAmount) {
        this.noWithdrawalAmount = noWithdrawalAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getShareAccount() {
        return shareAccount;
    }

    public void setShareAccount(String shareAccount) {
        this.shareAccount = shareAccount;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(String loginClient) {
        this.loginClient = loginClient;
    }

    public String getTopUpGrade() {
        return topUpGrade;
    }

    public void setTopUpGrade(String topUpGrade) {
        this.topUpGrade = topUpGrade;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public Integer getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(Integer betStatus) {
        this.betStatus = betStatus;
    }

    public Integer getBackwaterStatus() {
        return backwaterStatus;
    }

    public void setBackwaterStatus(Integer backwaterStatus) {
        this.backwaterStatus = backwaterStatus;
    }

    public Integer getChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(Integer chatStatus) {
        this.chatStatus = chatStatus;
    }

    public Integer getShareOrderStatus() {
        return shareOrderStatus;
    }

    public void setShareOrderStatus(Integer shareOrderStatus) {
        this.shareOrderStatus = shareOrderStatus;
    }

    public Integer getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public String getLoginEquipment() {
        return loginEquipment;
    }

    public void setLoginEquipment(String loginEquipment) {
        this.loginEquipment = loginEquipment;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public String getOfflineTime() {
        //若缓存中无离线时间，则取上一次登录时间 + 30分钟(默认 session 会话过期时间)
        if (this.offlineTime == null || "".equals(this.offlineTime.trim())) {
            Date offlineDate = DateUtils.addMinutes(this.loginTime, 30);
            return DateUtils.formatDate(offlineDate);
        } else {
            return offlineTime;
        }
    }

    public void setOfflineTime(String offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Long getPayLevelId() {
        return payLevelId;
    }

    public void setPayLevelId(Long payLevelId) {
        this.payLevelId = payLevelId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public BigDecimal getWaitAmount() {
        return waitAmount;
    }

    public void setWaitAmount(BigDecimal waitAmount) {
        this.waitAmount = waitAmount;
    }
}
