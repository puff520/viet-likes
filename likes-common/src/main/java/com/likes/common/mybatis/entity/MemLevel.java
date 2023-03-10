package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemLevel extends MemLevelKey implements Serializable {
    /**
     * 字段: mem_level.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 会员标识号
     *
     * @mbggenerated
     */
    private String accno;

    /**
     * 字段: mem_level.memlevel<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 当前等级
     *
     * @mbggenerated
     */
    private String memlevel;

    /**
     * 字段: mem_level.memscore<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 会员当前积分
     *
     * @mbggenerated
     */
    private BigDecimal memscore;

    /**
     * 字段: mem_level.nextlevscore<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 距离下一级所需积分
     *
     * @mbggenerated
     */
    private BigDecimal nextlevscore;

    /**
     * 字段: mem_level.levellog<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 500<br/>
     * 说明: 等级log
     *
     * @mbggenerated
     */
    private String levellog;

    /**
     * 字段: mem_level.locked<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 0:未锁定,1:锁定
     *
     * @mbggenerated
     */
    private Boolean locked;

    /**
     * 字段: mem_level.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: mem_level.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: mem_level.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: mem_level.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 字段: mem_level.expire_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 过期时间 如 2020年9月10日17:46:55
     *
     * @mbggenerated
     */
    private Date expireTime;

    /**
     * 字段: mem_level.level_config_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 等级配置表ID
     *
     * @mbggenerated
     */
    private Long levelConfigId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mem_level
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return mem_level.accno: 会员标识号
     *
     * @mbggenerated
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 字段: mem_level.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 会员标识号
     *
     * @mbggenerated
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return mem_level.memlevel: 当前等级
     *
     * @mbggenerated
     */
    public String getMemlevel() {
        return memlevel;
    }

    /**
     * 字段: mem_level.memlevel<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 当前等级
     *
     * @mbggenerated
     */
    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    /**
     * @return mem_level.memscore: 会员当前积分
     *
     * @mbggenerated
     */
    public BigDecimal getMemscore() {
        return memscore;
    }

    /**
     * 字段: mem_level.memscore<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 会员当前积分
     *
     * @mbggenerated
     */
    public void setMemscore(BigDecimal memscore) {
        this.memscore = memscore;
    }

    /**
     * @return mem_level.nextlevscore: 距离下一级所需积分
     *
     * @mbggenerated
     */
    public BigDecimal getNextlevscore() {
        return nextlevscore;
    }

    /**
     * 字段: mem_level.nextlevscore<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 距离下一级所需积分
     *
     * @mbggenerated
     */
    public void setNextlevscore(BigDecimal nextlevscore) {
        this.nextlevscore = nextlevscore;
    }

    /**
     * @return mem_level.levellog: 等级log
     *
     * @mbggenerated
     */
    public String getLevellog() {
        return levellog;
    }

    /**
     * 字段: mem_level.levellog<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 500<br/>
     * 说明: 等级log
     *
     * @mbggenerated
     */
    public void setLevellog(String levellog) {
        this.levellog = levellog;
    }

    /**
     * @return mem_level.locked: 0:未锁定,1:锁定
     *
     * @mbggenerated
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * 字段: mem_level.locked<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 0:未锁定,1:锁定
     *
     * @mbggenerated
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * @return mem_level.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: mem_level.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return mem_level.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: mem_level.create_time<br/>
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
     * @return mem_level.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: mem_level.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return mem_level.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: mem_level.update_time<br/>
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
     * @return mem_level.expire_time: 过期时间 如 2020年9月10日17:46:55
     *
     * @mbggenerated
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 字段: mem_level.expire_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 过期时间 如 2020年9月10日17:46:55
     *
     * @mbggenerated
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * @return mem_level.level_config_id: 等级配置表ID
     *
     * @mbggenerated
     */
    public Long getLevelConfigId() {
        return levelConfigId;
    }

    /**
     * 字段: mem_level.level_config_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 等级配置表ID
     *
     * @mbggenerated
     */
    public void setLevelConfigId(Long levelConfigId) {
        this.levelConfigId = levelConfigId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_level
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
        MemLevel other = (MemLevel) that;
        return (this.getLevelid() == null ? other.getLevelid() == null : this.getLevelid().equals(other.getLevelid()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getAccno() == null ? other.getAccno() == null : this.getAccno().equals(other.getAccno()))
            && (this.getMemlevel() == null ? other.getMemlevel() == null : this.getMemlevel().equals(other.getMemlevel()))
            && (this.getMemscore() == null ? other.getMemscore() == null : this.getMemscore().equals(other.getMemscore()))
            && (this.getNextlevscore() == null ? other.getNextlevscore() == null : this.getNextlevscore().equals(other.getNextlevscore()))
            && (this.getLevellog() == null ? other.getLevellog() == null : this.getLevellog().equals(other.getLevellog()))
            && (this.getLocked() == null ? other.getLocked() == null : this.getLocked().equals(other.getLocked()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getLevelConfigId() == null ? other.getLevelConfigId() == null : this.getLevelConfigId().equals(other.getLevelConfigId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_level
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLevelid() == null) ? 0 : getLevelid().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getAccno() == null) ? 0 : getAccno().hashCode());
        result = prime * result + ((getMemlevel() == null) ? 0 : getMemlevel().hashCode());
        result = prime * result + ((getMemscore() == null) ? 0 : getMemscore().hashCode());
        result = prime * result + ((getNextlevscore() == null) ? 0 : getNextlevscore().hashCode());
        result = prime * result + ((getLevellog() == null) ? 0 : getLevellog().hashCode());
        result = prime * result + ((getLocked() == null) ? 0 : getLocked().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getLevelConfigId() == null) ? 0 : getLevelConfigId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_level
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accno=").append(accno);
        sb.append(", memlevel=").append(memlevel);
        sb.append(", memscore=").append(memscore);
        sb.append(", nextlevscore=").append(nextlevscore);
        sb.append(", levellog=").append(levellog);
        sb.append(", locked=").append(locked);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", levelConfigId=").append(levelConfigId);
        sb.append("]");
        return sb.toString();
    }
}