package com.likes.common.mybatis.mapperext.sg;


import com.likes.common.mybatis.entity.TensscLotterySg;

import java.util.List;
import java.util.Map;

/**
 * @Date:Created in 16:142019/12/7
 * @Descriotion
 * @Author
 **/
public interface TensscLotterySgMapperExt {
    int openCountByExample(Map<String, Object> map);

    int updateByIssue(TensscLotterySg updateSg);

    int insertBatch(List<TensscLotterySg> list);
}
