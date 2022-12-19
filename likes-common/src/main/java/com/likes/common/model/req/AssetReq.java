package com.likes.common.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 余额宝记录请求信息
 */
@Data
@ApiModel
public class AssetReq extends BaseReq {


    @ApiModelProperty("语言")
    private String language;


}
