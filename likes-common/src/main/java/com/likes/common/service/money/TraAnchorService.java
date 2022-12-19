package com.likes.common.service.money;

import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.TraAnchorDO;
import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.model.vo.MemFamilyIncomeStatementVO;
import com.likes.common.mybatis.entity.TraAnchor;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 16:54
 */
public interface TraAnchorService {

    /**
     * 批量插入主播结算收入明细
     *
     * @param allTraAnchorList
     */
    void saveTraAnchor(List<TraAnchor> allTraAnchorList) throws BusinessException;

    /**
     * 根据提现申请id获取结算收入明细
     *
     * @param apycid
     * @return
     * @throws BusinessException
     */
    List<TraAnchor> getListByApycid(Long apycid) throws BusinessException;

    /**
     * 分页获取主播结算明细
     *
     * @param req
     * @param page
     * @return
     * @throws BusinessException
     */
    PageResult getincarnateHistoryByFamily(FamilyIncarnateRequest req, PageBounds page) throws BusinessException;

    /**
     * 分页获取主播收入明细(提现之前且不重复的）
     *
     * @param objects
     * @param page
     * @param memFamilyIncomeStatementVO
     * @return
     * @throws BusinessException
     */
    MemFamilyIncomeStatementVO getTraAnchorDOList(List<Long> objects, PageBounds page, MemFamilyIncomeStatementVO memFamilyIncomeStatementVO) throws BusinessException;

    /**
     * 原生mapper获取主播结算明细
     *
     * @param traanchorid
     * @return
     */
    TraAnchor selectByPrimaryKey(Long traanchorid);

    /**
     * 分页获取主播结算明细
     *
     * @param req
     * @param page
     * @return
     * @throws BusinessException
     */
    Page<TraAnchorDO> getincarnateHistoryByApycid(FamilyIncarnateRequest req, PageBounds page) throws BusinessException;
}
