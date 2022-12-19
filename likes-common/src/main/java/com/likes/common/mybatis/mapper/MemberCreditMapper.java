package com.likes.common.mybatis.mapper;

import com.github.pagehelper.Page;
import com.likes.common.model.request.CreditChangeRequest;
import com.likes.common.model.request.UserCreditRequest;
import com.likes.common.model.vo.member.MemberCreditVO;
import com.likes.common.mybatis.entity.MemberCredit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MemberCreditMapper extends Mapper<MemberCredit> {

    @Update("update member_credit set integral = integral + #{integral},update_time = NOW() where member_no = #{memNo} ")
    int updateMemberCreditByMemNo(@Param("integral") Integer integral, @Param("memNo") String memNo);


    @Select("${sql} ")
    List<String> selectModiyfList(String sql);


    int modifyBatch(@Param("credit") MemberCredit credit, @Param("ids") List<String> ids);

    @Update("update member_credit set integral = integral +#{integral} where member_no = #{accno} ")
    int modifyIntegral(@Param("integral") Integer integral, @Param("accno") String accno);

    Page<MemberCreditVO> memCreditList(CreditChangeRequest creditRequest, RowBounds rowBounds);


}
