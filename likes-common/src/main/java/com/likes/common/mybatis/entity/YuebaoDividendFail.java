package com.likes.common.mybatis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`dz_yuebao_dividend_fail`")
@Data
public class YuebaoDividendFail {
    @Id
    @Column(name = "`fail_id`")
    private Long failId;

    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`asset_cfg_id`")
    private Long assetCfgId;

    @Column(name = "`error_msg`")
    private String errorMsg;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;


}
