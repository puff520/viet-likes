package com.likes.common.service.sys;

import com.alibaba.fastjson.JSONArray;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.response.SysBusparameterResp;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * ClassName: SysBusParamService
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/19 16:52
 */
public interface SysBusParamService {

    /**
     * 分页查询
     *
     * @param sysBusparameter
     * @param page
     * @return
     */
    JSONArray list(SysBusparameter sysBusparameter, PageBounds page);

    List<SysBusparameterResp> listAll(SysBusparameter sysBusparameter);

    /**
     * 直接下级业务列表
     *
     * @param code
     * @return
     */
//    List<SysBusparameter> getParamSub(String code);

    /**
     * 新增
     *
     * @param sysBusparameter
     * @param loginUser
     * @return
     */
    Long doSaveParam(SysBusparameter sysBusparameter, LoginUser loginUser);

    Integer doSaveParam(String refcode,String code, String value, LoginUser loginUser);

    /**
     * 更新
     *
     * @param sysBusparameter
     * @param loginUser
     * @return
     */
    Integer doUpdateParam(SysBusparameter sysBusparameter, LoginUser loginUser);

    /**
     * 详细
     *
     * @param busparamid
     * @return
     */
    SysBusparameter getDetail(Long busparamid);

    /**
     * 删除
     *
     * @param busparamid
     * @param loginUser
     * @return
     */
    String doDeleteParam(Long busparamid, LoginUser loginUser);

    /**
     * 查询除自己的所有子节点 busparamcode
     *
     * @param busparamcode
     * @return
     */
    List<String> getChild(String busparamcode);

    /**
     * 根据查询
     *
     * @param busparamcodes
     * @return
     */
    List<SysBusparameter> getByCodes(List<String> busparamcodes);

    /**
     * 分页查询
     *
     * @param sysBusparameter
     * @param page
     * @return
     */
    PageResult getList(SysBusparameter sysBusparameter, PageBounds page);

    List<SysBusparameter> selectByParedubpcode(String busparamcode);

//    List<SysBusparameter> selectByParedubpcode(@Param("pbusparamcode") String pbusparamcode);

    List<SysBusparameter> getList(SysBusparameter sysBusparameter);

    SysBusparameter getRepeat(SysBusparameter sysBusparameter);

    SysBusparameter selectByBusparamcode(String busparamcode);

//    List<String> getChild(String busparamcode);

//    List<SysBusparameter> getByCodes(List<String> list);

    List<SysBusparameterResp> selectByCode(@Param("busparamcode") String busparamcode);

    Page<SysBusparameter> getList2(SysBusparameter req, RowBounds rowBounds);

    List<SysBusparameter> getNormList(@Param("pbusparamcode") String pbusparamcode);

    SysBusparameter getRandomOne(@Param("pbusparamcode") String pbusparamcode);

    List<String> queryAllBusKey();

    boolean deleteAllCaches();

    String getRandomHeadImg();

    void  updateNote(String noteinfo);

    String getNote();
}
