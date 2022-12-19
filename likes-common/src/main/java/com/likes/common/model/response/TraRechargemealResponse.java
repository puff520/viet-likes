package com.likes.common.model.response;

import java.math.BigDecimal;

public class TraRechargemealResponse {
    private Long mealid;
    private BigDecimal realamt;
    private Long rechargegold;
    private Long givegold;
    private BigDecimal givepercent;
    //private String message;

    public Long getMealid() {
        return mealid;
    }

    public void setMealid(Long mealid) {
        this.mealid = mealid;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    public Long getRechargegold() {
        return rechargegold;
    }

    public void setRechargegold(Long rechargegold) {
        this.rechargegold = rechargegold;
    }

    public Long getGivegold() {
        return givegold;
    }

    public void setGivegold(Long givegold) {
        this.givegold = givegold;
    }

    public BigDecimal getGivepercent() {
        return givepercent;
    }

    public void setGivepercent(BigDecimal givepercent) {
        this.givepercent = givepercent;
    }

	/*public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}*/

}
