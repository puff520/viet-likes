package com.likes.common.model.dto;

import com.likes.common.model.common.PageBaseInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
@Data
public class AgentLineData extends PageBaseInfo implements Serializable {

    @ApiModelProperty(value = "代理名称")
    private String userName;

    @ApiModelProperty(value = "充值")
    private BigDecimal rechargeAmount;

    @ApiModelProperty(value = "提现")
    private BigDecimal withdrawAmount;

    @ApiModelProperty(value = "充提差")
    private BigDecimal difference;

}
