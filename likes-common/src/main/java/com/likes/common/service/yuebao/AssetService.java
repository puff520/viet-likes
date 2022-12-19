package com.likes.common.service.yuebao;


import com.likes.common.model.LoginUser;
import com.likes.common.model.req.AssetReq;
import com.likes.common.model.vo.AssetProductVO;

import java.util.List;

public interface AssetService {


    List<AssetProductVO> list(AssetReq req, LoginUser loginUser);


}
