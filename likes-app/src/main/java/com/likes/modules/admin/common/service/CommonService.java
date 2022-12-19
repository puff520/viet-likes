/**
 *
 */
package com.likes.modules.admin.common.service;

import com.likes.common.mybatis.entity.MemHotsearch;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.service.BaseService;

import java.util.List;

/**
 * @author
 */
public interface CommonService extends BaseService {

    /**
     * @param pcode
     * @return
     */
    List<SysBusparameter> getParamList(String pcode);

    /**
     * 热词
     * @param memHotsearch
     * @return
     */
    Long doHotSearch(MemHotsearch memHotsearch);

    /**
     * 热词列表
     * @param memHotsearch
     * @return
     */
    List<MemHotsearch> getHotSearch(MemHotsearch memHotsearch);


    /**
     * 给用户新增金币
     * @param goldnum 要新增的金币
     * @param accno 对于的用户
     * @param refid 相关ID
     * @param changetype 变化类型
     * @param opnote 备注
     */
    void updateAddMemGoldchange(Double goldnum, String accno, Long refid, Integer changetype, String opnote);

    boolean checkUserMemberLevelExpire(Integer levelSeq, String accno);
}
