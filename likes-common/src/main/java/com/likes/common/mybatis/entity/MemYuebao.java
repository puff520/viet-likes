package com.likes.common.mybatis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`mem_yuebao`")
@Data
public class MemYuebao {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`amount`")
    private BigDecimal amount;

    @Column(name = "`yesterday_earn`")
    private BigDecimal yesterdayEarn;

    @Column(name = "`total_income`")
    private BigDecimal totalIncome;

    @Column(name = "`dividend_type`")
    private Integer dividendType;

    @Column(name = "`rate_type`")
    private Integer rateType;

    @Column(name = "`create_user`")
    private String createUser;

    @Column(name = "`update_user`")
    private String updateUser;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;


}
