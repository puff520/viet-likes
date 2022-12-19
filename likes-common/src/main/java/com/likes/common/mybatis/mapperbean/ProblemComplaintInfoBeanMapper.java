package com.likes.common.mybatis.mapperbean;


import com.likes.common.model.vo.sys.AdminOperaterVO;
import com.likes.common.model.vo.sys.MenuDataVO;
import com.likes.common.model.vo.sys.MenuPermissionVO;
import com.likes.common.model.vo.sys.ResourcesZtreeVO;
import com.likes.common.mybatis.mapperbean.provider.system.SystemDynaSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface ProblemComplaintInfoBeanMapper {

    /**
     * 根据角色id查询所有的权限
     * @param roleId
     * @return
     */
    @Select("select apr.permission from "
            + " (select resource_id from role_role_resource where role_id=#{roleId}) as roleIds "
            + " LEFT JOIN role_resource as apr ON roleIds.resource_id=apr.id where apr.deleted=0 ")
    List<String> selectPermissionsByRoleId(@Param("roleId") int roleId);

    /**
     * 根据角色id获取顶部菜单
     * @param roleId 角色id
     * @return
     */
    @Select("select r.id f_ModuleId, r.parent_id f_ParentId, r.name f_FullName, r.icon f_Icon, " +
            "r.url f_UrlAddress from role_role_resource as ar LEFT JOIN  role_resource r " +
            "ON ar.resource_id=r.id where ar.role_id=#{roleId} and r.`type`=1" +
            " and r.deleted=0 and r.parent_id=0 ORDER BY resource_order ASC")
    List<MenuDataVO> listTopMenu(@Param("roleId") int roleId);

    /**
     * 根据角色id获取左边菜单
     * @param roleId 角色id
     * @return
     */
    @Select("select r.id f_ModuleId, r.parent_id f_ParentId, r.name f_FullName, r.icon f_Icon, " +
            "r.url f_UrlAddress from role_role_resource as ar LEFT JOIN  role_resource r " +
            "ON ar.resource_id=r.id where ar.role_id=#{roleId} and r.`type`=1" +
            " and r.deleted=0 ORDER BY resource_order ASC")
    List<MenuDataVO> listLeftMenu(@Param("roleId") int roleId);

    /**
     * 菜单管理页面查询树菜单结构
     * @return List<ResourcesZtreeVO>
     */
    @Select("select r.id as id, r.name as name, r.parent_id as pId"
            + " from problem_complaint_resource r ORDER BY resource_order ASC ")
    List<ResourcesZtreeVO> queryMenusTree();

    /**
     * 根据菜单id分页查询对应的子菜单的集合
     * @param menuId
     * @param offset
     * @param pageSize
     * @return PageResult<List<MenuPermissionVO>>
     */
    @Select("select r.id id, r.`name` as `name`, r.parent_id parentId, r.resource_order resourceOrder, "
            + " r.deleted isDeleted from problem_complaint_resource r "
            + " where r.parent_id=#{menuId} order by r.resource_order ASC LIMIT #{offset},#{pageSize} ")
    List<MenuPermissionVO> queryMenuList(@Param("menuId") int menuId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 获取树状结构资源数据
     * @param roleId
     * @return
     */
    @Select("select r.id as id, r.name as name, r.parent_id as pId, "
            + " (select count(arr.id) from role_role_resource as arr "
            + " where arr.role_id=#{roleId} and arr.resource_id=r.id) as checked "
            + " from role_resource r where r.deleted=0  ORDER BY resource_order ASC ")
    List<ResourcesZtreeVO> queryResourcesZtree(@Param("roleId") int roleId);

    /**
     * 条件查询系统系统管理员人数
     * @param account 账号
     * @param status 状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @SelectProvider(type= SystemDynaSqlProvider.class,method="getOperaterCount")
    int queryMemberCountDyna(@Param("account") String account, @Param("status") int status, @Param("startTime") int startTime, @Param("endTime") int endTime);

    /**
     * 条件查询系统管理员列表
     * @author admin
     * @param account 帐号
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param offset 从第几条开始查
     * @param pageSize 查询的记录数
     * @date 2017年9月28日 下午5:59:25
     * @return String
     */
    @SelectProvider(type=SystemDynaSqlProvider.class,method="getOperaterList")
    List<AdminOperaterVO> queryMemberListDyna(@Param("account") String account, @Param("status") int status,
                                              @Param("startTime") int startTime, @Param("endTime") int endTime,
                                              @Param("offset") int offset, @Param("pageSize") int pageSize);
}
