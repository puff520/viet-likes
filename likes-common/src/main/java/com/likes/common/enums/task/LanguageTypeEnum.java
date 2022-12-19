package com.likes.common.enums.task;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum LanguageTypeEnum {

    ZH("zh", "简体中文"),
    EN("en", "英文"),
    VIE("vie", "越南语");
    private final String code;
    private final String name;

    LanguageTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public static List toList() {
        List list = Lists.newArrayList();
        for (LanguageTypeEnum languageTypeEnum : LanguageTypeEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", languageTypeEnum.getCode());
            map.put("name", languageTypeEnum.getName());
            list.add(map);
        }
        return list;
    }

}
