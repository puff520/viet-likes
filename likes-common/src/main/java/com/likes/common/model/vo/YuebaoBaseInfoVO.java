package com.likes.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 余额宝记录信息
 */
@Data
@ApiModel
public class YuebaoBaseInfoVO {

    @ApiModelProperty("余额宝金额")
    private BigDecimal money;

    @ApiModelProperty("昨日收益")
    private BigDecimal YesterdayIncome;

    @ApiModelProperty("七日收益")
    private BigDecimal totalIncome;


}
