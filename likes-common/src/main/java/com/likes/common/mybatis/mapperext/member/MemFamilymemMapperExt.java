package com.likes.common.mybatis.mapperext.member;

import com.likes.common.model.dto.AnchorDO;
import com.likes.common.model.dto.member.UserDO;
import com.likes.common.model.dto.member.MemFamilyDO;
import com.likes.common.mybatis.entity.MemFamilymem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface MemFamilymemMapperExt {
    int insertMemFamilymem(MemFamilymem memFamily);

    Page<UserDO> getNoFamilyAnchorList(MemFamilymem req, RowBounds rowBounds);

    Page<MemFamilymem> getFamilyAnchorList(MemFamilymem req, RowBounds rowBounds);

    int doDelFamilyAnchor(MemFamilymem req);

    MemFamilymem getMemFamilymem(MemFamilymem param);

    List<MemFamilymem> getAllFamilyAnchor(Long familyid);

    int doDelMemFamilymemAnchor(@Param("familyid")Long familyid , @Param("accno")String accno );

    MemFamilymem getMemFamilymemByAncorAccno(String accno);

    MemFamilyDO findFamilyByAnchorAccno(String accno);

    Page<AnchorDO> getAnchorList(Long familyid, RowBounds rowBounds);

    List<AnchorDO> getAllAnchorList(Long familyid);
}