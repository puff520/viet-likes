package com.likes.common.mybatis.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`dz_coin`")
public class DzCoin {
    /**
     * 币种id
     */
    @Id
    @Column(name = "`coin_id`")
    private Integer coinId;

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
     * 币种单位
     */
    @Column(name = "`main_symbol`")
    private String mainSymbol;

    /**
     * 主币种单位
     */
    @Column(name = "`symbol`")
    private String symbol;

    /**
     * 0： 主币 1：代币
     */
    @Column(name = "`token_status`")
    private Integer tokenStatus;

    /**
     * 获取币种id
     *
     * @return coin_id - 币种id
     */
    public Integer getCoinId() {
        return coinId;
    }

    /**
     * 设置币种id
     *
     * @param coinId 币种id
     */
    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
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
     * 获取币种单位
     *
     * @return main_symbol - 币种单位
     */
    public String getMainSymbol() {
        return mainSymbol;
    }

    /**
     * 设置币种单位
     *
     * @param mainSymbol 币种单位
     */
    public void setMainSymbol(String mainSymbol) {
        this.mainSymbol = mainSymbol;
    }

    /**
     * 获取主币种单位
     *
     * @return symbol - 主币种单位
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * 设置主币种单位
     *
     * @param symbol 主币种单位
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * 获取0： 主币 1：代币
     *
     * @return token_status - 0： 主币 1：代币
     */
    public Integer getTokenStatus() {
        return tokenStatus;
    }

    /**
     * 设置0： 主币 1：代币
     *
     * @param tokenStatus 0： 主币 1：代币
     */
    public void setTokenStatus(Integer tokenStatus) {
        this.tokenStatus = tokenStatus;
    }
}
