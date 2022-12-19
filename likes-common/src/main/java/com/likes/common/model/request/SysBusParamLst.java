package com.likes.common.model.request;

import java.util.Map;

public class SysBusParamLst {
    public Map<String, String> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, String> configs) {
        this.configs = configs;
    }

    private Map<String,String> configs;
}
