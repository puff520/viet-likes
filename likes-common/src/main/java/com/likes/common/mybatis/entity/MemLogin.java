package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class MemLogin implements Serializable {
    /**
     * 字段: mem_login.loginid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 登录账号id
     *
     * @mbggenerated
     */
    private Long loginid;

    /**
     * 字段: mem_login.accno<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 会员标识号
     *
     * @mbggenerated
     */
    private String accno;

    /**
     * 字段: mem_login.acclogin<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 会员账号(登录用)
     *
     * @mbggenerated
     */
    private String acclogin;

    /**
     * 字段: mem_login.logintype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * 说明: 账户类型  普通会员1      主播2   家族长3   运营后台管理员8    第三方登录7   服务注册中心管理员9  聚合站点后台管理员10
     *
     * @mbggenerated
     */
    private Integer logintype;

    /**
     * 字段: mem_login.password<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 登陆密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 字段: mem_login.passwordmd5<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 登录密码MD5
     *
     * @mbggenerated
     */
    private String passwordmd5;

    /**
     * 字段: mem_login.paypassword<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 支付密码md5
     *
     * @mbggenerated
     */
    private String paypassword;

    /**
     * 字段: mem_login.accstatus<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 账号状态 1正常 9禁止登陆 
     *
     * @mbggenerated
     */
    private Integer accstatus;

    /**
     * 字段: mem_login.lastlogindate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 最后登录时间
     *
     * @mbggenerated
     */
    private Date lastlogindate;

    /**
     * 字段: mem_login.loginnum<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 登录总次数
     *
     * @mbggenerated
     */
    private Integer loginnum;

    /**
     * 字段: mem_login.clintipadd<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 登录ip地址
     *
     * @mbggenerated
     */
    private String clintipadd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mem_login
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return mem_login.loginid: 登录账号id
     *
     * @mbggenerated
     */
    public Long getLoginid() {
        return loginid;
    }

    /**
     * 字段: mem_login.loginid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 登录账号id
     *
     * @mbggenerated
     */
    public void setLoginid(Long loginid) {
        this.loginid = loginid;
    }

    /**
     * @return mem_login.accno: 会员标识号
     *
     * @mbggenerated
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 字段: mem_login.accno<br/>
     * 必填: true<br/>
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
     * @return mem_login.acclogin: 会员账号(登录用)
     *
     * @mbggenerated
     */
    public String getAcclogin() {
        return acclogin;
    }

    /**
     * 字段: mem_login.acclogin<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 会员账号(登录用)
     *
     * @mbggenerated
     */
    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    /**
     * @return mem_login.logintype: 账户类型  普通会员1      主播2   家族长3   运营后台管理员8    第三方登录7   服务注册中心管理员9  聚合站点后台管理员10
     *
     * @mbggenerated
     */
    public Integer getLogintype() {
        return logintype;
    }

    /**
     * 字段: mem_login.logintype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * 说明: 账户类型  普通会员1      主播2   家族长3   运营后台管理员8    第三方登录7   服务注册中心管理员9  聚合站点后台管理员10
     *
     * @mbggenerated
     */
    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }

    /**
     * @return mem_login.password: 登陆密码
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * 字段: mem_login.password<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 登陆密码
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return mem_login.passwordmd5: 登录密码MD5
     *
     * @mbggenerated
     */
    public String getPasswordmd5() {
        return passwordmd5;
    }

    /**
     * 字段: mem_login.passwordmd5<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 登录密码MD5
     *
     * @mbggenerated
     */
    public void setPasswordmd5(String passwordmd5) {
        this.passwordmd5 = passwordmd5;
    }

    /**
     * @return mem_login.paypassword: 支付密码md5
     *
     * @mbggenerated
     */
    public String getPaypassword() {
        return paypassword;
    }

    /**
     * 字段: mem_login.paypassword<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 支付密码md5
     *
     * @mbggenerated
     */
    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }

    /**
     * @return mem_login.accstatus: 账号状态 1正常 9禁止登陆 
     *
     * @mbggenerated
     */
    public Integer getAccstatus() {
        return accstatus;
    }

    /**
     * 字段: mem_login.accstatus<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 账号状态 1正常 9禁止登陆 
     *
     * @mbggenerated
     */
    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    /**
     * @return mem_login.lastlogindate: 最后登录时间
     *
     * @mbggenerated
     */
    public Date getLastlogindate() {
        return lastlogindate;
    }

    /**
     * 字段: mem_login.lastlogindate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 最后登录时间
     *
     * @mbggenerated
     */
    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    /**
     * @return mem_login.loginnum: 登录总次数
     *
     * @mbggenerated
     */
    public Integer getLoginnum() {
        return loginnum;
    }

    /**
     * 字段: mem_login.loginnum<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 登录总次数
     *
     * @mbggenerated
     */
    public void setLoginnum(Integer loginnum) {
        this.loginnum = loginnum;
    }

    /**
     * @return mem_login.clintipadd: 登录ip地址
     *
     * @mbggenerated
     */
    public String getClintipadd() {
        return clintipadd;
    }

    /**
     * 字段: mem_login.clintipadd<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 登录ip地址
     *
     * @mbggenerated
     */
    public void setClintipadd(String clintipadd) {
        this.clintipadd = clintipadd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_login
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
        MemLogin other = (MemLogin) that;
        return (this.getLoginid() == null ? other.getLoginid() == null : this.getLoginid().equals(other.getLoginid()))
            && (this.getAccno() == null ? other.getAccno() == null : this.getAccno().equals(other.getAccno()))
            && (this.getAcclogin() == null ? other.getAcclogin() == null : this.getAcclogin().equals(other.getAcclogin()))
            && (this.getLogintype() == null ? other.getLogintype() == null : this.getLogintype().equals(other.getLogintype()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getPasswordmd5() == null ? other.getPasswordmd5() == null : this.getPasswordmd5().equals(other.getPasswordmd5()))
            && (this.getPaypassword() == null ? other.getPaypassword() == null : this.getPaypassword().equals(other.getPaypassword()))
            && (this.getAccstatus() == null ? other.getAccstatus() == null : this.getAccstatus().equals(other.getAccstatus()))
            && (this.getLastlogindate() == null ? other.getLastlogindate() == null : this.getLastlogindate().equals(other.getLastlogindate()))
            && (this.getLoginnum() == null ? other.getLoginnum() == null : this.getLoginnum().equals(other.getLoginnum()))
            && (this.getClintipadd() == null ? other.getClintipadd() == null : this.getClintipadd().equals(other.getClintipadd()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_login
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLoginid() == null) ? 0 : getLoginid().hashCode());
        result = prime * result + ((getAccno() == null) ? 0 : getAccno().hashCode());
        result = prime * result + ((getAcclogin() == null) ? 0 : getAcclogin().hashCode());
        result = prime * result + ((getLogintype() == null) ? 0 : getLogintype().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getPasswordmd5() == null) ? 0 : getPasswordmd5().hashCode());
        result = prime * result + ((getPaypassword() == null) ? 0 : getPaypassword().hashCode());
        result = prime * result + ((getAccstatus() == null) ? 0 : getAccstatus().hashCode());
        result = prime * result + ((getLastlogindate() == null) ? 0 : getLastlogindate().hashCode());
        result = prime * result + ((getLoginnum() == null) ? 0 : getLoginnum().hashCode());
        result = prime * result + ((getClintipadd() == null) ? 0 : getClintipadd().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_login
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", loginid=").append(loginid);
        sb.append(", accno=").append(accno);
        sb.append(", acclogin=").append(acclogin);
        sb.append(", logintype=").append(logintype);
        sb.append(", password=").append(password);
        sb.append(", passwordmd5=").append(passwordmd5);
        sb.append(", paypassword=").append(paypassword);
        sb.append(", accstatus=").append(accstatus);
        sb.append(", lastlogindate=").append(lastlogindate);
        sb.append(", loginnum=").append(loginnum);
        sb.append(", clintipadd=").append(clintipadd);
        sb.append("]");
        return sb.toString();
    }
}