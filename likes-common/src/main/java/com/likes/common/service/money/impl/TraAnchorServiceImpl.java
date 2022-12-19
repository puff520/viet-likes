package com.likes.common.service.money.impl;

import com.likes.common.constant.Constants;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.TraAnchorDO;
import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.model.vo.MemFamilyIncomeStatementVO;
import com.likes.common.model.vo.TraAnchorVO;
import com.likes.common.mybatis.entity.TraAnchor;
import com.likes.common.mybatis.mapper.TraAnchorMapper;
import com.likes.common.mybatis.mapperext.tra.TraAnchorMapperExt;
import com.likes.common.service.money.TraAnchorService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lucien
 * @create 2020/6/19 16:54
 */
@Service
public class TraAnchorServiceImpl implements TraAnchorService {

    @Autowired
    private TraAnchorMapper traAnchorMapper;
    @Autowired
    private TraAnchorMapperExt traAnchorMapperExt;

    @Override
    public void saveTraAnchor(List<TraAnchor> allTraAnchorList) throws BusinessException {
        traAnchorMapperExt.insertMany(allTraAnchorList);
    }

    @Override
    public List<TraAnchor> getListByApycid(Long apycid) throws BusinessException {
        List<TraAnchor> listByApycid = traAnchorMapperExt.getListByApycid(apycid);
        return null == listByApycid ? new ArrayList<TraAnchor>() : listByApycid;
    }

    @Override
    public PageResult getincarnateHistoryByFamily(FamilyIncarnateRequest req, PageBounds page) throws BusinessException {
        Page<TraAnchorDO> traAnchorDOS = traAnchorMapperExt.getincarnateHistoryByFamily(req, page.toRowBounds());
        return null == traAnchorDOS ? PageResult.getPageResult(page, new Page<TraAnchorDO>())
                : PageResult.getPageResult(page, traAnchorDOS);
    }

    @Override
    public MemFamilyIncomeStatementVO getTraAnchorDOList(List<Long> objects, PageBounds page, MemFamilyIncomeStatementVO memFamilyIncomeStatementVO) throws BusinessException {
        Page<TraAnchorVO> traAnchorDOList = traAnchorMapperExt.getTraAnchorList(objects, page.toRowBounds());
        if (null == traAnchorDOList) {
            memFamilyIncomeStatementVO.setList(new Page<TraAnchorVO>());
            memFamilyIncomeStatementVO.setTotal(Constants.BANK_CARD_BINGDING_PROHABIT);
        } else {
            memFamilyIncomeStatementVO.setList(traAnchorDOList);
            memFamilyIncomeStatementVO.setTotal((int) traAnchorDOList.getTotal());
        }
        memFamilyIncomeStatementVO.setPage(page);
        return memFamilyIncomeStatementVO;
    }

    @Override
    public TraAnchor selectByPrimaryKey(Long traanchorid) {
        return traAnchorMapper.selectByPrimaryKey(traanchorid);
    }

    @Override
    public Page<TraAnchorDO> getincarnateHistoryByApycid(FamilyIncarnateRequest req, PageBounds page) throws BusinessException {
        return traAnchorMapperExt.getincarnateHistoryByApycid(req, page.toRowBounds());
    }
}
