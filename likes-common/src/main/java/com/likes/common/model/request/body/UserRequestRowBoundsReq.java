package com.likes.common.model.request.body;

import com.likes.common.model.request.UserRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.response.pay.BasePayReq;
import org.apache.ibatis.session.RowBounds;

/**
 * @ClassName
 * @Description UserRequestRowBoundsReq
 * @Version 1.0
 **/
public class UserRequestRowBoundsReq {

    private UserRequest userRequest;
    private UsersRequest usersRequest;
    private RowBounds rowBounds;

    public UserRequestRowBoundsReq() {
    }

    public UserRequestRowBoundsReq(UserRequest userRequest, UsersRequest usersRequest, RowBounds rowBounds) {
        this.setUserRequest(userRequest);
        this.setUsersRequest(usersRequest);
        this.setRowBounds(rowBounds);
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public RowBounds getRowBounds() {
        return rowBounds;
    }

    public void setRowBounds(RowBounds rowBounds) {
        this.rowBounds = rowBounds;
    }

    public UsersRequest getUsersRequest() {
        return usersRequest;
    }

    public void setUsersRequest(UsersRequest usersRequest) {
        this.usersRequest = usersRequest;
    }
}
