package com.likes.common.mybatis.mapperext.sg;


import com.likes.common.mybatis.entity.TjsscLotterySg;

import java.util.List;
import java.util.Map;

/**
 * @Date:Created in 16:142019/12/7
 * @Descriotion
 * @Author
 **/
public interface TjsscLotterySgMapperExt {
    int openCountByExample(Map<String, Object> map);

    int updateByIssue(TjsscLotterySg updateSg);

    int insertBatch(List<TjsscLotterySg> list);
}
