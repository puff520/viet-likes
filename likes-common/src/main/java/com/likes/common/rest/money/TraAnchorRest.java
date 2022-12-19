package com.likes.common.rest.money;

import com.github.pagehelper.Page;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.TraAnchorDO;
import com.likes.common.model.request.body.FamilyIncarnateRequestReq;
import com.likes.common.model.request.body.GetTraAnchorDoListBodyReq;
import com.likes.common.model.vo.MemFamilyIncomeStatementVO;
import com.likes.common.mybatis.entity.TraAnchor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName: TraAnchorRest
 * Description: 描述
 *
 * @author ping
 * @since JDK 1.8
 * date: 2020/6/19 21:26
 */
public interface TraAnchorRest {

    /**
     * 批量插入主播结算收入明细
     */
    void saveTraAnchor(@RequestBody List<TraAnchor> allTraAnchorList) throws BusinessException;

    /**
     * 根据提现申请id获取结算收入明细
     */
    List<TraAnchor> getListByApycid(@RequestParam("apycid") Long apycid) throws BusinessException;

    /**
     * 分页获取主播结算明细
     */
    PageResult getincarnateHistoryByFamily(@RequestBody FamilyIncarnateRequestReq familyIncarnateRequestReq) throws BusinessException;

    /**
     * 分页获取主播收入明细(提现之前且不重复的）
     */
    MemFamilyIncomeStatementVO getTraAnchorDOList(@RequestBody GetTraAnchorDoListBodyReq getTraAnchorDoListBodyReq) throws BusinessException;

    /**
     * 原生mapper获取主播结算明细
     */
    TraAnchor selectByPrimaryKey(@RequestParam("traanchorid") Long traanchorid);

    /**
     * 分页获取主播结算明细
     */
    Page<TraAnchorDO> getincarnateHistoryByApycid(@RequestBody FamilyIncarnateRequestReq familyIncarnateRequestReq) throws BusinessException;
}
