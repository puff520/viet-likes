package com.likes.common.util.file;

import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.WithdrawPick;
import com.likes.common.util.DateUtils;
import com.likes.common.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FileExportUtil {

    public static void fileExport(String content, HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileExport(content, null, request, response);
    }

    public static void fileExport(String content, String charset, HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileExport(null, content, charset, request, response);
    }

    public static void fileExport(String fileName, String content, String charset, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(content)) {
            throw new NullPointerException("要下载的文件内容为空");
        }
        fileName = StringUtils.isEmpty(fileName) ? com.likes.common.util.DateUtils.formatDate(new Date(), DateUtils.FORMAT_YYYYMMDD_HHMMSS_SSS) : fileName;
        fileName = fileName.concat(".txt");
        charset = StringUtils.isEmpty(charset) ? "UTF-8" : charset;

        String userAgent = request.getHeader("User-Agent");

        byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes(charset);
        fileName = new String(bytes, StandardCharsets.UTF_8); // 各浏览器基本都支持ISO编码
        response.setContentType("application/force-download");
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
        BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(content.getBytes(charset));
        outputStream.flush();
    }

//    /**
//     * 提现文件导出，内容build
//     *
//     * @param picks
//     * @param members
//     * @param supportBanks
//     * @return
//     */
//    public static String buildWithdrawExportContent(List<WithdrawPick> picks, List<MemBaseinfo> members, List<SupportBank> supportBanks, Integer bankType) {
//        if (CollectionUtil.isEmpty(picks) && CollectionUtil.isEmpty(members)) {
//            return null;
//        }
//        Map<Integer, MemBaseinfo> memberMap = new HashMap<>(members.size());
//        for (MemBaseinfo member : members) {
//            memberMap.put(member.getMemid().intValue(), member);
//        }
//
//        StringBuilder text = new StringBuilder(1000);
//        for (WithdrawPick pick : picks) {
//            text.append(buildWithdrawExportContent(pick, memberMap.get(pick.getMemberId()), supportBanks, bankType));
//        }
//        return text.toString();
//    }
//
//    public static String buildWithdrawExportContent(WithdrawPick pick, MemBaseinfo member, List<SupportBank> supportBanks, Integer bankType) {
//        if (WithdrawExportBankType.MIGSHENG.getValue().equals(bankType)) {
//            return buildMinShengWithdrawExportContent(pick, member, supportBanks);
//        } else {
//            return buildDefaultWithdrawExportContent(pick, member);
//        }
//    }
//
//
//    //构建民生银行内容
//    private static String buildMinShengWithdrawExportContent(WithdrawPick pick, MemBaseinfo member, List<SupportBank> supportBanks) {
//        Map<String, String> bankCodeMap = new HashMap<>();
//        for (SupportBank bank : supportBanks) {
//            bankCodeMap.put(bank.getName(), bank.getBankCode());
//        }
//
//        String bank = pick.getBank();
//        String split = ",";
//        String useWay = "105655051783";//用途
//        String huiLu = "12";//汇路
//        String lineSeparator = System.getProperty("line.separator");
//        String realName = null == member ? null : member.getMemname();
//        boolean isMinSheng = bank.contains("民生");
//
//        //格式：收款人账号，收款人账户类型，收款人账户名称，付款金额，用途，汇路，开户行号，收款人手机号
//        StringBuilder text = new StringBuilder(100);
//        text.append(pick.getCardNumber()).append(split);
//        text.append(isMinSheng ? "民生卡" : "它行").append(split);
//        text.append(realName).append(split);
//        text.append(pick.getMoney()).append(split);
//        text.append(useWay).append(split); //用途
//        text.append(huiLu).append(split); //汇路
//        String bankCode = bankCodeMap.get(CommonUtils.truncateBankName(pick.getBank()));
//        text.append(bankCode).append(split);
//        text.append(member.getMobileno());//此处的手机号不一定为真实的收款人手机号，临时取用户设置的手机号
//        text.append(lineSeparator);
//        return text.toString();
//    }

    //构建默认银行内容
    private static String buildDefaultWithdrawExportContent(WithdrawPick pick, MemBaseinfo member) {
        String bank = pick.getBank();
        String split = "|";
        String realName = null == member ? null : member.getMemname();
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder text = new StringBuilder(100);
        if (!(bank.contains("交通") || bank.contains("中国"))) {
            text.append("中国");
        }
        text.append(bank).append(split);
        text.append(pick.getCardNumber()).append(split);
        text.append(realName).append(split);
        text.append(pick.getMoney()).append(split);
        text.append(pick.getOrderCode()).append(split).append(lineSeparator);
        return text.toString();
    }
}
