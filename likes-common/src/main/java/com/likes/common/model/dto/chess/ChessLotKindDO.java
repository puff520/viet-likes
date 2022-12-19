package com.likes.common.model.dto.chess;

public class ChessLotKindDO {
    private Long id;
    private String name;
    //1 彩票 2 棋牌
    private Integer type;
    private String imgurl;
    //备用
    private Long parentid;
    //游戏id
    private String gameid;

    private Integer status;

    public ChessLotKindDO(Long id, String name, Integer type, String imgurl, Long parentid, Integer status) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.imgurl = imgurl;
        this.parentid = parentid;
        this.status = status;
    }

    public ChessLotKindDO(Long id, String name, Integer type, String imgurl, Long parentid, String gameid, Integer status) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.imgurl = imgurl;
        this.parentid = parentid;
        this.gameid = gameid;
        this.status = status;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
