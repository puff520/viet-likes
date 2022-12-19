package com.likes.modules.admin.users.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.Page;
import com.likes.common.constant.Constants;
import com.likes.common.enums.pay.MemBankAccountTypeEnum;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.EntryOrderReq;
import com.likes.common.model.request.IncarnateOrderReq;
import com.likes.common.model.response.EntryOrderResponse;
import com.likes.common.model.response.EntryOrderTotalResponse;
import com.likes.common.model.response.IncarnateOrderResponse;
import com.likes.common.model.response.StatisticsOnLineOrderResponse;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.money.SysPayproviderService;
import com.likes.common.service.money.SysThreePaysetService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.users.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl  extends BaseServiceImpl implements OrderService {

    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Resource
    private SysPayproviderService sysPayproviderService;
    @Resource
    private SysThreePaysetService sysThreePaysetService;
    @Resource
    private SysBusParamService sysBusParamService;


    @Override
    @DS("slave")
    public PageResult appOrderList(EntryOrderReq req, PageBounds page) {
        Page<EntryOrderResponse> list = traOrderinfomMapperService.appOrderList(req, page.toRowBounds());
        list.forEach(r -> {
            if (Constants.ORDER_ORD04.equals(r.getOrderstatus())) {
                r.setModifyusername(null);
                r.setUpdateTime(null);
            }
        });
        return PageResult.getPageResult(page, list);
    }



    @Override
    @DS("slave")
    public PageResult incarnateOrderListV2(IncarnateOrderReq req, PageBounds page) {
        if (req.getStartDate() != null && req.getStartDate() != ""){
            req.setStartDate(req.getStartDate()+" 00:00:00");
        }
        if (req.getEndDate() != null && req.getEndDate() != ""){
            req.setEndDate(req.getEndDate()+" 23:59:59");
        }
        Page<IncarnateOrderResponse>  list = traOrderinfomMapperService.appIncarnateOrderListBySuper(req, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }


}
