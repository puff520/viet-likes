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
public class AssetChangeBO {


    private String accno;

    private Long assetCfgId;

    private Long buyAssetCfgId;

    private BigDecimal changeAmount;

    private BigDecimal changeRate;

    private YueaboOperateEnum operateEnum;

    private AssetOperateEnum assetOperateEnum;


}
