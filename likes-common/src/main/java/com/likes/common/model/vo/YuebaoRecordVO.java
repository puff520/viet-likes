package com.likes.common.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 余额宝记录信息
 */
@Data
@ApiModel
public class YuebaoRecordVO {

    @ApiModelProperty("余额宝记录Id")
    private Long id;

    @ApiModelProperty("会员Id")
    private String accno;

    @ApiModelProperty("会员Id")
    private Integer changeType;

    @ApiModelProperty("理财产品id")
    private Long assetCfgId;

    @ApiModelProperty("操作方式")
    private Integer operationWay = 2;

    @ApiModelProperty("理财产品名称")
    private String assetName;

    @ApiModelProperty("金额")
    private BigDecimal changeAmount;

    @ApiModelProperty("时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("流水号")
    private String serialNo;

}
