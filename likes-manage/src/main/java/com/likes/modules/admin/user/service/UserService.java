package com.likes.modules.admin.user.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.*;
import com.likes.common.model.vo.member.UserDetailForUpdateVO;

public interface UserService {

    /**
     * 用户列表
     *
     * @return
     */
    PageResult userList(UserRequest req, Integer pageNo, Integer pageSize);

    PageResult memBankList(MemberBankRequest req, PageBounds page);

    //系统用户列表
    PageResult getSystemUserList(UserRequest req, Integer pageNo, Integer pageSize);

    /**
     * 禁用/开启
     *
     * @return
     */
    Integer doAccstatusUser(UserRequest req);

    /**
     * 系统用户禁用/开启
     *
     * @return
     */
    Integer doAccstatusSystemUser(UserRequest req);

    /**
     * 删除账号
     *
     * @return
     */
    String doDelUser(UserRequest req);

    /**
     * 删除系统账号
     *
     * @return
     */
    String doDelSystemUser(UserRequest req);

    /**
     * 用户详细
     *
     * @param req
     * @return
     */
    <T> T getUserDetail(UserRequest req);

    /**
     * @param request
     * @param page
     * @return
     */
    PageResult applyAnchorList(UserRequest request, PageBounds page);

//    /**
//     * 审核主播信息
//     *
//     * @param req
//     * @param loginAdmin
//     * @return
//     */
//    String reviewAnchor(MemCertification req, LoginUser loginAdmin, Long familyid);

    /**
     * 修改密码
     *
     * @param
     * @return
     */
    String updatePassword(String acclogin, String pwd, Integer type, LoginUser loginAdmin);

    /**
     * 更新备注
     */
    String updateRemark(Long memid, String remark, LoginUser loginAdmin);

    boolean modifyCredit(UserCreditRequest userCreditRequest, LoginUser loginAdmin);
    boolean singleModifyCredit(CreditChangeRequest creditRequest, LoginUser loginAdmin);

    PageResult creditList(UserCreditRequest userCreditRequest, LoginUser loginAdmin, Integer pageNo, Integer pageSize);

    PageResult memCreditList(CreditChangeRequest creditRequest, PageBounds pageBounds);

    /**
     * 编辑
     */
    String updateUser(UserUpdateRequest br, LoginUser loginAdmin);

//    /**
//     * 直播间列表
//     */
//    PageResult anchorList(UserRequest req, Integer pageNo, Integer pageSize);

    /**
     * 详情页
     */
    UserDetailForUpdateVO userDetailForUpdate(BankRequest br, LoginUser loginAdmin);

    /**
     * 删除头像
     */
    String delUserHeadimg(BankRequest br, LoginUser loginAdmin);

    /**
     * 修改代理跳转url
     */
    String updateProxyUrl(Long memid, String proxyUrl, LoginUser loginAdmin);


    void updateUserLevel(String accno, String memlevel, Boolean locked, LoginUser loginAdmin);

    void updateUserLevelNoLock(String accno, String memlevel, boolean b, LoginUser loginAdmin);
}
