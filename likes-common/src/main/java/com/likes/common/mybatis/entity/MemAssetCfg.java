package com.likes.common.mybatis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`mem_asset_cfg`")
@Data
public class MemAssetCfg {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`name_en`")
    private String nameEn;

    @Column(name = "`name_sp`")
    private String nameSp;

    @Column(name = "`name_fn`")
    private String nameFn;

    @Column(name = "`name_ab`")
    private String nameAb;

    @Column(name = "`describe`")
    private String describe;

    @Column(name = "`describe_en`")
    private String describeEn;

    @Column(name = "`describe_sp`")
    private String describeSp;

    @Column(name = "`describe_fn`")
    private String describeFn;

    @Column(name = "`describe_ab`")
    private String describeAb;

    @Column(name = "`earn_rate`")
    private BigDecimal earnRate;

    @Column(name = "`daily_earn_rate`")
    private BigDecimal dailyEarnRate;

    @Column(name = "`deduct_rate`")
    private BigDecimal deductRate;

    @Column(name = "`max_deposit_amount`")
    private BigDecimal maxDepositAmount;

    @Column(name = "`open_level`")
    private Integer openLevel;

    @Column(name = "`cycle_type`")
    private Integer cycleType;

    @Column(name = "`take_status`")
    private Integer takeStatus;

    @Column(name = "`status`")
    private Integer status;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;

}
