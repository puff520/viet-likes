package com.likes.common.service.sys;

import com.likes.common.enums.SysParameterEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.vo.sys.SystemInfoSetVO;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.util.CollectionUtil;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SysParamService {

    /**
     * 获取系统配置
     *
     * @param sysParameterEnum 系统配置码枚举
     * @return
     * @throws BusinessException
     */
    SysParameter getByCode(SysParameterEnum sysParameterEnum) throws BusinessException;

    /**
     * 获取系统配置
     *
     * @param sysparamcode 系统配置码
     * @return
     * @throws BusinessException
     */
    SysParameter getByCode(String sysparamcode) throws BusinessException;

    /**
     * 分页获取系统配置
     *
     * @param req
     * @param page
     * @return
     */
    default PageResult getList(SysParameter req, PageBounds page) {
        return new PageResult();
    }

    /**
     * 根据id获取系统配置
     *
     * @param sysparamid
     * @return
     */
    default SysParameter getDetails(Long sysparamid) {
        return null;
    }

    /**
     * 新增系统配置
     *
     * @param req
     * @param loginUser
     * @return
     */
    default Long save(SysParameter req, LoginUser loginUser) {
        return 0L;
    }

    /**
     * 修改配置
     *
     * @param sysParameter
     * @return
     */
    default Integer edit(SysParameter sysParameter) {
        return edit(sysParameter, new LoginUser());
    }

    /**
     * 修改配置
     *
     * @param req
     * @param loginUser
     * @return
     */
    default Integer edit(SysParameter req, LoginUser loginUser) {
        return 0;
    }

    /**
     * 删除配置
     *
     * @param sysparaid
     * @param loginUser
     * @return
     */
    default String doDel(Long sysparaid, LoginUser loginUser) {
        return "";
    }

    /**
     * 获取统一配置码的列表信息
     *
     * @param name 配置码
     * @return
     */
    default List<String> getSameCodeParamList(String name) {
        return getSameCodeParamList(name, "asc");
    }

    /**
     * 获取同一配置码的列表信息
     *
     * @param code 配置码
     * @param sort 排序方式：ASC，DESC 不区分大小写
     * @return
     */
    default List<String> getSameCodeParamList(String code, String sort) {
        return new ArrayList<>();
    }

    /**
     * 获取同一配置码的值，以逗号分隔
     *
     * @param code 配置码
     * @return
     */
    default String getSameCodeParamsValueString(String code) {
        return getSameCodeParamsValueString(code, "asc");
    }

    /**
     * 获取同一配置码的值，以逗号分隔
     *
     * @param code 配置码
     * @param sort 排序方式：ASC，DESC 不区分大小写
     * @return
     */
    default String getSameCodeParamsValueString(String code, String sort) {
        List<String> valueList = getSameCodeParamList(code, sort);
        return CollectionUtil.toAppendString(valueList);
    }

    List<SysParameter> queryByCodeNames(@Param("codeNames") List<String> codeNames);

    /**
     * 获取系统设置的信息
     *
     * @return
     */
    SystemInfoSetVO listSystemInfoSet();

    /**
     * 获取产品配置和活动设置的信息
     *
     * @return
     */
    Map<String, Object> listProductSet();

    List<SysParameter> reloadAll();

    /**
     * 查询注册型用户是否有评论等权限
     *
     * @return
     */
    String queryRecommendAuth();

//    /**
//     * 根据key获取配置信息
//     *
//     * @param key key值
//     * @return
//     */
//    SysParameter queryByKey(SysParameterEnum key);

    /**
     * 获取产品配置和活动设置的信息
     *
     * @param names 系统设置key
     * @return
     */
    Map<String, SysParameter> getProductsByName(List<String> names);

    List<SysParameter> queryList();

    List<SysParameter> queryList(SysParameter systemInfo);

    SysParameter queryByName(String name);

    List<String> queryAllKey();

    boolean deleteAllCaches();

    /**
     * 查询主播端平台对应配置信息
     *
     * @param platformCode
     * @return
     */
    List<SysParameter> queryBasAnchorPlatformConfigs(String platformCode);

    /**
     * 查询主播端平台对应信息，对queryBasAnchorPlatformConfigs方法的再次处理
     *
     * @param platformCode
     * @return
     */
    Map<String, Object> queryBasAnchorPlatformInfos(String platformCode);


    Map<String, String> getkefuwechat();

    void initSysParams();


    /**
     * 获取产品配置和活动设置的信息
     *
     * @param codes 系统设置key
     * @return
     */
    Map<String, String> getParamValueByCode(List<String> codes);
}