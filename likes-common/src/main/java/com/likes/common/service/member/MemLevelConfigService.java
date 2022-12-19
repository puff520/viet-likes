package com.likes.common.service.member;

import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.MemLevelConfigDto;
import com.likes.common.model.response.level.MemberLevelResponse;
import com.likes.common.mybatis.entity.MemLevelConfig;
import com.likes.common.mybatis.entity.MemLevelConfigExample;
import com.likes.common.service.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * ClassName: MemLevelConfigService
 * Description: 描述
 *
 * @author dapan
 * @since JDK 1.8
 * date: 2020/6/19 14:42
 */
public interface MemLevelConfigService extends BaseService {


    /**
     * 根据条件 删除记录
     */
    int deleteByExample(MemLevelConfigExample example);

    /**
     * 根据主键 删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加记录
     */
    int insert(MemLevelConfig record);

    /**
     * 添加记录（非空）
     */
    int insertSelective(MemLevelConfig record);

    /**
     * 根据条件 查询单条记录
     */
    MemLevelConfig selectOneByExample(MemLevelConfigExample example);

    /**
     * 根据条件 查询记录
     */
    List<MemLevelConfig> selectByExample(MemLevelConfigExample example);

    /**
     * 根据主键 查询记录
     */
    MemLevelConfig selectByPrimaryKey(Long id);

    /**
     * 根据条件 更新记录（非空）
     */
    int updateByExampleSelective(@Param("record") MemLevelConfig record, @Param("example") MemLevelConfigExample example);

    /**
     * 根据条件 更新记录
     */
    int updateByExample(@Param("record") MemLevelConfig record, @Param("example") MemLevelConfigExample example);

    /**
     * 根据主键 更新记录（非空）
     */
    int updateByPrimaryKeySelective(MemLevelConfig record);

    /**
     * 根据主键 更新记录
     */
    int updateByPrimaryKey(MemLevelConfig record);

    // MapperExt###############################################################

    /**
     * 依据充值金额 查询出当前等级和下一个等级
     */
    List<MemLevelConfig> selectMemLevelConfigByAmount(Double amount);

    // live-manage###############################################################

    List<MemLevelConfig> selectMemLevlConfigByLevelSeq();

    List<MemLevelConfig> selectMemLevlConfigDownLevelSeq(Integer leveseq);

    /**
     * 分页查询会员等级配置
     */
    PageResult<List<MemLevelConfig>> memLevelConfigListForManager(Integer pageNo, Integer pageSize);


    /**
     * 编辑会员等级配置
     */
    boolean updateForManager(MemLevelConfig memLevelConfig);

    /**
     * 新增会员等级配置
     */
    boolean insertForManager(MemLevelConfig memLevelConfig);

    /**
     * 新增会员等级配置
     */
    boolean deleteForManager(Long id);

    // Others###############################################################


    /**
     * 获取所有的级别配置列表
     */
    List<MemLevelConfig> memLevelConfigAllList();

    /**
     * 获取所有的级别配置Map
     */
    HashMap<String, MemLevelConfig> memLevelConfigAllMap();

    /**
     * 获取这个用户下的等级信息
     *
     * @param accno
     * @return
     */
    MemberLevelResponse getMemLevelConfig(String accno);

    /**
     * 通过等级获取配置信息
     *
     * @param level
     * @return
     */
    MemLevelConfig getMemLevelConfigForLevel(String level);

    MemLevelConfig getMemLevelConfigForConfigId(Long configId);

    List<MemLevelConfigDto> memLevelConfigAlllist();

}
