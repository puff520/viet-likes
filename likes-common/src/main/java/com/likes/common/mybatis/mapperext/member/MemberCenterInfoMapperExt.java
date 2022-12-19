package com.likes.common.mybatis.mapperext.member;


import com.likes.common.model.vo.member.WinnersRankingVO;

import java.util.List;

public interface MemberCenterInfoMapperExt {
    List<WinnersRankingVO> winnersRanking();
}
