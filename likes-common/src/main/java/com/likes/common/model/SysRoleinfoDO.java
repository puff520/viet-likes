package com.likes.common.model;

import com.likes.common.model.dto.sys.SysFunctionorgDO;
import com.likes.common.mybatis.entity.SysRoleinfo;

import java.util.ArrayList;
import java.util.List;

public class SysRoleinfoDO extends SysRoleinfo {

    private List<SysFunctionorgDO> functionlist = new ArrayList<>();

    public List<SysFunctionorgDO> getFunctionlist() {
        return functionlist;
    }

    public void setFunctionlist(List<SysFunctionorgDO> functionlist) {
        this.functionlist = functionlist;
    }

}
