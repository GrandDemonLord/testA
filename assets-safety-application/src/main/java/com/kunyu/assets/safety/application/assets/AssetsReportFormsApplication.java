/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.application.assets;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.domain.service.assets.AssetsReportFormsDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 资产报表应用层
*
* @author yangliu
* @date 2023-08-30
*/
@Service
public class AssetsReportFormsApplication {

    @Autowired
    private AssetsReportFormsDomain assetsReportFormsDomain;

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
        return assetsReportFormsDomain.masterList(assetsSearchDo, pageNum, pageSize);
    }
}
