package com.likes.common.mybatis.mapperext.sg;


import com.likes.common.mybatis.entity.FcssqLotterySg;

import java.util.Map;

/**
 * @Date:Created in 16:142019/12/7
 * @Descriotion
 * @Author
 **/
public interface FcssqLotterySgMapperExt {
    int openCountByExample(Map<String, Object> map);

    int updateByIssue(FcssqLotterySg updateSg);
}
