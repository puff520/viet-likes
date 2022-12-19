package com.likes.common.model;

import com.likes.common.enums.AssetOperateEnum;
import com.likes.common.enums.YueaboOperateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class YuebaoChangeBO {
    private String accno;

    private Long assetCfgId;

    private Long buyAssetCfgId;

    private BigDecimal changeAmount;
    private YueaboOperateEnum operateEnum;

    private AssetOperateEnum assetOperateEnum;

    private Boolean isSystemWay = false;


}
