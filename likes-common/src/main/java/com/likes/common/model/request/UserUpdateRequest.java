package com.likes.common.model.request;

import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import org.apache.commons.lang.StringUtils;

public class UserUpdateRequest {

    private String acclogin;

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    private String resetPwd;

    public String getResetPwd() {
        return resetPwd;
    }

    public void setResetPwd(String resetPwd) {
        this.resetPwd = resetPwd;
    }

    /**
     * 账号id
     */
    private Long memid;

    /**
     * 银行/支付宝/微信开户人姓名
     */
    private String accountname;

    /**
     * 银行名称标识符 如ICBC
     */
    private String bankname;

    /**
     * 开户行 如 某某支行
     */
    private String bankaddress;

    /**
     * 卡号
     */
    private String accountno;

    /**
     * 昵称--用户表
     */
    private String nickname;

    /**
     * 个人签名
     */
    private String describes;

    /**
     * 用户等级
     */
    private String memlevel;

    /**
     * 用户等级锁定
     */
    private Boolean locked;

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    /**
     * 参数检查
     */
    public void updateUserCheck(LoginUser loginAdmin) {
        if (loginAdmin == null || StringUtils.isEmpty(loginAdmin.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
        }
        if (null == this.getMemid()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_161.getCode(), "用户id为空");
        }
//        if (StringUtils.isBlank(this.getBankaddress())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "支行地址为空");
//        }
//        if (StringUtils.isBlank(this.getBankname())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "银行名称为空");
//        }
//        if (StringUtils.isBlank(this.getAccountno())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "卡号为空");
//        }
//        if (StringUtils.isBlank(this.getAccountname())) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "真实姓名为空");
//        }
        if (StringUtils.isBlank(this.getNickname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_143.getCode(), "昵称为空");
        }
        if (StringUtils.isBlank(this.getMemlevel())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_162.getCode(), "等级为空");
        }
        if (null == this.getLocked()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_163.getCode(), "等级锁为空");
        }
    }
}
