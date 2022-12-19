package com.likes.modules.admin.finance.service.impl;

import com.likes.common.service.money.TraRechargemealService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.TraRechargemealReq;
import com.likes.common.model.response.TraRechargemealResponse;
import com.likes.common.mybatis.entity.TraRechargemeal;
import com.likes.common.util.BeanUtils;
import com.likes.modules.admin.finance.service.ManageTraRechargemealService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class ManageTraRechargemealServiceImpl implements ManageTraRechargemealService {

    @Resource
    private TraRechargemealService traRechargemealService;

    @Resource
    private SysParamService sysParamService;


    @Override
    public Long doSaveTraRechargemeal(TraRechargemealReq req, LoginUser loginUser) {
		
		/*//套餐数量不能超过6个
		int num = 6;
		SysParameter sp = this.sysParamService.getByCode(SysParameterEnum.RECHARGEMEAL_NUM.name());
		if (sp != null 
				&& StringUtils.isNotEmpty(sp.getSysparamvalue())) {
			num = Integer.parseInt(sp.getSysparamvalue());
		}
		
		int traRechargemealnum  = traRechargemealMapperService.findAllTotal();
		if (traRechargemealnum >= num) {
			throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "套餐数量不能超过6个");
		}*/

        TraRechargemeal tra = new TraRechargemeal();
        BeanUtils.copyProperties(req, tra);
        if (null == tra.getSortby()) {
            tra.setSortby(0);
        }
        tra.setCreateUser(loginUser.getAccno());
        tra.setUpdateUser(loginUser.getAccno());
        BigDecimal bili = new BigDecimal(Constants.CHONGZHIBILIE);
        BigDecimal rechargegoldBigDecimal = getTradeOffAmount(tra.getRealamt().multiply(bili));

        tra.setRechargegold(rechargegoldBigDecimal);
        if (tra.getGivepercent().doubleValue() > 0) {
            tra.setGivegold(getTradeOffAmount(rechargegoldBigDecimal.multiply(tra.getGivepercent())));
        } else {
            tra.setGivegold(getTradeOffAmount(null));
        }

        traRechargemealService.insertSelective(tra);
        return tra.getMealid();
    }

    @Override
    public Long doUpdateTraRechargemeal(TraRechargemealReq req, LoginUser loginUser) {
        if (null == req.getMealid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "mealid不能为空");
        }

        TraRechargemeal tra = new TraRechargemeal();
        BeanUtils.copyProperties(req, tra);
        tra.setIsDelete(false);
        if (null == tra.getSortby()) {
            tra.setSortby(0);
        }
        tra.setUpdateUser(loginUser.getAccno());
        BigDecimal bili = new BigDecimal(Constants.CHONGZHIBILIE);
        BigDecimal rechargegoldBigDecimal = tra.getRealamt().multiply(bili);
        tra.setRechargegold(getTradeOffAmount(rechargegoldBigDecimal));
        if (tra.getGivepercent().doubleValue() > 0) {
            tra.setGivegold(getTradeOffAmount(rechargegoldBigDecimal.multiply(tra.getGivepercent())));
        } else {
            tra.setGivegold(getTradeOffAmount(null));
        }

        traRechargemealService.updateByPrimaryKeySelective(tra);
        return tra.getMealid();
    }

    @Override
    public String doDelTraRechargemeal(TraRechargemealReq req, LoginUser loginUser) {
        if (null == req.getMealid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "mealid不能为空");
        }
        try {
            TraRechargemeal tra = new TraRechargemeal();
            BeanUtils.copyProperties(req, tra);
            tra.setIsDelete(true);
            tra.setUpdateUser(loginUser.getAccno());
            traRechargemealService.updateByPrimaryKeySelective(tra);
            return Constants.SUCCESS_MSG;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Constants.FAIL_MSG;
    }

    @Override
    public PageResult traRechargemealList(TraRechargemealReq req, PageBounds page) {
        Page<TraRechargemealResponse> list = traRechargemealService.traRechargemealList(req, page.toRowBounds());
		/*if (!CollectionUtils.isEmpty(list)) {
			for (TraRechargemealResponse traRechargemealResponse : list) {
				BigDecimal realamt = traRechargemealResponse.getRealamt();
				realamt = realamt.setScale(2, BigDecimal.ROUND_DOWN);
				traRechargemealResponse.setRealamt(realamt);
			}
		}*/
        return PageResult.getPageResult(page, list);
    }

}
