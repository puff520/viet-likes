package com.likes.common.model.request;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class TraRechargemealReq {

    private Long mealid;

    @NotNull(message = "充值金额不能为空")
    @DecimalMin(value = "1")
    private BigDecimal realamt;

    @DecimalMax(value = "1")
    @DecimalMin(value = "0")
    @NotNull(message = "赠送率不能为空")
    private BigDecimal givepercent;
    @PositiveOrZero
    private Integer sortby;

    public Integer getSortby() {
        return sortby;
    }

    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

    public Long getMealid() {
        return mealid;
    }

    public void setMealid(Long mealid) {
        this.mealid = mealid;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    public BigDecimal getGivepercent() {
        return givepercent;
    }

    public void setGivepercent(BigDecimal givepercent) {
        this.givepercent = givepercent;
    }

}
