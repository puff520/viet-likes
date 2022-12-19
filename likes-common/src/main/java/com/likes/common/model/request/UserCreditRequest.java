package com.likes.common.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserCreditRequest {

    //会员账号
    private String accno;

    //会员账号
    private String email;

    //顶级代理
    private String headAgent;

    //上级代理
    private String topAgent;

    //开始时间
    private String beginTime;

    //起始金额
    private BigDecimal beginAmount;

    //结束金额
    private BigDecimal endAmount;

    //结束时间
    private String endTime;

    //等级
    private Long memLevel;

    //积分
    private Integer integral;

}
