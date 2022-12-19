package com.likes.common.model.request;

import java.util.List;

public class FamilyIncarnateRequest {

    // yyyy-MM
    private String searchdate;
    private Long familyid;
    private List<Integer> changetypeList;
    private String accno;
    private List<String> accnoList;

    private String starttime;
    private String endtime;

    // 支付凭证
    private String payimg;
    private Long apycid;
    private Long orderid;

    public Long getTraanchorid() {
        return traanchorid;
    }

    public void setTraanchorid(Long traanchorid) {
        this.traanchorid = traanchorid;
    }

    private Long traanchorid;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    private String familyname;

    private List<Long> ids;
    private List<Integer> ordertypelist;


    public List<Integer> getOrdertypelist() {
        return ordertypelist;
    }

    public void setOrdertypelist(List<Integer> ordertypelist) {
        this.ordertypelist = ordertypelist;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public Long getApycid() {
        return apycid;
    }

    public void setApycid(Long apycid) {
        this.apycid = apycid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getPayimg() {
        return payimg;
    }

    public void setPayimg(String payimg) {
        this.payimg = payimg;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public List<String> getAccnoList() {
        return accnoList;
    }

    public void setAccnoList(List<String> accnoList) {
        this.accnoList = accnoList;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getSearchdate() {
        return searchdate;
    }

    public void setSearchdate(String searchdate) {
        this.searchdate = searchdate;
    }

    public Long getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Long familyid) {
        this.familyid = familyid;
    }

    public List<Integer> getChangetypeList() {
        return changetypeList;
    }

    public void setChangetypeList(List<Integer> changetypeList) {
        this.changetypeList = changetypeList;
    }

}
