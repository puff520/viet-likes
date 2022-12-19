package com.likes.common.model.dto;

public class FileDO {
    private String filekey;
    private String filename;
    private Integer issuccess;
    private String filekeyurl;

    public String getFilekey() {
        return filekey;
    }

    public void setFilekey(String filekey) {
        this.filekey = filekey;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(Integer issuccess) {
        this.issuccess = issuccess;
    }

    public String getFilekeyurl() {
        return filekeyurl;
    }

    public void setFilekeyurl(String filekeyurl) {
        this.filekeyurl = filekeyurl;
    }
}
