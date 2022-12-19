package com.likes.common.model.dto.member;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class MemFamilymemReq {

	private Long familymemid;
	
	@NotNull(message = "家族id不能为空") 
	private Long familyid;
	
	@NotEmpty(message = "主播不能为空")
	private List<String> accnolist;
	
	//@NotNull(message = "提成比例不能为空")
	//@DecimalMax(value = "1")
	//@DecimalMin(value = "0")
	private BigDecimal royaltypercent;

	public Long getFamilymemid() {
		return familymemid;
	}

	public void setFamilymemid(Long familymemid) {
		this.familymemid = familymemid;
	}

	public List<String> getAccnolist() {
		return accnolist;
	}

	public void setAccnolist(List<String> accnolist) {
		this.accnolist = accnolist;
	}

	public BigDecimal getRoyaltypercent() {
		return royaltypercent;
	}

	public void setRoyaltypercent(BigDecimal royaltypercent) {
		this.royaltypercent = royaltypercent;
	}

	public Long getFamilyid() {
		return familyid;
	}

	public void setFamilyid(Long familyid) {
		this.familyid = familyid;
	}

	
	
}
