package com.likes.common.service.money.impl;

import com.likes.common.constant.Constants;
import com.likes.common.exception.BusinessException;
import com.likes.common.mybatis.entity.TraAnchortracking;
import com.likes.common.mybatis.mapper.TraAnchortrackingMapper;
import com.likes.common.mybatis.mapperext.tra.TraAnchortrackingMapperExt;
import com.likes.common.service.money.TraAnchortrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 16:57
 */
@Service
public class TraAnchortrackingServiceImpl implements TraAnchortrackingService {

    @Autowired
    private TraAnchortrackingMapperExt traAnchortrackingMapperExt;

    @Autowired
    private TraAnchortrackingMapper traAnchortrackingMapper;

    @Override
    public void saveTraAnchortrackingList(List<TraAnchortracking> allTraAnchortrackingList) throws BusinessException {
        traAnchortrackingMapperExt.insertMany(allTraAnchortrackingList);
    }

    @Override
    public List<Long> getOrderidByOnlineid(Long anconlineid) throws BusinessException {
        List<Long> orderidList = traAnchortrackingMapperExt.getOrderidByOnlineid(null == anconlineid ? Constants.DEFAULT_LONG : anconlineid);
        return null == orderidList ? new ArrayList<>() : orderidList;
    }
}
