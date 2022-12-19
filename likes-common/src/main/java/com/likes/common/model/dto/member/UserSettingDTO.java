package com.likes.common.model.dto.member;

/**
 * @ClassName: UserSettingDTO
 * @Description: 用户配置信息
 * @author: HANS
 * @date: 2019年7月29日 下午1:47:49
 */
public class UserSettingDTO {

    private Integer id;

    private String caipiaoType;

    private String dragonLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaipiaoType() {
        return caipiaoType;
    }

    public void setCaipiaoType(String caipiaoType) {
        this.caipiaoType = caipiaoType;
    }

    public String getDragonLimit() {
        return dragonLimit;
    }

    public void setDragonLimit(String dragonLimit) {
        this.dragonLimit = dragonLimit;
    }

}
