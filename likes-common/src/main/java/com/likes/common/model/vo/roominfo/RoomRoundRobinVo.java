package com.likes.common.model.vo.roominfo;

import com.likes.common.model.response.UserResp;

import java.util.List;

/**
 * @ClassName RoomRoundRobinVo
 * @Description 封装直播间房间轮询信息
 * @Author ping
 * @Date 2020/6/18 17:51
 * @Version 1.0
 **/
public class RoomRoundRobinVo {

    /**
     * 房间在线人数
     */
    private Long onlinenum;
    /**
     * 是否开启聊天
     */
    private Integer istalk;
    /**
     * 房间热度
     */
    private Integer hotnum;
    /**
     * 等级榜
     */
    private List<UserResp> top;
    /**
     * 是否成为房间管理员
     */
    private Integer isusermanage;
    /**
     * 是否禁言
     */
    private Integer isforbidden;
    /**
     * 直播收入
     */
    private double liveincome;
  

    public Long getOnlinenum() {
        return onlinenum;
    }

    public void setOnlinenum(Long onlinenum) {
        this.onlinenum = onlinenum;
    }

    public Integer getIstalk() {
        return istalk;
    }

    public void setIstalk(Integer istalk) {
        this.istalk = istalk;
    }

    public Integer getHotnum() {
        return hotnum;
    }

    public void setHotnum(Integer hotnum) {
        this.hotnum = hotnum;
    }

    public List<UserResp> getTop() {
        return top;
    }

    public void setTop(List<UserResp> top) {
        this.top = top;
    }

    public Integer getIsusermanage() {
        return isusermanage;
    }

    public void setIsusermanage(Integer isusermanage) {
        this.isusermanage = isusermanage;
    }

    public Integer getIsforbidden() {
        return isforbidden;
    }

    public void setIsforbidden(Integer isforbidden) {
        this.isforbidden = isforbidden;
    }

    public double getLiveincome() {
        return liveincome;
    }

    public void setLiveincome(double liveincome) {
        this.liveincome = liveincome;
    }


}
