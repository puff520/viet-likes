package com.likes.common.model.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class MemGoldChangeDTON {
    //金币变动明细id
    private long goldchangid;
    // 相关id 如充值订单id 、 打赏id 、彩票派獎id(ksorderid)  代理結算id
    private long refid;
    // 乐币来源主播accno
    private String refaccno;
    // 会员标识号
    private String accno;
    private String email;
    // 会员名
    private String memname;
    private  String memmobileno;


    //变动类型 1充值 2打赏 3赠送 4簽到獎勵 5發帖獎勵 6 發視頻獎勵 7 邀請用戶 8充值附赠 9 主播分成 10 提現申請 11提現取消 12已提現 13彩票派獎 14彩票下注 15彩票下注取消 16稽核手续费 17稽核手续费取消
    //18 棋牌轉出 19棋牌轉入 20代理結算
    private BigDecimal changetype;
    //充值/提現前金币数
    private BigDecimal goldnum;
    //充值/提現金币数
    private BigDecimal quantity;
    //金额
    private BigDecimal amount;
    //充值/提現后金币数
    private BigDecimal recgoldnum;
    //操作说明
    private String opnote;
    //创建人
    private String createuser;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date createtime;
    //最后修改人
    private String updateuser;
private  String bdusername;

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }
    public String getMemmobileno() {
        return memmobileno;
    }

    public void setMemmobileno(String memmobileno) {
        this.memmobileno = memmobileno;
    }

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date updatetime;
    // 雪花排序号
    private Long snowsn;
    // 来源：Android | IOS | WEB
    private String source;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getGoldchangid() {
        return goldchangid;
    }

    public void setGoldchangid(long goldchangid) {
        this.goldchangid = goldchangid;
    }

    public long getRefid() {
        return refid;
    }

    public void setRefid(long refid) {
        this.refid = refid;
    }

    public String getRefaccno() {
        return refaccno;
    }

    public void setRefaccno(String refaccno) {
        this.refaccno = refaccno;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getMemname() {
        return memname;
    }

    public void setMemname(String memName) {
        this.memname = memName;
    }

    public BigDecimal getChangetype() {
        return changetype;
    }

    public void setChangetype(BigDecimal changetype) {
        this.changetype = changetype;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRecgoldnum() {
        return recgoldnum;
    }

    public void setRecgoldnum(BigDecimal recgoldnum) {
        this.recgoldnum = recgoldnum;
    }

    public String getOpnote() {
        return opnote;
    }

    public void setOpnote(String opnote) {
        this.opnote = opnote;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getSnowsn() {
        return snowsn;
    }

    public void setSnowsn(Long snowsn) {
        this.snowsn = snowsn;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
