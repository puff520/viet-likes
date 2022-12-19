package com.likes.common.model.response;

import java.math.BigDecimal;

public class FamilyIncomeAndExpensesResponse {

	/*AA.accno,
    6ee5b243a9be0a21888298c2780b61f9aa8b6ced
	SUM(AA.shouruquantity) as allshouruquantity,
	SUM(AA.zhichuquantity) as allzhichuquantity,
	SUM(AA.shouruamount) as allshouruamount,
	SUM(AA.zhichuamount) as allzhichuamount*/

    private String accno;
    private BigDecimal allshouruquantity;
    private BigDecimal allzhichuquantity;
    private BigDecimal allshouruamount;
    private BigDecimal allzhichuamount;
    private String nickname;
    private String refaccno;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public BigDecimal getAllshouruquantity() {
        return allshouruquantity;
    }

    public void setAllshouruquantity(BigDecimal allshouruquantity) {
        this.allshouruquantity = allshouruquantity;
    }

    public BigDecimal getAllzhichuquantity() {
        return allzhichuquantity;
    }

    public void setAllzhichuquantity(BigDecimal allzhichuquantity) {
        this.allzhichuquantity = allzhichuquantity;
    }

    public BigDecimal getAllshouruamount() {
        return allshouruamount;
    }

    public void setAllshouruamount(BigDecimal allshouruamount) {
        this.allshouruamount = allshouruamount;
    }

    public BigDecimal getAllzhichuamount() {
        return allzhichuamount;
    }

    public void setAllzhichuamount(BigDecimal allzhichuamount) {
        this.allzhichuamount = allzhichuamount;
    }

}
