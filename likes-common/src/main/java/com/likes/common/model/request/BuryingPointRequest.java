package com.likes.common.model.request;

public class BuryingPointRequest {


    private Integer mtype;

    private String imeimd5;

    private String mcode;

    private String idfa;

    private String androididmd5;

    private String ip;



    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    public String getImeimd5() {
        return imeimd5;
    }

    public void setImeimd5(String imeimd5) {
        this.imeimd5 = imeimd5;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getAndroididmd5() {
        return androididmd5;
    }

    public void setAndroididmd5(String androididmd5) {
        this.androididmd5 = androididmd5;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
