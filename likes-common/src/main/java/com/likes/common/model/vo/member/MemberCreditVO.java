package com.likes.common.model.vo.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MemberCreditVO {

    //会员账号 （列表不展示-调整信用分的时候传）
    private String accno;

    //会员账号
    private String email;

    //顶级代理
    private String headAgent;

    //上级代理
    private String topAgent;

    // 余额
    private BigDecimal goldnum;

    //等级
    private Integer level;

    //积分
    private Integer integral;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date updateTime;

}

