package com.likes.common.mybatis.mapperbean.provider.system;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 系统模块动态sql
 * @author admin
 * @date 2017年9月28日 下午5:07:45
 *
 */
public class SystemDynaSqlProvider {

    /**
     * @Author: admin
     * @Description:动态生成查询系统管理员列表sql
     * @Version: 1.0.0
     * @Date; 2017年9月28日 下午5:59:25
     * @param: [map]
     * @return: java.lang.String
     */
    public String getOperaterList(final Map<String, Object> map){

        String account = (String) map.get("account");
        int status = (Integer) map.get("status");
        int startTime = (Integer) map.get("startTime");
        int endTime = (Integer) map.get("endTime");
        
        return new SQL(){
            {
                SELECT(" m.id id, m.account account,m.role_id roleId, "
                        + " r.`name` role, m.status status, m.last_login_ip lastLoginIp, m.last_login_time lastLoginTime, m.create_time createTime ");
                FROM(" operater m ");
                LEFT_OUTER_JOIN(" operater_role r ON m.role_id=r.id");
                WHERE("m.deleted = 0");
                if(account != null && !account.isEmpty()){
                    WHERE(" m.account like #{account} ");
                }
                if(status >= 0){
                    WHERE(" m.status = #{status} ");
                }
                if(startTime > 0){
                    WHERE(" m.last_login_time >= #{startTime} ");
                }
                if(endTime > 0){
                    WHERE(" m.last_login_time < #{endTime} ");
                }
                ORDER_BY(" m.create_time DESC LIMIT #{offset},#{pageSize} ");
                
            }
        }.toString();
        
    }
    
    /**
     * 动态生成查询系统管理员列表sql（查询总数量）
     * @author admin
     * @param map
     * @date 2017年9月29日 上午10:13:07
     * @return String
     */
    public String getOperaterCount(final Map<String, Object> map){
        
        String account = (String) map.get("account");
        int status = (Integer) map.get("status");
        int startTime = (Integer) map.get("startTime");
        int endTime = (Integer) map.get("endTime");
        
        return new SQL(){
            {
                SELECT(" count(*) ");
                FROM(" operater m ");
                WHERE("m.deleted = 0");
                if(account != null && !account.isEmpty()){
                    WHERE(" m.account like #{account} ");
                }
                if(status >= 0){
                    WHERE(" m.status = #{status} ");
                }
                if(startTime > 0){
                    WHERE(" m.last_login_time >= #{startTime} ");
                }
                if(endTime > 0){
                    WHERE(" m.last_login_time < #{endTime} ");
                }
                ORDER_BY(" m.create_time DESC ");
            }
        }.toString();
        
    }

}
