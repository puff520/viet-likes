package com.likes.common.service.money;

import com.likes.common.mybatis.entity.TraOrdertracking;
import com.likes.common.mybatis.entity.TraOrdertrackingExample;

import java.util.List;

public interface TraOrdertrackingService {

    int countByExample(TraOrdertrackingExample example);

    int deleteByExample(TraOrdertrackingExample example);

    int deleteByPrimaryKey(Long trackid);

    int insert(TraOrdertracking record);

    int insertSelective(TraOrdertracking record);

    TraOrdertracking selectOneByExample(TraOrdertrackingExample example);

    List<TraOrdertracking> selectByExample(TraOrdertrackingExample example);

    TraOrdertracking selectByPrimaryKey(Long trackid);

    int updateByExampleSelective(TraOrdertracking record, TraOrdertrackingExample example);

    int updateByExample(TraOrdertracking record, TraOrdertrackingExample example);

    int updateByPrimaryKeySelective(TraOrdertracking record);

    int updateByPrimaryKey(TraOrdertracking record);

    int insertTraOrdertracking(TraOrdertracking traOrdertracking);
}
