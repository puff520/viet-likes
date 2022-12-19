package com.likes.common.util;

import java.util.Date;
import java.util.TimeZone;

public class LanguageUtil {

    public static void setTimeZone(String language) {
//        if(language.contains("\"")){
//            language =language.replace("\"","");
//            language=language.trim();
//        }
//
//        String zoneId = "Asia/Shanghai";
//        if (language.equals("es")) {
//            zoneId = "Europe/London";
//        } else if (language.equals("sp")) {
//            zoneId = "Europe/Madrid";
//        } else if (language.equals("ab")) {
//            zoneId = "Asia/Riyadh";
//        } else if (language.equals("fr")) {
//            zoneId = " Europe/Paris";
//        }
//        final TimeZone timeZone = TimeZone.getTimeZone(zoneId);
//        TimeZone.setDefault(timeZone);
        System.out.println(DateUtils.formatDate(new Date()));
    }


}
