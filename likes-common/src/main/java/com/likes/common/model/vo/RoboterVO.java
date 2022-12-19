package com.likes.common.model.vo;

/**
 * @author lucien
 * 机器人
 * @create 2020/8/20 15:44
 */
public class RoboterVO {

    String accno;
    String headimg;
    String nickname;
    String memlevel;
    String anifileid;

    public RoboterVO() {
    }

    public RoboterVO(String accno, String headimg, String nickname, String memlevel, String anifileid) {
        super();
        this.accno = accno;
        this.headimg = headimg;
        this.nickname = nickname;
        this.memlevel = memlevel;
        this.anifileid = anifileid;
    }


    public String getAnifileid() {
        return anifileid;
    }

    public void setAnifileid(String anifileid) {
        this.anifileid = anifileid;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
