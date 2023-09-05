/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.service.assets;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.domain.respository.assets.IAsAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
* 资产报表领域层
*
* @author yangliu
* @date 2023-08-30
*/
@Service
public class AssetsReportFormsDomain {

    @Autowired
    private IAsAssetsRepository iAsAssetsRepository;

    /**
     * 资产总清单查询
     *
     * @param assetsSearchDo assetsSearchDo
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return null
     * @author yangliu
     * @date 2023/9/5
     */
    public PageInfo<AsAssetsDo> masterList(AsAssetsSearchDo assetsSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AsAssetsDo> asAssetsDos = iAsAssetsRepository.masterList(assetsSearchDo);
        if (CollectionUtils.isEmpty(asAssetsDos)) {
            return null;
        }
        return new PageInfo<>(asAssetsDos);
    }
}
