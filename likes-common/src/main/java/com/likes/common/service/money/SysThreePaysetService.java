package com.likes.common.service.money;


import com.likes.common.model.dto.report.ProviderSetDO;
import com.likes.common.model.dto.report.ThirdProviderDO;
import com.likes.common.mybatis.entity.SysThreepayset;

import java.util.List;

public interface SysThreePaysetService {

    List<SysThreepayset> commonPay(int userLevel, String wayType);

    List<Long> getAllSysThreePaySet(String providerid);

    List<ProviderSetDO> getAllsetAndProvider();

    List<Long> getAllids(String provider);

}
