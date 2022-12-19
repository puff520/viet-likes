package com.likes.common.model;

import java.util.List;

public class DomainList {
    // 域名类型： 0：乐跑app域名 1：乐跑管理后台域名 2：乐跑web域名 3：乐跑下载域名 4：幽兰web域名 5幽兰管理后台域名 6：awsS3视频资源 7：awsS3图片资源 8：三分时时彩
    private Integer domainkind;

    private List<String> list;

    public DomainList() {
        super();
    }

    public DomainList(Integer domainkind, List<String> list) {
        super();
        this.domainkind = domainkind;
        this.list = list;
    }

    public Integer getDomainkind() {
        return domainkind;
    }

    public void setDomainkind(Integer domainkind) {
        this.domainkind = domainkind;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
