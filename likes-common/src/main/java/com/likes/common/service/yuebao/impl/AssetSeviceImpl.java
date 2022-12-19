package com.likes.common.service.yuebao.impl;


import com.likes.common.model.LoginUser;
import com.likes.common.model.req.AssetReq;
import com.likes.common.model.vo.AssetProductVO;
import com.likes.common.mybatis.mapper.MemAssetMapper;
import com.likes.common.service.yuebao.AssetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssetSeviceImpl implements AssetService {


    @Resource
    private MemAssetMapper memAssetMapper;


    @Override
    public List<AssetProductVO> list(AssetReq req, LoginUser loginUser) {
        List<AssetProductVO> list = memAssetMapper.assetList(loginUser.getAccno());
        list.forEach(item -> {
            if (req.getLanguage().equals("en")) {
                item.setName(item.getNameEn());
            } else if (req.getLanguage().equals("es")) {
                item.setName(item.getNameSp());
            } else if (req.getLanguage().equals("ar")) {
                item.setName(item.getNameAb());
            } else if (req.getLanguage().equals("fr")) {
                item.setName(item.getNameFn());
            }
        });
        return list;
    }
}
