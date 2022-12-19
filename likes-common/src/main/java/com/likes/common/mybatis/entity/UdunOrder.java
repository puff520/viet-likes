package com.likes.common.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`dz_udun_order`")
public class UdunOrder {
    /**
     * udun 订单id
     */
    @Id
    @Column(name = "`undu_order_id`")
    private Long unduOrderId;

    /**
     * 会员标识
     */
    @Column(name = "`accno`")
    private String accno;

    /**
     * 币种名称
     */
    @Column(name = "`coin_name`")
    private String coinName;

    /**
     * 主币种类型
     */
    @Column(name = "`main_coin_type`")
    private String mainCoinType;

    /**
     * 币种类型
     */
    @Column(name = "`coin_type`")
    private String coinType;

    /**
     * 金额
     */
    @Column(name = "`amount`")
    private BigDecimal amount;

    /**
     * 钱包地址
     */
    @Column(name = "`address`")
    private String address;

    /**
     * 矿工费
     */
    @Column(name = "`fee`")
    private BigDecimal fee;

    /**
     * udun 交易id
     */
    @Column(name = "`trade_Id`")
    private String tradeId;

    /**
     * 区块链交易哈希
     */
    @Column(name = "`tx_id`")
    private String txId;

    /**
     * 平台业务id （流水号）
     */
    @Column(name = "`business_Id`")
    private String businessId;

    /**
     * 交易类型1 充值 2 提现
     */
    @Column(name = "`trade_type`")
    private Integer tradeType;

    /**
     * 1 成功 2 失败
     */
    @Column(name = "`trade_status`")
    private Integer tradeStatus;

    @Column(name = "`create_user`")
    private String createUser;

    @Column(name = "`update_user`")
    private String updateUser;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 获取udun 订单id
     *
     * @return undu_order_id - udun 订单id
     */
    public Long getUnduOrderId() {
        return unduOrderId;
    }

    /**
     * 设置udun 订单id
     *
     * @param unduOrderId udun 订单id
     */
    public void setUnduOrderId(Long unduOrderId) {
        this.unduOrderId = unduOrderId;
    }

    /**
     * 获取会员标识
     *
     * @return accno - 会员标识
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 设置会员标识
     *
     * @param accno 会员标识
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * 获取币种名称
     *
     * @return coin_name - 币种名称
     */
    public String getCoinName() {
        return coinName;
    }

    /**
     * 设置币种名称
     *
     * @param coinName 币种名称
     */
    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    /**
     * 获取主币种类型
     *
     * @return main_coin_type - 主币种类型
     */
    public String getMainCoinType() {
        return mainCoinType;
    }

    /**
     * 设置主币种类型
     *
     * @param mainCoinType 主币种类型
     */
    public void setMainCoinType(String mainCoinType) {
        this.mainCoinType = mainCoinType;
    }

    /**
     * 获取币种类型
     *
     * @return coin_type - 币种类型
     */
    public String getCoinType() {
        return coinType;
    }

    /**
     * 设置币种类型
     *
     * @param coinType 币种类型
     */
    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    /**
     * 获取金额
     *
     * @return amount - 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取钱包地址
     *
     * @return address - 钱包地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置钱包地址
     *
     * @param address 钱包地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取矿工费
     *
     * @return fee - 矿工费
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 设置矿工费
     *
     * @param fee 矿工费
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 获取udun 交易id
     *
     * @return trade_Id - udun 交易id
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置udun 交易id
     *
     * @param tradeId udun 交易id
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取区块链交易哈希
     *
     * @return tx_id - 区块链交易哈希
     */
    public String getTxId() {
        return txId;
    }

    /**
     * 设置区块链交易哈希
     *
     * @param txId 区块链交易哈希
     */
    public void setTxId(String txId) {
        this.txId = txId;
    }

    /**
     * 获取平台业务id （流水号）
     *
     * @return business_Id - 平台业务id （流水号）
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * 设置平台业务id （流水号）
     *
     * @param businessId 平台业务id （流水号）
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    /**
     * 获取交易类型1 充值 2 提现
     *
     * @return trade_type - 交易类型1 充值 2 提现
     */
    public Integer getTradeType() {
        return tradeType;
    }

    /**
     * 设置交易类型1 充值 2 提现
     *
     * @param tradeType 交易类型1 充值 2 提现
     */
    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * 获取1 成功 2 失败
     *
     * @return trade_status - 1 成功 2 失败
     */
    public Integer getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置1 成功 2 失败
     *
     * @param tradeStatus 1 成功 2 失败
     */
    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * @return create_user
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return update_user
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
