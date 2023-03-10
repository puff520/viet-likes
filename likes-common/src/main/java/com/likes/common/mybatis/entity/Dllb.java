//package com.likes.common.mybatis.entity;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//public class Dllb implements Serializable {
//    /**
//     * 字段: dllb.id<br/>
//     * 主键: 自动增长<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private Integer id;
//
//    /**
//     * 字段: dllb.uid<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private Integer uid;
//
//    /**
//     * 字段: dllb.nick_name<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 昵称
//     *
//     * @mbggenerated
//     */
//    private String nickName;
//
//    /**
//     * 字段: dllb.account<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 账号
//     *
//     * @mbggenerated
//     */
//    private String account;
//
//    /**
//     * 字段: dllb.share_code<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 分享码
//     *
//     * @mbggenerated
//     */
//    private String shareCode;
//
//    /**
//     * 字段: dllb.one_level<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 一级上级
//     *
//     * @mbggenerated
//     */
//    private String oneLevel;
//
//    /**
//     * 字段: dllb.two_level<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 二级上级
//     *
//     * @mbggenerated
//     */
//    private String twoLevel;
//
//    /**
//     * 字段: dllb.dlnum<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private Integer dlnum;
//
//    /**
//     * 字段: dllb.bet_amount<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 有效投注（元）
//     *
//     * @mbggenerated
//     */
//    private BigDecimal betAmount;
//
//    /**
//     * 字段: dllb.pay_amount<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 累计充值（元）
//     *
//     * @mbggenerated
//     */
//    private BigDecimal payAmount;
//
//    /**
//     * 字段: dllb.win_amount<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 投注盈利（元）
//     *
//     * @mbggenerated
//     */
//    private BigDecimal winAmount;
//
//    /**
//     * 字段: dllb.betback<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 投注返水
//     *
//     * @mbggenerated
//     */
//    private BigDecimal betback;
//
//    /**
//     * 字段: dllb.payback<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 充值返水
//     *
//     * @mbggenerated
//     */
//    private BigDecimal payback;
//
//    /**
//     * 字段: dllb.winback<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 盈利返水
//     *
//     * @mbggenerated
//     */
//    private BigDecimal winback;
//
//    /**
//     * 字段: dllb.sumback<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 总返水
//     *
//     * @mbggenerated
//     */
//    private BigDecimal sumback;
//
//    /**
//     * 字段: dllb.betback_rate<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 8<br/>
//     * 说明: 投注返水比例
//     *
//     * @mbggenerated
//     */
//    private Double betbackRate;
//
//    /**
//     * 字段: dllb.payback_rate<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 8<br/>
//     * 说明: 充值返水比例
//     *
//     * @mbggenerated
//     */
//    private Double paybackRate;
//
//    /**
//     * 字段: dllb.winback_rate<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 8<br/>
//     * 说明: 盈利返水比例
//     *
//     * @mbggenerated
//     */
//    private Double winbackRate;
//
//    /**
//     * 字段: dllb.create_time<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 创建时间
//     *
//     * @mbggenerated
//     */
//    private String createTime;
//
//    /**
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database table dllb
//     *
//     * @mbggenerated
//     */
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * @return dllb.id:
//     *
//     * @mbggenerated
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     * 字段: dllb.id<br/>
//     * 主键: 自动增长<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    /**
//     * @return dllb.uid:
//     *
//     * @mbggenerated
//     */
//    public Integer getUid() {
//        return uid;
//    }
//
//    /**
//     * 字段: dllb.uid<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setUid(Integer uid) {
//        this.uid = uid;
//    }
//
//    /**
//     * @return dllb.nick_name: 昵称
//     *
//     * @mbggenerated
//     */
//    public String getNickName() {
//        return nickName;
//    }
//
//    /**
//     * 字段: dllb.nick_name<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 昵称
//     *
//     * @mbggenerated
//     */
//    public void setNickName(String nickName) {
//        this.nickName = nickName;
//    }
//
//    /**
//     * @return dllb.account: 账号
//     *
//     * @mbggenerated
//     */
//    public String getAccount() {
//        return account;
//    }
//
//    /**
//     * 字段: dllb.account<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 账号
//     *
//     * @mbggenerated
//     */
//    public void setAccount(String account) {
//        this.account = account;
//    }
//
//    /**
//     * @return dllb.share_code: 分享码
//     *
//     * @mbggenerated
//     */
//    public String getShareCode() {
//        return shareCode;
//    }
//
//    /**
//     * 字段: dllb.share_code<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 分享码
//     *
//     * @mbggenerated
//     */
//    public void setShareCode(String shareCode) {
//        this.shareCode = shareCode;
//    }
//
//    /**
//     * @return dllb.one_level: 一级上级
//     *
//     * @mbggenerated
//     */
//    public String getOneLevel() {
//        return oneLevel;
//    }
//
//    /**
//     * 字段: dllb.one_level<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 一级上级
//     *
//     * @mbggenerated
//     */
//    public void setOneLevel(String oneLevel) {
//        this.oneLevel = oneLevel;
//    }
//
//    /**
//     * @return dllb.two_level: 二级上级
//     *
//     * @mbggenerated
//     */
//    public String getTwoLevel() {
//        return twoLevel;
//    }
//
//    /**
//     * 字段: dllb.two_level<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 二级上级
//     *
//     * @mbggenerated
//     */
//    public void setTwoLevel(String twoLevel) {
//        this.twoLevel = twoLevel;
//    }
//
//    /**
//     * @return dllb.dlnum:
//     *
//     * @mbggenerated
//     */
//    public Integer getDlnum() {
//        return dlnum;
//    }
//
//    /**
//     * 字段: dllb.dlnum<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setDlnum(Integer dlnum) {
//        this.dlnum = dlnum;
//    }
//
//    /**
//     * @return dllb.bet_amount: 有效投注（元）
//     *
//     * @mbggenerated
//     */
//    public BigDecimal getBetAmount() {
//        return betAmount;
//    }
//
//    /**
//     * 字段: dllb.bet_amount<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 有效投注（元）
//     *
//     * @mbggenerated
//     */
//    public void setBetAmount(BigDecimal betAmount) {
//        this.betAmount = betAmount;
//    }
//
//    /**
//     * @return dllb.pay_amount: 累计充值（元）
//     *
//     * @mbggenerated
//     */
//    public BigDecimal getPayAmount() {
//        return payAmount;
//    }
//
//    /**
//     * 字段: dllb.pay_amount<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 累计充值（元）
//     *
//     * @mbggenerated
//     */
//    public void setPayAmount(BigDecimal payAmount) {
//        this.payAmount = payAmount;
//    }
//
//    /**
//     * @return dllb.win_amount: 投注盈利（元）
//     *
//     * @mbggenerated
//     */
//    public BigDecimal getWinAmount() {
//        return winAmount;
//    }
//
//    /**
//     * 字段: dllb.win_amount<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 投注盈利（元）
//     *
//     * @mbggenerated
//     */
//    public void setWinAmount(BigDecimal winAmount) {
//        this.winAmount = winAmount;
//    }
//
//    /**
//     * @return dllb.betback: 投注返水
//     *
//     * @mbggenerated
//     */
//    public BigDecimal getBetback() {
//        return betback;
//    }
//
//    /**
//     * 字段: dllb.betback<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 投注返水
//     *
//     * @mbggenerated
//     */
//    public void setBetback(BigDecimal betback) {
//        this.betback = betback;
//    }
//
//    /**
//     * @return dllb.payback: 充值返水
//     *
//     * @mbggenerated
//     */
//    public BigDecimal getPayback() {
//        return payback;
//    }
//
//    /**
//     * 字段: dllb.payback<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 充值返水
//     *
//     * @mbggenerated
//     */
//    public void setPayback(BigDecimal payback) {
//        this.payback = payback;
//    }
//
//    /**
//     * @return dllb.winback: 盈利返水
//     *
//     * @mbggenerated
//     */
//    public BigDecimal getWinback() {
//        return winback;
//    }
//
//    /**
//     * 字段: dllb.winback<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 盈利返水
//     *
//     * @mbggenerated
//     */
//    public void setWinback(BigDecimal winback) {
//        this.winback = winback;
//    }
//
//    /**
//     * @return dllb.sumback: 总返水
//     *
//     * @mbggenerated
//     */
//    public BigDecimal getSumback() {
//        return sumback;
//    }
//
//    /**
//     * 字段: dllb.sumback<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 20<br/>
//     * 说明: 总返水
//     *
//     * @mbggenerated
//     */
//    public void setSumback(BigDecimal sumback) {
//        this.sumback = sumback;
//    }
//
//    /**
//     * @return dllb.betback_rate: 投注返水比例
//     *
//     * @mbggenerated
//     */
//    public Double getBetbackRate() {
//        return betbackRate;
//    }
//
//    /**
//     * 字段: dllb.betback_rate<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 8<br/>
//     * 说明: 投注返水比例
//     *
//     * @mbggenerated
//     */
//    public void setBetbackRate(Double betbackRate) {
//        this.betbackRate = betbackRate;
//    }
//
//    /**
//     * @return dllb.payback_rate: 充值返水比例
//     *
//     * @mbggenerated
//     */
//    public Double getPaybackRate() {
//        return paybackRate;
//    }
//
//    /**
//     * 字段: dllb.payback_rate<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 8<br/>
//     * 说明: 充值返水比例
//     *
//     * @mbggenerated
//     */
//    public void setPaybackRate(Double paybackRate) {
//        this.paybackRate = paybackRate;
//    }
//
//    /**
//     * @return dllb.winback_rate: 盈利返水比例
//     *
//     * @mbggenerated
//     */
//    public Double getWinbackRate() {
//        return winbackRate;
//    }
//
//    /**
//     * 字段: dllb.winback_rate<br/>
//     * 必填: true<br/>
//     * 缺省: 0.00<br/>
//     * 长度: 8<br/>
//     * 说明: 盈利返水比例
//     *
//     * @mbggenerated
//     */
//    public void setWinbackRate(Double winbackRate) {
//        this.winbackRate = winbackRate;
//    }
//
//    /**
//     * @return dllb.create_time: 创建时间
//     *
//     * @mbggenerated
//     */
//    public String getCreateTime() {
//        return createTime;
//    }
//
//    /**
//     * 字段: dllb.create_time<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 创建时间
//     *
//     * @mbggenerated
//     */
//    public void setCreateTime(String createTime) {
//        this.createTime = createTime;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table dllb
//     *
//     * @mbggenerated
//     */
//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        Dllb other = (Dllb) that;
//        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
//            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
//            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
//            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
//            && (this.getShareCode() == null ? other.getShareCode() == null : this.getShareCode().equals(other.getShareCode()))
//            && (this.getOneLevel() == null ? other.getOneLevel() == null : this.getOneLevel().equals(other.getOneLevel()))
//            && (this.getTwoLevel() == null ? other.getTwoLevel() == null : this.getTwoLevel().equals(other.getTwoLevel()))
//            && (this.getDlnum() == null ? other.getDlnum() == null : this.getDlnum().equals(other.getDlnum()))
//            && (this.getBetAmount() == null ? other.getBetAmount() == null : this.getBetAmount().equals(other.getBetAmount()))
//            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
//            && (this.getWinAmount() == null ? other.getWinAmount() == null : this.getWinAmount().equals(other.getWinAmount()))
//            && (this.getBetback() == null ? other.getBetback() == null : this.getBetback().equals(other.getBetback()))
//            && (this.getPayback() == null ? other.getPayback() == null : this.getPayback().equals(other.getPayback()))
//            && (this.getWinback() == null ? other.getWinback() == null : this.getWinback().equals(other.getWinback()))
//            && (this.getSumback() == null ? other.getSumback() == null : this.getSumback().equals(other.getSumback()))
//            && (this.getBetbackRate() == null ? other.getBetbackRate() == null : this.getBetbackRate().equals(other.getBetbackRate()))
//            && (this.getPaybackRate() == null ? other.getPaybackRate() == null : this.getPaybackRate().equals(other.getPaybackRate()))
//            && (this.getWinbackRate() == null ? other.getWinbackRate() == null : this.getWinbackRate().equals(other.getWinbackRate()))
//            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table dllb
//     *
//     * @mbggenerated
//     */
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
//        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
//        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
//        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
//        result = prime * result + ((getShareCode() == null) ? 0 : getShareCode().hashCode());
//        result = prime * result + ((getOneLevel() == null) ? 0 : getOneLevel().hashCode());
//        result = prime * result + ((getTwoLevel() == null) ? 0 : getTwoLevel().hashCode());
//        result = prime * result + ((getDlnum() == null) ? 0 : getDlnum().hashCode());
//        result = prime * result + ((getBetAmount() == null) ? 0 : getBetAmount().hashCode());
//        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
//        result = prime * result + ((getWinAmount() == null) ? 0 : getWinAmount().hashCode());
//        result = prime * result + ((getBetback() == null) ? 0 : getBetback().hashCode());
//        result = prime * result + ((getPayback() == null) ? 0 : getPayback().hashCode());
//        result = prime * result + ((getWinback() == null) ? 0 : getWinback().hashCode());
//        result = prime * result + ((getSumback() == null) ? 0 : getSumback().hashCode());
//        result = prime * result + ((getBetbackRate() == null) ? 0 : getBetbackRate().hashCode());
//        result = prime * result + ((getPaybackRate() == null) ? 0 : getPaybackRate().hashCode());
//        result = prime * result + ((getWinbackRate() == null) ? 0 : getWinbackRate().hashCode());
//        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
//        return result;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table dllb
//     *
//     * @mbggenerated
//     */
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", uid=").append(uid);
//        sb.append(", nickName=").append(nickName);
//        sb.append(", account=").append(account);
//        sb.append(", shareCode=").append(shareCode);
//        sb.append(", oneLevel=").append(oneLevel);
//        sb.append(", twoLevel=").append(twoLevel);
//        sb.append(", dlnum=").append(dlnum);
//        sb.append(", betAmount=").append(betAmount);
//        sb.append(", payAmount=").append(payAmount);
//        sb.append(", winAmount=").append(winAmount);
//        sb.append(", betback=").append(betback);
//        sb.append(", payback=").append(payback);
//        sb.append(", winback=").append(winback);
//        sb.append(", sumback=").append(sumback);
//        sb.append(", betbackRate=").append(betbackRate);
//        sb.append(", paybackRate=").append(paybackRate);
//        sb.append(", winbackRate=").append(winbackRate);
//        sb.append(", createTime=").append(createTime);
//        sb.append("]");
//        return sb.toString();
//    }
//}