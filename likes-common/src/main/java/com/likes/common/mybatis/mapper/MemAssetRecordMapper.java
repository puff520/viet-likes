package com.likes.common.mybatis.mapper;

import com.github.pagehelper.Page;
import com.likes.common.model.vo.YuebaoRecordVO;
import com.likes.common.mybatis.entity.MemAssetRecord;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

public interface MemAssetRecordMapper extends Mapper<MemAssetRecord> {

    Page<YuebaoRecordVO> earnList(String accno, RowBounds rowBounds);

}
