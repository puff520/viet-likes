package com.likes.common.model.response;

public class AdResponse {

    private String bndispicurl;
    private String bndispic;
    private String bndlink;
    private String specparame;
    private String bndisptxt;
    private String investname;
    private String title;
    private String pathUrl;
    private String withinLink;
    private Integer bseatid;

    public Integer getBseatid() {
        return bseatid;
    }

    public void setBseatid(Integer bseatid) {
        this.bseatid = bseatid;
    }

    /*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirydates;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirydatee;*/
    private String seatname;
    private Integer linktype;

	/*public Date getExpirydates() {
		return expirydates;
	}

	public void setExpirydates(Date expirydates) {
		this.expirydates = expirydates;
	}

	public Date getExpirydatee() {
		return expirydatee;
	}

	public void setExpirydatee(Date expirydatee) {
		this.expirydatee = expirydatee;
	}*/

    public String getInvestname() {
        return investname;
    }

    public Integer getLinktype() {
        return linktype;
    }

    public void setLinktype(Integer linktype) {
        this.linktype = linktype;
    }

    public String getSeatname() {
        return seatname;
    }

    public void setSeatname(String seatname) {
        this.seatname = seatname;
    }

    public void setInvestname(String investname) {
        this.investname = investname;
    }

    public String getBndispicurl() {
        return bndispicurl;
    }

    public void setBndispicurl(String bndispicurl) {
        this.bndispicurl = bndispicurl;
    }

    public String getBndispic() {
        return bndispic;
    }

    public void setBndispic(String bndispic) {
        this.bndispic = bndispic;
    }

    public String getBndlink() {
        return bndlink;
    }

    public void setBndlink(String bndlink) {
        this.bndlink = bndlink;
    }

    public String getSpecparame() {
        return specparame;
    }

    public void setSpecparame(String specparame) {
        this.specparame = specparame;
    }

    public String getBndisptxt() {
        return bndisptxt;
    }

    public void setBndisptxt(String bndisptxt) {
        this.bndisptxt = bndisptxt;
    }

    public String getWithinLink() {
        return withinLink;
    }

    public void setWithinLink(String withinLink) {
        this.withinLink = withinLink;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
