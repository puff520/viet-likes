package com.likes.common.model.req;

import com.likes.common.util.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @创建人 puff
 * @创建时间 2021/4/22 19:20
 * @描述
 */

@Data
public class BaseReq {

    @ApiModelProperty("开始时间")
    private String beginTIme;
    @ApiModelProperty("结束时间")
    private String endTime;
//
//    @ApiModelProperty("分页参数")
//    private PageBounds page;

    {
        this.beginTIme = DateUtils.dayBeginStr();
        this.endTime = DateUtils.dayEndStr();
    }

}
