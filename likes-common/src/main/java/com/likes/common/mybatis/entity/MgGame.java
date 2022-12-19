package com.likes.common.mybatis.entity;

import java.io.Serializable;

public class MgGame implements Serializable {
    /**
     * 字段: mg_game.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 字段: mg_game.game_code<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 游戏编号
     *
     * @mbggenerated
     */
    private String gameCode;

    /**
     * 字段: mg_game.game_name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 游戏名称
     *
     * @mbggenerated
     */
    private String gameName;

    /**
     * 字段: mg_game.lottery_tag<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer lotteryTag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return mg_game.id: 
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 字段: mg_game.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return mg_game.game_code: 游戏编号
     *
     * @mbggenerated
     */
    public String getGameCode() {
        return gameCode;
    }

    /**
     * 字段: mg_game.game_code<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 游戏编号
     *
     * @mbggenerated
     */
    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    /**
     * @return mg_game.game_name: 游戏名称
     *
     * @mbggenerated
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * 字段: mg_game.game_name<br/>
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
     * @return mg_game.lottery_tag: 
     *
     * @mbggenerated
     */
    public Integer getLotteryTag() {
        return lotteryTag;
    }

    /**
     * 字段: mg_game.lottery_tag<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setLotteryTag(Integer lotteryTag) {
        this.lotteryTag = lotteryTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
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
        MgGame other = (MgGame) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGameCode() == null ? other.getGameCode() == null : this.getGameCode().equals(other.getGameCode()))
            && (this.getGameName() == null ? other.getGameName() == null : this.getGameName().equals(other.getGameName()))
            && (this.getLotteryTag() == null ? other.getLotteryTag() == null : this.getLotteryTag().equals(other.getLotteryTag()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGameCode() == null) ? 0 : getGameCode().hashCode());
        result = prime * result + ((getGameName() == null) ? 0 : getGameName().hashCode());
        result = prime * result + ((getLotteryTag() == null) ? 0 : getLotteryTag().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
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
        sb.append(", gameCode=").append(gameCode);
        sb.append(", gameName=").append(gameName);
        sb.append(", lotteryTag=").append(lotteryTag);
        sb.append("]");
        return sb.toString();
    }
}