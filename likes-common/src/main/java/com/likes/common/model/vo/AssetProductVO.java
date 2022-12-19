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
public class AssetProductVO {


    @ApiModelProperty("余额宝记录Id")
    private Long id;

    @ApiModelProperty("理财产品id")
    private Long assetCfgId;

    @ApiModelProperty("理财产品名称")
    private String name;
    @ApiModelProperty("理财产品名称-西班牙")
    private String nameSp;
    @ApiModelProperty("理财产品名称-法国")
    private String nameFn;
    @ApiModelProperty("理财产品名称-英文")
    private String nameEn;
    @ApiModelProperty("理财产品名称-阿拉伯")
    private String nameAb;

    @ApiModelProperty("最高可存金额")
    private BigDecimal maxDepositAmount;

    @ApiModelProperty("收益率")
    private BigDecimal earnRate;

    @ApiModelProperty("已存入金额")
    private BigDecimal amount;

    @ApiModelProperty("昨日收益")
    private BigDecimal yesterdayEarn;

    @ApiModelProperty("总收益")
    private BigDecimal totalEarn;

    @ApiModelProperty("时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
