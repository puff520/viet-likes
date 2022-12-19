package com.likes.common.rest.money;


import com.likes.common.model.dto.order.OrderTotalDTO;
import org.springframework.web.bind.annotation.RequestParam;


public interface TraOrderinfomRest {
    /**
     * 查询订单主表 统计当前房间的总收入
     */
    Double getSumamt(Long roomid);

    /**
     * 统计家族长礼物分成和投注分成
     */
    OrderTotalDTO getFamilyGiftDivided(@RequestParam(name = "accno") String accno, @RequestParam(name = "startTime") String startTime, @RequestParam(name = "endTime") String endTime);
}
