package com.likes.common.service.money;

import com.likes.common.exception.BusinessException;
import com.likes.common.mybatis.entity.TraAnchortracking;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 16:56
 */
public interface TraAnchortrackingService {

    /**
     * 批量插入主播结算追踪表
     * @param allTraAnchortrackingList
     */
    void saveTraAnchortrackingList(List<TraAnchortracking> allTraAnchortrackingList)  throws BusinessException;

    /**
     * 获取该记录 onlineid 对应的 orderid
     * @param anconlineid
     * @return
     */
    List<Long> getOrderidByOnlineid(@Param("anconlineid") Long anconlineid)  throws BusinessException;
}
