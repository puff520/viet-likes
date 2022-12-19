package com.likes.common.mybatis.mapperext.sg;

import com.likes.common.mybatis.entity.DzpceggLotterySg;

import java.util.Map;

/**
 * @Date:Created in 22:102019/12/16
 * @Descriotion
 * @Author
 **/
public interface DzpceggLotterySgMapperExt {
    int openCountByExample(Map<String,Object> map);
    int updateByPrimaryKey(DzpceggLotterySg record);

    int updateByIssue(DzpceggLotterySg record);
}
