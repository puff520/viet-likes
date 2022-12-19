package com.likes.common.model.request;

public class AgentAccountRequest {

    /**
     * 银行账户id
     */
    private Long bankid;

    /**
     * 收款名称
     */
    private String nickname;

    /**
     * 银行名称
     */
    private String bankname;

    /**
     * 银行账号
     */
    private String accountno;

    /**
     * 开户银行网点
     */
    private String bankaddress;

    /**
     * 收款人
     */
    private String accountname;

    /**
     * 状态	0-启用 9-禁用
     */
    private Integer status;

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
