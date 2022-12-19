package com.likes.common.model.dto.member;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class MemFamilymemSingleReq {

    @NotNull(message = "id不能为空")
    private Long familymemid;

    @NotNull(message = "提成比例不能为空")
    @DecimalMax(value = "1")
    @DecimalMin(value = "0")
    private BigDecimal royaltypercent;

    public Long getFamilymemid() {
        return familymemid;
    }

    public void setFamilymemid(Long familymemid) {
        this.familymemid = familymemid;
    }

    public BigDecimal getRoyaltypercent() {
        return royaltypercent;
    }

    public void setRoyaltypercent(BigDecimal royaltypercent) {
        this.royaltypercent = royaltypercent;
    }

}
