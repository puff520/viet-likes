package com.likes.common.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class MemberBankRequest {

    private String moneyAddress;
    private String email;
    private String accountname;
    private String accountno;
    private String beginTime;
    private String endTime;
}
