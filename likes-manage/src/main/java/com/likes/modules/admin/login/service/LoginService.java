/**
 *
 */
package com.likes.modules.admin.login.service;

import com.likes.common.service.BaseService;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;

/**
 * @author abu
 *
 */
public interface LoginService extends BaseService {

    /**
     * @param acclogin
     * @param password
     * @return
     */
    LoginUser doLogin(String acclogin, String password,String clintipadd,String serverIp);



    /**
     *
     * @author abu
     *
     *         Description:<br>
     *         修改密码
     *
     * @param newpsd
     * @param oldpsd
     * @param user
     * @throws BusinessException
     */
    void doModPassword(String newpsd, String oldpsd, LoginUser user) throws BusinessException;

}
