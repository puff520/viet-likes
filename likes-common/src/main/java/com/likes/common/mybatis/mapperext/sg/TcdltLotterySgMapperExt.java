package com.likes.common.mybatis.mapperext.sg;


import com.likes.common.mybatis.entity.TcdltLotterySg;

import java.util.List;
import java.util.Map;

/**
 * @Date:Created in 16:142019/12/7
 * @Descriotion
 * @Author
 **/
public interface TcdltLotterySgMapperExt {
    int openCountByExample(Map<String, Object> map);

    int updateByIssue(TcdltLotterySg updateSg);

    int insertBatch(List<TcdltLotterySg> list);
}
