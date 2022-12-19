package com.likes.common.service.qutoutiao;

import com.likes.common.model.LoginUser;
import com.likes.common.model.request.BuryingPointRequest;
import com.likes.common.model.request.EastToutiaoRequest;
import com.likes.common.model.request.QutoutiaoRequest;

public interface QutoutiaoService {

    boolean saveData(QutoutiaoRequest qutoutiaoRequest);

    boolean saveEastData(EastToutiaoRequest request);

    boolean saveBuryingPoint(BuryingPointRequest pointReques,  LoginUser loginUser );
}
