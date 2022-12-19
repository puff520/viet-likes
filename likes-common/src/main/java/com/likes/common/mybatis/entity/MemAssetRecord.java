package com.likes.common.mybatis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`mem_asset_record`")
@Data
public class MemAssetRecord {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`change_type`")
    private Integer changeType;

    @Column(name = "`change_amount`")
    private BigDecimal changeAmount;

    @Column(name = "`asset_cfg_id`")
    private Long assetCfgId;

    @Column(name = "`is_system_way`")
    private Boolean isSystemWay;

    @Column(name = "`serial_no`")
    private String serialNo;

    @Column(name = "`create_user`")
    private String createUser;

    @Column(name = "`update_user`")
    private String updateUser;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;

}
