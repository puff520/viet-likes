package com.likes.common.mybatis.mapperext.sg;


import com.likes.common.mybatis.entity.FivesscLotterySg;

import java.util.List;
import java.util.Map;

/**
 * @Date:Created in 16:142019/12/7
 * @Descriotion
 * @Author
 **/
public interface FivesscLotterySgMapperExt {
    int openCountByExample(Map<String, Object> map);

    int updateByIssue(FivesscLotterySg updateSg);

    int insertBatch(List<FivesscLotterySg> list);
}
