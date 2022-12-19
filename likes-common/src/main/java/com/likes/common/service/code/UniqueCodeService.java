package com.likes.common.service.code;

import com.likes.common.enums.UniqueCodeEnum;

import java.util.Set;

/**
 * ClassName: UniqueCodeService
 * Description: 非主播端随机码生成service
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/7/17 16:04
 */
public interface UniqueCodeService {

    /**
     * 公用获取唯一随机码
     *
     * @param uniqueCodeEnum
     * @return
     */
    String getUniqueCode(UniqueCodeEnum uniqueCodeEnum);

    /**
     * 获取用户唯一id码
     *
     * @return
     */
    String getMemUniqueId();

    /**
     * 获取用户唯一邀请码
     *
     * @return
     */
    String getMemInviteCode();

    /**
     * 加载指定唯一码
     *
     * @param uniqueCodeEnum
     * @return
     */
    boolean loadUniqueCode(UniqueCodeEnum uniqueCodeEnum);

    /**
     * 加载已有的所有随机码
     *
     * @param uniqueCodeEnum
     * @return
     */
    Set<String> loadAllUniqueCode(UniqueCodeEnum uniqueCodeEnum);

    /**
     * 批量产生随机码
     *
     * @param uniqueCodeEnum
     * @return
     */
    boolean generatorUniqueCodes(UniqueCodeEnum uniqueCodeEnum);

    /**
     * 更新已使用code状态
     *
     * @param maxRecordId
     * @param uniqueCodeEnum
     * @return
     */
    boolean updateCodeStatus(int maxRecordId, UniqueCodeEnum uniqueCodeEnum);

}
