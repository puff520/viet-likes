/**
 *
 */
package com.likes.modules.admin.common.service.impl;

import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.mybatis.entity.MemHotsearch;
import com.likes.common.mybatis.entity.MemLevel;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.mybatis.mapper.MemHotsearchMapper;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemHotsearchService;
import com.likes.common.service.member.MemLevelService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.modules.admin.common.service.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;


/**
 * @author
 */
@Service
public class CommonServiceImpl extends BaseServiceImpl implements CommonService {

    @Resource
    private SysBusParamService sysBusParamService;
    @Resource
    private MemHotsearchMapper memHotsearchMapper;
    @Resource
    private MemHotsearchService memHotsearchService;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemLevelService memLevelService;

    /**
     * @param pcode
     * @return
     */
    @Override
    public List<SysBusparameter> getParamList(String pcode) {
        return this.sysBusParamService.selectByParedubpcode(pcode);
    }

    @Override
    public Long doHotSearch(MemHotsearch m) {
        MemHotsearch memHotsearch = memHotsearchService.find(m);
        if (memHotsearch != null) {
            //更新次数
            memHotsearchService.updatenum(memHotsearch);
            return memHotsearch.getSearchid();
        } else {
            //新增
            m.setSearchnums(1l);
            if (null == m.getSearchtype()) {
                m.setSearchtype(1);
            }
            memHotsearchMapper.insertSelective(m);
            return m.getSearchid();
        }
    }

    @Override
    public List<MemHotsearch> getHotSearch(MemHotsearch memHotsearch) {
        if (null == memHotsearch.getSearchtype()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_998.getCode(), "类型为空");
        }
        return memHotsearchService.getHotSearch(memHotsearch);
    }


    @Override
    public void updateAddMemGoldchange(Double goldnum, String accno, Long refid, Integer changetype,
                                       String opnote) {
        //Double goldnum = realamt.doubleValue();
        // 更新金币参数
        BigDecimal tradeOffAmount = getTradeOffAmount(new BigDecimal(goldnum));
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setGoldnum(tradeOffAmount.doubleValue());
        usersRequest.setAccno(accno);
        // 插入金币变动数据

        MemGoldchange memGoldchange = new MemGoldchange();
        memGoldchange.setRefid(refid);
        memGoldchange.setAccno(accno);
        memGoldchange.setChangetype(changetype);
        memGoldchange.setQuantity(tradeOffAmount);
        memGoldchange.setAmount(tradeOffAmount);
        memGoldchange.setCreateUser(accno);
        memGoldchange.setUpdateUser(accno);
        memGoldchange.setOpnote(opnote);
        // 再insert处枷锁
        int i = memGoldchangeService.insertSelectiveMemGoldchange(memGoldchange);
        if (i > 0) {
            memBaseinfoService.updateAddGold(usersRequest);
            //logger.info(opnote + "，返回播币成功");
        } else {
            //logger.info("已经加过金币");
            throw new BusinessException(StatusCode.LIVE_ERROR_2104.getCode(), "已经加过金币");
        }

    }

    @Override
    public boolean checkUserMemberLevelExpire(Integer levelSeq, String accno) {
        int count = memLevelService.checkUserMemberLevelExpire(levelSeq,accno);
        return count <= 0;
    }

//	@Override
//	public List<CountryArea> areas(String parareacode) {
//		if (StringUtils.isEmpty(parareacode)) {
//			parareacode = "086";
//		}
//		return this.sysCountryareacodeMapper.selectByParareacode(parareacode);
//	}
//
//	@Override
//	public SysCountryareacode getArea(String areacode) {
//
//		return this.sysCountryareacodeMapper.selectByPrimaryKey(areacode);
//	}
//
//	@Override
//	public List<CountryArea> newareas(String parareacode) {
//		if (StringUtils.isEmpty(parareacode) || "086".equals(parareacode)) {
//			return this.sysCountryareacodeMapper.selectByParareacode("086");
//		}
//
//		List<CountryArea> provinces = this.sysCountryareacodeMapper.selectByParareacode("086");
//
//		List<String> selectArea = new ArrayList<String>();
//
//		if (parareacode.endsWith("0000000000")) {
//			selectArea.add(parareacode.substring(0, 2) + "0000000000");
//		} else if (parareacode.endsWith("00000000")) {
//			selectArea.add(parareacode.substring(0, 2) + "0000000000");
//			selectArea.add(parareacode.substring(0, 4) + "00000000");
//		} else if (parareacode.endsWith("000000")) {
//			selectArea.add(parareacode.substring(0, 2) + "0000000000");
//			selectArea.add(parareacode.substring(0, 4) + "00000000");
//			selectArea.add(parareacode.substring(0, 6) + "000000");
//		} else if (parareacode.endsWith("000")) {
//			selectArea.add(parareacode.substring(0, 2) + "0000000000");
//			selectArea.add(parareacode.substring(0, 4) + "00000000");
//			selectArea.add(parareacode.substring(0, 6) + "000000");
//			selectArea.add(parareacode.substring(0, 9) + "000");
//		} else if (parareacode.length() == 12) {
//			selectArea.add(parareacode.substring(0, 2) + "0000000000");
//			selectArea.add(parareacode.substring(0, 4) + "00000000");
//			selectArea.add(parareacode.substring(0, 6) + "000000");
//			selectArea.add(parareacode.substring(0, 9) + "000");
//			selectArea.add(parareacode);
//		}
//		if (selectArea.size() >= 1) {
//			for (CountryArea province : provinces) {
//
//				if (selectArea.get(0).equals(province.getAreacode())) {
//					province.setChildren(this.sysCountryareacodeMapper.selectByParareacode(province.getAreacode()));
//
//					if (selectArea.size() >= 2 && province.getChildren() != null && province.getChildren().size() > 0) {
//						for (CountryArea city : province.getChildren()) {
//							if (selectArea.get(1).equals(city.getAreacode())) {
//								city.setChildren(this.sysCountryareacodeMapper.selectByParareacode(city.getAreacode()));
//
//								if (selectArea.size() >= 3 && city.getChildren() != null
//										&& city.getChildren().size() > 0) {
//									for (CountryArea county : city.getChildren()) {
//										if (selectArea.get(2).equals(county.getAreacode())) {
//											county.setChildren(this.sysCountryareacodeMapper
//													.selectByParareacode(county.getAreacode()));
//											if (selectArea.size() >= 4 && county.getChildren() != null
//													&& county.getChildren().size() > 0) {
//												for (CountryArea community : county.getChildren()) {
//													if (selectArea.get(3).equals(community.getAreacode())) {
//														community.setChildren(this.sysCountryareacodeMapper
//																.selectByParareacode(community.getAreacode()));
//														for (CountryArea street : community.getChildren()) {
//															street.setChildren(null);
//														}
//														break;
//
//													}
//												}
//											}
//										}
//									}
//								}
//								break;
//							}
//						}
//					}
//					break;
//				}
//			}
//		}
//
//		return provinces;
//	}

}
