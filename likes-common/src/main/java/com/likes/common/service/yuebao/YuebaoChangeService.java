package com.likes.common.service.yuebao;


import com.likes.common.model.AssetChangeBO;
import com.likes.common.model.YuebaoChangeBO;

public interface YuebaoChangeService {

     /**
      * 修改余额宝余额
      *
      * @param change 改变记录
      * @return
      */
     boolean updateYuebaoAmount(YuebaoChangeBO change) throws RuntimeException;


     /**
      * 修改理财产品余额
      *
      * @return
      */
     boolean updateAssetAmount(AssetChangeBO change) throws RuntimeException;


     boolean updateEranYueBao(AssetChangeBO change) throws RuntimeException;
     boolean updateEranAmount(AssetChangeBO change) throws RuntimeException;





}
