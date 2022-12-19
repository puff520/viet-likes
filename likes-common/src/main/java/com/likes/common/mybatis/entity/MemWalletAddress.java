package com.likes.common.mybatis.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`mem_wallet_address`")
public class MemWalletAddress {
    @Id
    @Column(name = "`address_id`")
    private Integer addressId;

    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`coin_name`")
    private String coinName;

    @Column(name = "`money_address`")
    private String moneyAddress;

    @Column(name = "`create_user`")
    private String createUser;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_user`")
    private String updateUser;

    @Column(name = "`update_time`")
    private Date updateTime;

    @Column(name = "`remark`")
    private String remark;

    /**
     * @return address_id
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * @return accno
     */
    public String getAccno() {
        return accno;
    }

    /**
     * @param accno
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return coin_name
     */
    public String getCoinName() {
        return coinName;
    }

    /**
     * @param coinName
     */
    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    /**
     * @return money_address
     */
    public String getMoneyAddress() {
        return moneyAddress;
    }

    /**
     * @param moneyAddress
     */
    public void setMoneyAddress(String moneyAddress) {
        this.moneyAddress = moneyAddress;
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

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
