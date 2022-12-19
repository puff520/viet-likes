package com.likes.modules.admin.report.service;

import com.likes.common.model.response.DepositsReports;
import com.likes.common.model.response.ThirdProvider;

import java.util.List;

public interface DepositReportService {
    DepositsReports getDepositByKefu(String startTime, String endTime);

    DepositsReports getDepositByCompany(String startTime, String endTime);

    DepositsReports getDepositByThirdOnline(String startTime, String endTime);

    DepositsReports getDepositByPayFor(String startTime, String endTime);

    List<ThirdProvider> getDepositReportThird(String startTime, String endTime, String provider);
}
