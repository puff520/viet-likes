package com.likes.common.model.dto.member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class MemFamilyReq {

	private Long familyid;

	@NotNull(message = "账号类型不为空")
	private Integer accounttype;

	private String bankname;
	private String bankaddress;

	@NotBlank(message = "持卡人姓名为空")
	@Size(min = 1, max = 20, message = "持卡人姓名长度1-20位")
	private String accountname;

	@NotBlank(message = "提现账号为空")
	@Size(min = 10, max = 40)
	private String accountno;

	@NotBlank(message = "身份证号为空")
	@Size(min = 1, max = 18, message = "身份证长度1-18位")
	private String idcardno;

	@NotBlank(message = "电话为空")
	@Size(min = 1, max = 15, message = "电话度1-15位")
	private String telephone;

	@NotBlank(message = "家族名称为空")
	@Size(min = 1, max = 30, message = "家族名称长度1-30位")
	private String familyname;

	@NotBlank(message = "家族长姓名为空")
	@Size(min = 1, max = 20, message = "家族长姓名长度1-20位")
	private String familyman;
	private BigDecimal royaltypercent;

	@NotBlank(message = "后台账户为空")
	@Size(min = 2, max = 20, message = "后台账户长度2-20位")
	private String acclogin;
	
	@NotBlank(message = "密码为空")
	@Size(min = 2, max = 32, message = "密码长度2-32位")
	private String passwordmd5;

	private BigDecimal bettingpercentage; //投注分成百分比

	public BigDecimal getBettingpercentage() {
		return bettingpercentage;
	}

	public void setBettingpercentage(BigDecimal bettingpercentage) {
		this.bettingpercentage = bettingpercentage;
	}



	public BigDecimal getRoyaltypercent() {
		return royaltypercent;
	}

	public void setRoyaltypercent(BigDecimal royaltypercent) {
		this.royaltypercent = royaltypercent;
	}

	public String getAcclogin() {
		return acclogin;
	}

	public void setAcclogin(String acclogin) {
		this.acclogin = acclogin;
	}

	public String getPasswordmd5() {
		return passwordmd5;
	}

	public void setPasswordmd5(String passwordmd5) {
		this.passwordmd5 = passwordmd5;
	}

	public String getBankaddress() {
		return bankaddress;
	}

	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}

	public Long getFamilyid() {
		return familyid;
	}

	public void setFamilyid(Long familyid) {
		this.familyid = familyid;
	}

	public Integer getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(Integer accounttype) {
		this.accounttype = accounttype;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public String getFamilyman() {
		return familyman;
	}

	public void setFamilyman(String familyman) {
		this.familyman = familyman;
	}

}
