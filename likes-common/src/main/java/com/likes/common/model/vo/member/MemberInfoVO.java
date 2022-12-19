package com.likes.common.model.vo.member;

import java.math.BigDecimal;

/**
 * 用户账户信息VO
 *
 * @author lzy
 * @create 2018-08-10 16:05
 **/
public class MemberInfoVO {

    /** 账号 */
    private String account;

    /** 昵称 */
    private String nickname;

    /** vip等级 */
    private String vip;

    /** 余额 */
    private BigDecimal balance;

    /** 等待开奖金额 */
    private BigDecimal waitAmount;

    /** 可提现额度 */
    private BigDecimal withdrawalAmount;

    /** 不可提额度 */
    private BigDecimal noWithdrawalAmount;

    /** 可提现余额 */
    private BigDecimal withdrawalRemainder;

    /** 性别 */
    private Integer sex;

    /** 说明: 会员头像 */
    private String heads;

    /** 真实姓名 */
    private String realName;

    /** 生日 */
    private String birthday;

    /** 上次登录时间 */
    private String loginTime;

    /** 上次登录地区 */
    private String region;

    /** 上次登录ip */
    private String ip;

    /** 支付密码 */
    private String payPassword;

    /** 推广码 */
    private String promotionCode;

    private String qq;
    private String phone;

    /** 标识修改昵称 */
    private boolean checkRename;

    private Integer nicknameStatus;

    /** 说明: 注册时间 yyyy-MM-dd HH:mm:ss */
    private String createTime;

    /** 累计投注 */
    private BigDecimal betAmount;

    /** 用户层级id */
    private Long payLevelId;

    /** 用户类别，1：游客，未绑定手机号；2：充值游客，未绑定手机号，有充值；3：注册用户，绑定手机号；4：充值用户，绑定手机号，充过值，平台赠送彩金也算充值；5：测试用户 */
    private Integer userType;

    /** 聊天室状态 */
    private Integer roomStatus;

    /**
     * 检查额度为负的情况
     *
     * @return
     */
    public boolean checkMoneyAvailable() {
        boolean isValid = true;
        if (this.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            this.setBalance(BigDecimal.ZERO);
            isValid = false;
        }
        if (this.getWithdrawalAmount().compareTo(BigDecimal.ZERO) < 0) {
            this.setWithdrawalAmount(BigDecimal.ZERO);
            isValid = false;
        }
        return isValid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public BigDecimal getNoWithdrawalAmount() {
        return noWithdrawalAmount;
    }

    public void setNoWithdrawalAmount(BigDecimal noWithdrawalAmount) {
        this.noWithdrawalAmount = noWithdrawalAmount;
    }

    public BigDecimal getWithdrawalRemainder() {
        return withdrawalRemainder;
    }

    public void setWithdrawalRemainder(BigDecimal withdrawalRemainder) {
        this.withdrawalRemainder = withdrawalRemainder;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeads() {
        return heads;
    }

    public void setHeads(String heads) {
        this.heads = heads;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCheckRename() {
        return checkRename;
    }

    public void setCheckRename(boolean checkRename) {
        this.checkRename = checkRename;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getNicknameStatus() {
        return nicknameStatus;
    }

    public void setNicknameStatus(Integer nicknameStatus) {
        this.nicknameStatus = nicknameStatus;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
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

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public BigDecimal getWaitAmount() {
        return waitAmount;
    }

    public void setWaitAmount(BigDecimal waitAmount) {
        this.waitAmount = waitAmount;
    }
}
