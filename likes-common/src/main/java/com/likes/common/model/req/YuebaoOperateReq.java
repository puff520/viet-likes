package com.likes.common.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 余额宝记录请求信息
 */
@Data
@ApiModel
public class YuebaoOperateReq extends BaseReq {


//    @ApiModelProperty("语言")
//    private Integer changeType;

    @ApiModelProperty("理财产品id")
    private Long assetCfgId;

    @ApiModelProperty("操作金额")
    private BigDecimal operateAmount;

}
