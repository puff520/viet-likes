package com.likes.common.service.credit;


import com.likes.common.model.LoginUser;
import com.likes.common.model.request.CreditRequest;

public interface MemCreditService {

    boolean operation(CreditRequest request);

    boolean initMemberCredit(String memberNo);


    Integer selectCreditByMemNo(String  memNo);

}
