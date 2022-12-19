package com.likes.common.mybatis.mapperext.sys;

import com.likes.common.model.dto.sys.SysTagsDO;
import com.likes.common.mybatis.entity.SysTags;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
@Mapper
public interface SysTagsMapperExt {


    List<String> getTagBQName(String[] array);

	Page<SysTags> getList(SysTags req, RowBounds rowBounds);

    SysTags findByName(SysTags sysTags);


    Page<SysTagsDO> getListTuwen(SysTags req, RowBounds rowBounds);

    List<SysTags> getTagBQNameForTages(String[] userTags);

    List<SysTags> getTagBQNameList(String[] userTags);
}