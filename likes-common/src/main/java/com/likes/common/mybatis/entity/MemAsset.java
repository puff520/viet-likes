package com.likes.common.mybatis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`mem_asset`")
@Data
public class MemAsset {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`asset_cfg_id`")
    private Long assetCfgId;

    @Column(name = "`amount`")
    private BigDecimal amount;

    @Column(name = "`today_earn`")
    private BigDecimal todayEarn;

    @Column(name = "`yesterday_earn`")
    private BigDecimal yesterdayEarn;

    @Column(name = "`total_earn`")
    private BigDecimal totalEarn;

    @Column(name = "`earn_status`")
    private Integer earnStatus;

    @Column(name = "`usable_amount`")
    private BigDecimal usableAmount;

    @Column(name = "`into_time`")
    private Date intoTime;

    @Column(name = "`take_time`")
    private Date takeTime;

    @Column(name = "`create_user`")
    private String createUser;

    @Column(name = "`update_user`")
    private String updateUser;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;


}
