package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysFunctionorg;

import java.util.List;

public class SysFunctionorgDO extends SysFunctionorg {

    private List<SysFunctionorgDO> children;

    private Integer checkbox;

    private Boolean checked;

    private Long sysroleid;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Long getSysroleid() {
        return sysroleid;
    }

    public void setSysroleid(Long sysroleid) {
        this.sysroleid = sysroleid;
    }

    public Integer getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Integer checkbox) {
        this.checkbox = checkbox;
    }

    public List<SysFunctionorgDO> getChildren() {
        return children;
    }

    public void setChildren(List<SysFunctionorgDO> children) {
        this.children = children;
    }

}
