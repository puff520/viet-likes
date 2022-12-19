package com.likes.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 余额宝记录信息
 */
@Data
@ApiModel
public class AssetExpireVO {


    @ApiModelProperty("理财产品id")
    private Long assetCfgId;

    @ApiModelProperty("是否到期(0 未到期 1 到期 ) 未到期需要扣除10% 佣金")
    private Integer isExpire = 0;

    @ApiModelProperty("到期时间")
    private String expireTime;


}
