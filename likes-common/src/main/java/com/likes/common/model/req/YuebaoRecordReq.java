package com.likes.common.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 余额宝记录请求信息
 */
@Data
@ApiModel
public class YuebaoRecordReq extends BaseReq {



    @ApiModelProperty("分页参数")
    private Integer pageNo;

    @ApiModelProperty("分页参数")
    private Integer pageSize;

    @ApiModelProperty("语言")
    private String language;

    @ApiModelProperty("语言")
    private Integer changeType;


}
