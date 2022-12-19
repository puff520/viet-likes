package com.likes.modules.admin.users.service.impl;

import com.likes.common.exception.BusinessException;
import com.likes.common.model.GreatPersonList;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.GreatPerson;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.modules.admin.users.service.GreatPersonService;
import com.github.pagehelper.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 阿布 牛人
 */

@Service
public class GreatPersonServiceImpl extends BaseServiceImpl implements GreatPersonService {

    @Resource
    private com.likes.common.service.member.MemBaseinfoService MemBaseinfoService;
    @Resource
    private TraOrderinfomService traOrderinfomMapperService;

    @Override
    public List<GreatPerson> getGreatPersonTop() throws BusinessException {

        List<GreatPerson> list = new ArrayList<>();
        // 1.财气主播 收到最多
        GreatPerson caiqizhubo = traOrderinfomMapperService.getCaiqizhubo();
        if (caiqizhubo != null) {
            list.add(caiqizhubo);
        }

        // 2.神算子 彩票胜率 最高
        GreatPerson shensuanzi = traOrderinfomMapperService.getShenSuanZi();
        if (shensuanzi != null) {
            list.add(shensuanzi);
        }

        // 3.财富子 消费最高
        GreatPerson caifuzi = traOrderinfomMapperService.getCaifuzi();
        if (caifuzi != null) {
            list.add(caifuzi);
        }

        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(o -> {
                if (o.getGptype() == 2) {
                    String gpval = o.getGpval();
                    if (StringUtils.isNotEmpty(gpval)) {
                        Double gpvalDouble = new BigDecimal(gpval).setScale(3, BigDecimal.ROUND_DOWN).doubleValue();
                        gpvalDouble = new BigDecimal(gpvalDouble * 100).setScale(3, BigDecimal.ROUND_DOWN).doubleValue();
                        o.setGpval(gpvalDouble + "%");
                    } else {
                        o.setGpval(0 + "%");
                    }
                }
            });
        }
        return list;
    }

    @Override
    public PageResult getList(PageBounds page) throws BusinessException {
        Page<GreatPersonList> pagelist = this.MemBaseinfoService.getGpList(page.toRowBounds());
        /*for (GreatPersonList gp : pagelist) {
            if (StringUtils.isNotEmpty(gp.getHeadimg())) {
                gp.setHeadimg(gp.getHeadimg());
            }
        }*/
        return PageResult.getPageResult(page, pagelist);
    }
//
//	@Override
//	public Map<String, Object> getFollowOdd(Long orderid, LoginUser loginUser) throws BusinessException {
//		CaiRequest req = this.lotSscorderMapperExt.getFollowOdd(orderid);
//		return this.shiShiCaiWaiService.getSSCDetail(req, loginUser);
//	}

}
