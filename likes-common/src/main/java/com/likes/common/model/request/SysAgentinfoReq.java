package com.likes.common.model.request;

/**
 * @author 阿布
 */
public class SysAgentinfoReq {

    private String searchstr;
    private String accno;
    // 时间起
    private String datesta;
    // 时间止
    private String dateend;

    /**
     * type=1查所有，type=0，精准查询(后台管理-代理列表用)
     */
    private Integer type;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getSearchstr() {
        return searchstr;
    }

    public void setSearchstr(String searchstr) {
        this.searchstr = searchstr;
    }

    public String getDatesta() {
        return datesta;
    }

    public void setDatesta(String datesta) {
        this.datesta = datesta;
    }

    public String getDateend() {
        return dateend;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
