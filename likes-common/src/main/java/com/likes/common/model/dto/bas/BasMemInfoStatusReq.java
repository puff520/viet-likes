package com.likes.common.model.dto.bas;


import java.util.List;

/**
 * @ClassName BasMemInfoStatusResp
 * @Description 直播间会员异常状态列表(前端请求参数封装)
 * @Author ping
 * @Date 2020/6/23 17:51
 * @Version 1.0
 **/
public class BasMemInfoStatusReq {
    /**
     * 临时 永久 多种类型禁用状态
     */
    private List<String> typeLists;
    /**
     * 会员手机账号
     */
    private String mobileno;
    /**
     * 房间名称
     */
    private String roomname;
    /**
     * 主播账号
     */
    private String anchoraccno;
    /**
     *   操作解除类型 1.临时禁言 2.永久禁言 3.临时禁入 4.永久禁入, 5.房间临时禁言 6.房间永久禁言
     *   7.房间临时禁入 8.房间永久禁入
     */
    private String type;

    /**
     *  操作对象ID
     */
    private Long id;

    private String uniqueId;

    public List<String> getTypeLists() {
        return typeLists;
    }

    public void setTypeLists(List<String> typeLists) {
        this.typeLists = typeLists;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getAnchoraccno() {
        return anchoraccno;
    }

    public void setAnchoraccno(String anchoraccno) {
        this.anchoraccno = anchoraccno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}