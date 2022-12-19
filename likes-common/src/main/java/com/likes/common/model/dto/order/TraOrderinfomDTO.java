package com.likes.common.model.dto.order;

import com.likes.common.mybatis.entity.TraOrderinfom;

import java.math.BigDecimal;
import java.util.Date;

public class TraOrderinfomDTO extends TraOrderinfom {

    /**
     * 说明: 注册日期
     */
    private Date registerdate;

    /**
     * 说明: 累计充值（元）
     */
    private BigDecimal payAmount;

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
