package com.likes.common.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AeBetOrder implements Serializable {
    /**
     * 字段: ae_bet_order.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: ae_bet_order.order_no<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 注单号
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 字段: ae_bet_order.uname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 玩家账号
     *
     * @mbggenerated
     */
    private String uname;

    /**
     * 字段: ae_bet_order.game_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 游戏ID
     *
     * @mbggenerated
     */
    private String gameId;

    /**
     * 字段: ae_bet_order.battle_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 游戏局号
     *
     * @mbggenerated
     */
    private String battleId;

    /**
     * 字段: ae_bet_order.room_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 房间ID
     *
     * @mbggenerated
     */
    private Integer roomId;

    /**
     * 字段: ae_bet_order.bet<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 有效下注
     *
     * @mbggenerated
     */
    private BigDecimal bet;

    /**
     * 字段: ae_bet_order.allbet<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 总下注
     *
     * @mbggenerated
     */
    private BigDecimal allbet;

    /**
     * 字段: ae_bet_order.profit<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 输赢
     *
     * @mbggenerated
     */
    private BigDecimal profit;

    /**
     * 字段: ae_bet_order.revenue<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 抽水
     *
     * @mbggenerated
     */
    private BigDecimal revenue;

    /**
     * 字段: ae_bet_order.stime<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 游戏开始时间戳
     *
     * @mbggenerated
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT-6"
    )
    private Date stime;

    /**
     * 字段: ae_bet_order.etime<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 游戏结束时间戳
     *
     * @mbggenerated
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT-6"
    )
    private Date etime;

    /**
     * 字段: ae_bet_order.playernum<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 同局玩家人数
     *
     * @mbggenerated
     */
    private Integer playernum;

    /**
     * 字段: ae_bet_order.xiqian<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 洗钱(炸金花拿到豹子时奖励)
     *
     * @mbggenerated
     */
    private BigDecimal xiqian;

    /**
     * 字段: ae_bet_order.chair_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 玩家座位
     *
     * @mbggenerated
     */
    private Integer chairId;

    /**
     * 字段: ae_bet_order.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: ae_bet_order.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 字段: ae_bet_order.is_handle<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 打码量：0未处理，1已处理
     *
     * @mbggenerated
     */
    private Integer isHandle;

    /**
     * 字段: ae_bet_order.game_name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 游戏名称
     *
     * @mbggenerated
     */
    private String gameName;

    /**
     * 字段: ae_bet_order.room_name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 房间名称
     *
     * @mbggenerated
     */
    private String roomName;

    /**
     * 字段: ae_bet_order.user_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 用户ID
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 字段: ae_bet_order.result<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 65535<br/>
     * 说明: 游戏结果
     *
     * @mbggenerated
     */
    private String result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ae_bet_order
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return ae_bet_order.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: ae_bet_order.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return ae_bet_order.order_no: 注单号
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 字段: ae_bet_order.order_no<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 注单号
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return ae_bet_order.uname: 玩家账号
     *
     * @mbggenerated
     */
    public String getUname() {
        return uname;
    }

    /**
     * 字段: ae_bet_order.uname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 玩家账号
     *
     * @mbggenerated
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * @return ae_bet_order.game_id: 游戏ID
     *
     * @mbggenerated
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * 字段: ae_bet_order.game_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 游戏ID
     *
     * @mbggenerated
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    /**
     * @return ae_bet_order.battle_id: 游戏局号
     *
     * @mbggenerated
     */
    public String getBattleId() {
        return battleId;
    }

    /**
     * 字段: ae_bet_order.battle_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 游戏局号
     *
     * @mbggenerated
     */
    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }

    /**
     * @return ae_bet_order.room_id: 房间ID
     *
     * @mbggenerated
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * 字段: ae_bet_order.room_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 房间ID
     *
     * @mbggenerated
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * @return ae_bet_order.bet: 有效下注
     *
     * @mbggenerated
     */
    public BigDecimal getBet() {
        return bet;
    }

    /**
     * 字段: ae_bet_order.bet<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 有效下注
     *
     * @mbggenerated
     */
    public void setBet(BigDecimal bet) {
        this.bet = bet;
    }

    /**
     * @return ae_bet_order.allbet: 总下注
     *
     * @mbggenerated
     */
    public BigDecimal getAllbet() {
        return allbet;
    }

    /**
     * 字段: ae_bet_order.allbet<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 总下注
     *
     * @mbggenerated
     */
    public void setAllbet(BigDecimal allbet) {
        this.allbet = allbet;
    }

    /**
     * @return ae_bet_order.profit: 输赢
     *
     * @mbggenerated
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 字段: ae_bet_order.profit<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 输赢
     *
     * @mbggenerated
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * @return ae_bet_order.revenue: 抽水
     *
     * @mbggenerated
     */
    public BigDecimal getRevenue() {
        return revenue;
    }

    /**
     * 字段: ae_bet_order.revenue<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 抽水
     *
     * @mbggenerated
     */
    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    /**
     * @return ae_bet_order.stime: 游戏开始时间戳
     *
     * @mbggenerated
     */
    public Date getStime() {
        return stime;
    }

    /**
     * 字段: ae_bet_order.stime<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 游戏开始时间戳
     *
     * @mbggenerated
     */
    public void setStime(Date stime) {
        this.stime = stime;
    }

    /**
     * @return ae_bet_order.etime: 游戏结束时间戳
     *
     * @mbggenerated
     */
    public Date getEtime() {
        return etime;
    }

    /**
     * 字段: ae_bet_order.etime<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 游戏结束时间戳
     *
     * @mbggenerated
     */
    public void setEtime(Date etime) {
        this.etime = etime;
    }

    /**
     * @return ae_bet_order.playernum: 同局玩家人数
     *
     * @mbggenerated
     */
    public Integer getPlayernum() {
        return playernum;
    }

    /**
     * 字段: ae_bet_order.playernum<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 同局玩家人数
     *
     * @mbggenerated
     */
    public void setPlayernum(Integer playernum) {
        this.playernum = playernum;
    }

    /**
     * @return ae_bet_order.xiqian: 洗钱(炸金花拿到豹子时奖励)
     *
     * @mbggenerated
     */
    public BigDecimal getXiqian() {
        return xiqian;
    }

    /**
     * 字段: ae_bet_order.xiqian<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 洗钱(炸金花拿到豹子时奖励)
     *
     * @mbggenerated
     */
    public void setXiqian(BigDecimal xiqian) {
        this.xiqian = xiqian;
    }

    /**
     * @return ae_bet_order.chair_id: 玩家座位
     *
     * @mbggenerated
     */
    public Integer getChairId() {
        return chairId;
    }

    /**
     * 字段: ae_bet_order.chair_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 玩家座位
     *
     * @mbggenerated
     */
    public void setChairId(Integer chairId) {
        this.chairId = chairId;
    }

    /**
     * @return ae_bet_order.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: ae_bet_order.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return ae_bet_order.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: ae_bet_order.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return ae_bet_order.is_handle: 打码量：0未处理，1已处理
     *
     * @mbggenerated
     */
    public Integer getIsHandle() {
        return isHandle;
    }

    /**
     * 字段: ae_bet_order.is_handle<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 打码量：0未处理，1已处理
     *
     * @mbggenerated
     */
    public void setIsHandle(Integer isHandle) {
        this.isHandle = isHandle;
    }

    /**
     * @return ae_bet_order.game_name: 游戏名称
     *
     * @mbggenerated
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * 字段: ae_bet_order.game_name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 游戏名称
     *
     * @mbggenerated
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * @return ae_bet_order.room_name: 房间名称
     *
     * @mbggenerated
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * 字段: ae_bet_order.room_name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 房间名称
     *
     * @mbggenerated
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return ae_bet_order.user_id: 用户ID
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 字段: ae_bet_order.user_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 用户ID
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return ae_bet_order.result: 游戏结果
     *
     * @mbggenerated
     */
    public String getResult() {
        return result;
    }

    /**
     * 字段: ae_bet_order.result<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 65535<br/>
     * 说明: 游戏结果
     *
     * @mbggenerated
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_bet_order
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AeBetOrder other = (AeBetOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getUname() == null ? other.getUname() == null : this.getUname().equals(other.getUname()))
            && (this.getGameId() == null ? other.getGameId() == null : this.getGameId().equals(other.getGameId()))
            && (this.getBattleId() == null ? other.getBattleId() == null : this.getBattleId().equals(other.getBattleId()))
            && (this.getRoomId() == null ? other.getRoomId() == null : this.getRoomId().equals(other.getRoomId()))
            && (this.getBet() == null ? other.getBet() == null : this.getBet().equals(other.getBet()))
            && (this.getAllbet() == null ? other.getAllbet() == null : this.getAllbet().equals(other.getAllbet()))
            && (this.getProfit() == null ? other.getProfit() == null : this.getProfit().equals(other.getProfit()))
            && (this.getRevenue() == null ? other.getRevenue() == null : this.getRevenue().equals(other.getRevenue()))
            && (this.getStime() == null ? other.getStime() == null : this.getStime().equals(other.getStime()))
            && (this.getEtime() == null ? other.getEtime() == null : this.getEtime().equals(other.getEtime()))
            && (this.getPlayernum() == null ? other.getPlayernum() == null : this.getPlayernum().equals(other.getPlayernum()))
            && (this.getXiqian() == null ? other.getXiqian() == null : this.getXiqian().equals(other.getXiqian()))
            && (this.getChairId() == null ? other.getChairId() == null : this.getChairId().equals(other.getChairId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsHandle() == null ? other.getIsHandle() == null : this.getIsHandle().equals(other.getIsHandle()))
            && (this.getGameName() == null ? other.getGameName() == null : this.getGameName().equals(other.getGameName()))
            && (this.getRoomName() == null ? other.getRoomName() == null : this.getRoomName().equals(other.getRoomName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_bet_order
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getUname() == null) ? 0 : getUname().hashCode());
        result = prime * result + ((getGameId() == null) ? 0 : getGameId().hashCode());
        result = prime * result + ((getBattleId() == null) ? 0 : getBattleId().hashCode());
        result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
        result = prime * result + ((getBet() == null) ? 0 : getBet().hashCode());
        result = prime * result + ((getAllbet() == null) ? 0 : getAllbet().hashCode());
        result = prime * result + ((getProfit() == null) ? 0 : getProfit().hashCode());
        result = prime * result + ((getRevenue() == null) ? 0 : getRevenue().hashCode());
        result = prime * result + ((getStime() == null) ? 0 : getStime().hashCode());
        result = prime * result + ((getEtime() == null) ? 0 : getEtime().hashCode());
        result = prime * result + ((getPlayernum() == null) ? 0 : getPlayernum().hashCode());
        result = prime * result + ((getXiqian() == null) ? 0 : getXiqian().hashCode());
        result = prime * result + ((getChairId() == null) ? 0 : getChairId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsHandle() == null) ? 0 : getIsHandle().hashCode());
        result = prime * result + ((getGameName() == null) ? 0 : getGameName().hashCode());
        result = prime * result + ((getRoomName() == null) ? 0 : getRoomName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_bet_order
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", uname=").append(uname);
        sb.append(", gameId=").append(gameId);
        sb.append(", battleId=").append(battleId);
        sb.append(", roomId=").append(roomId);
        sb.append(", bet=").append(bet);
        sb.append(", allbet=").append(allbet);
        sb.append(", profit=").append(profit);
        sb.append(", revenue=").append(revenue);
        sb.append(", stime=").append(stime);
        sb.append(", etime=").append(etime);
        sb.append(", playernum=").append(playernum);
        sb.append(", xiqian=").append(xiqian);
        sb.append(", chairId=").append(chairId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isHandle=").append(isHandle);
        sb.append(", gameName=").append(gameName);
        sb.append(", roomName=").append(roomName);
        sb.append(", userId=").append(userId);
        sb.append(", result=").append(result);
        sb.append("]");
        return sb.toString();
    }
}