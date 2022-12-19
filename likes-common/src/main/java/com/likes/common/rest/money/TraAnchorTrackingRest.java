package com.likes.common.rest.money;

import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.TraAnchortracking;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * ClassName: TraAnchorTrackingRest
 * Description: 描述
 *
 * @author dapan
 * @since JDK 1.8
 * date: 2020/6/19 21:26
 */
public interface TraAnchorTrackingRest {

    /**
     * 批量插入主播结算追踪表
     */
    void saveTraAnchortrackingList(@RequestBody List<TraAnchortracking> allTraAnchortrackingList)  throws BusinessException;

    /**
     * 获取该记录 onlineid 对应的 orderid
     */
    ResultInfo<List<Long>> getOrderidByOnlineid(@RequestParam("anconlineid") Long anconlineid)  throws BusinessException;
}
