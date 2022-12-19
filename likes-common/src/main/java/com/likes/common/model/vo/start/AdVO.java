package com.likes.common.model.vo.start;


import com.likes.common.enums.ad.AdSiteTypeEnum;
import com.likes.common.mybatis.entity.AdBasic;
import com.likes.common.mybatis.entity.AdPhoto;

/**
 * @author lzy
 * @create 2018-09-07 11:42
 **/
public class AdVO {

    private Integer id;

    /**
     * 说明: 标题
     */
    private String title;

    /**
     * 说明: 有效开始时间
     */
    private String startTime;

    /**
     * 说明: 有效结束时间
     */
    private String endTime;

    /**
     * 说明: 自动隐藏：0，否；1，是
     */
    private Integer hide;

    /**
     * 说明: 是否关闭：0，否；1，是
     */
    private Integer close;

    /**
     * 说明: 排序值
     */
    private Integer sort;

    /**
     * 说明: 发布系统
     */
    private String publish;

    /**
     * 说明: 安卓图片类型id
     */
    private Integer aid;

    /**
     * 说明: 安卓广告位置id
     */
    private Integer asiteId;

    /**
     * 说明: 安卓图片路径
     */
    private String aphoto;

    /**
     * 说明: 安卓跳转地址
     */
    private String aurl;

    /**
     * 说明: ios图片类型id
     */
    private Integer iid;

    /**
     * 说明: ios广告位置id
     */
    private Integer isiteId;

    /**
     * 说明: ios图片路径
     */
    private String iphoto;

    /**
     * 说明: ios跳转地址
     */
    private String iurl;

    /**
     * 说明: web图片类型id
     */
    private Integer wid;

    /**
     * 说明: web广告位置id
     */
    private Integer wsiteId;

    /**
     * 说明: web图片路径
     */
    private String wphoto;

    /**
     * 说明: web跳转地址
     */
    private String wurl;

    /**
     * 说明: h5图片类型id
     */
    private Integer hid;

    /**
     * 说明: h5广告位置id
     */
    private Integer hsiteId;

    /**
     * 说明: h5图片路径
     */
    private String hphoto;

    /**
     * 说明: h5跳转地址
     */
    private String hurl;

    /**
     * ,跳转ID活动
     */
    private Integer targetId;

    /**
     * 说明: -1，使用原有的跳转url，0，前端原生界面跳转，1,跳转ID活动
     */
    private Integer targetType;


    public void setAdBasic(AdBasic adBasic) {
        if (adBasic != null) {
            this.id = adBasic.getId();
            this.title = adBasic.getTitle();
            this.startTime = adBasic.getStartTime();
            this.endTime = adBasic.getEndTime();
            this.hide = adBasic.getHide();
            this.close = adBasic.getClose();
            this.sort = adBasic.getSort();
        }
    }

    public void setAdPhoto(AdPhoto adPhoto) {
        if (adPhoto != null) {
            Integer type = adPhoto.getType();
            this.targetId = adPhoto.getTargetId();
            this.targetType = adPhoto.getTargetType();
            if (type == AdSiteTypeEnum.ANDROID.getValue()) {
                this.aid = adPhoto.getId();
                this.asiteId = adPhoto.getSiteId();
                this.aphoto = adPhoto.getPhoto();
                this.aurl = adPhoto.getUrl();
            } else if (type == AdSiteTypeEnum.IOS.getValue()) {
                this.iid = adPhoto.getId();
                this.isiteId = adPhoto.getSiteId();
                this.iphoto = adPhoto.getPhoto();
                this.iurl = adPhoto.getUrl();
            } else if (type == AdSiteTypeEnum.WEB.getValue()) {
                this.wid = adPhoto.getId();
                this.wsiteId = adPhoto.getSiteId();
                this.wphoto = adPhoto.getPhoto();
                this.wurl = adPhoto.getUrl();
            } else if (type == AdSiteTypeEnum.H5.getValue()) {
                this.hid = adPhoto.getId();
                this.hsiteId = adPhoto.getSiteId();
                this.hphoto = adPhoto.getPhoto();
                this.hurl = adPhoto.getUrl();
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
    }

    public Integer getClose() {
        return close;
    }

    public void setClose(Integer close) {
        this.close = close;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getAsiteId() {
        return asiteId;
    }

    public void setAsiteId(Integer asiteId) {
        this.asiteId = asiteId;
    }

    public String getAphoto() {
        return aphoto;
    }

    public void setAphoto(String aphoto) {
        this.aphoto = aphoto;
    }

    public String getAurl() {
        return aurl;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getIsiteId() {
        return isiteId;
    }

    public void setIsiteId(Integer isiteId) {
        this.isiteId = isiteId;
    }

    public String getIphoto() {
        return iphoto;
    }

    public void setIphoto(String iphoto) {
        this.iphoto = iphoto;
    }

    public String getIurl() {
        return iurl;
    }

    public void setIurl(String iurl) {
        this.iurl = iurl;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getWsiteId() {
        return wsiteId;
    }

    public void setWsiteId(Integer wsiteId) {
        this.wsiteId = wsiteId;
    }

    public String getWphoto() {
        return wphoto;
    }

    public void setWphoto(String wphoto) {
        this.wphoto = wphoto;
    }

    public String getWurl() {
        return wurl;
    }

    public void setWurl(String wurl) {
        this.wurl = wurl;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getHsiteId() {
        return hsiteId;
    }

    public void setHsiteId(Integer hsiteId) {
        this.hsiteId = hsiteId;
    }

    public String getHphoto() {
        return hphoto;
    }

    public void setHphoto(String hphoto) {
        this.hphoto = hphoto;
    }

    public String getHurl() {
        return hurl;
    }

    public void setHurl(String hurl) {
        this.hurl = hurl;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }
}
