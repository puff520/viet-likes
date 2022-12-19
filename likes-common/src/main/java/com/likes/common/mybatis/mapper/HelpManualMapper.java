package com.likes.common.mybatis.mapper;

import com.likes.common.model.HelpManual;
import com.likes.common.model.LoginUser;
import com.likes.common.model.request.TaskRequest;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HelpManualMapper extends Mapper<HelpManual> {

    List<HelpManual> getList(String keyWord);
}