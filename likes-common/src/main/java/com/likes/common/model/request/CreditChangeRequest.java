package com.likes.common.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditChangeRequest {

    //会员账号
    private String accno;

    //会员账号
    private String email;


    private Integer integral;


    private Integer changeType;

    //积分
    private Integer beginIntegral;


    //积分
    private Integer endIntegral;

}
