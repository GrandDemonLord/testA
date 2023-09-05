/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.application.assets;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.domain.service.assets.AssetsDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 资产安全管理模块应用层
*
* @author yangliu
* @date 2023-08-25
*/
@Service
public class AssetsApplication {

    @Autowired
    private AssetsDomain assetsDomain;

    /**
     * 资产上架清单
     *
     * @param assetsSearchDo assetsSearchDo
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    public PageInfo<AsAssetsDo> listingList(AsAssetsSearchDo assetsSearchDo, int pageNum, int pageSize) {
        return assetsDomain.listingList(assetsSearchDo, pageNum, pageSize);
    }

    /**
     * 新增资产上架
     *
     * @param asAssetsDo asAssetsDo
     * @return ApiResponse asAssetsDo
     */
    public AsAssetsDo listing(AsAssetsDo asAssetsDo) {
        return assetsDomain.listing(asAssetsDo);
    }

    /**
     * 上架驳回的资产，重新申请上架
     *
     * @param asAssetsDo asAssetsDo
     * @return ApiResponse asAssetsDo
     * @author yangliu
     * @date 2023/9/5
     */
    public AsAssetsDo listingReapply(AsAssetsDo asAssetsDo) {
        return assetsDomain.listingReapply(asAssetsDo);
    }

    public List<AsAssetsDo> batchListing(List<AsAssetsDo> asAssetsDos, String userId) {
        return assetsDomain.batchListing(asAssetsDos, userId);
    }

    /**
     * 资产下架清单
     *
     * @param assetsSearchDo assetsSearchDo
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    public PageInfo<AsAssetsDo> delistList(AsAssetsSearchDo assetsSearchDo, int pageNum, int pageSize) {
        return assetsDomain.delistList(assetsSearchDo, pageNum, pageSize);
    }

    /**
     * 资产下架
     *
     * @param asAssetsDo asAssetsDo
     * @return Boolean Boolean
     */
    public boolean delist(AsAssetsDo asAssetsDo) {
        return assetsDomain.delist(asAssetsDo);
    }

    /**
     * 下架驳回的资产，重新申请下架
     *
     * @param asAssetsDo asAssetsDo
     * @return Boolean Boolean
     */
    public boolean delistReapply(AsAssetsDo asAssetsDo) {
        return assetsDomain.delistReapply(asAssetsDo);
    }

    /**
     * 待审批列表
     *
     * @param assetsSearchDo assetsSearchDto
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/8/29
     */
    public PageInfo<AsAssetsDo> pendingList(AsAssetsSearchDo assetsSearchDo, int pageNum, int pageSize) {
        return assetsDomain.pendingList(assetsSearchDo, pageNum, pageSize);
    }

    /**
     * 资产删除
     *
     * @param id id
     * @param userId userId
     * @return boolean
     * @author yangliu
     * @date 2023/8/30
     */
    public boolean deleteById(Integer id, String userId) {
        return assetsDomain.deleteById(id, userId);
    }

    /**
     * 审批通过
     *
     * @param id id
     * @param userId userId
     * @param userName userName
     * @return boolean
     * @author yangliu
     * @date 2023/8/30
     */
    public boolean approved(Integer id, String userId, String userName) {
        return assetsDomain.approved(id, userId, userName);
    }

    /**
     * 审批驳回
     *
     * @param id id
     * @param userId userId
     * @param userName userName
     * @return boolean
     * @author yangliu
     * @date 2023/8/30
     */
    public boolean rejection(Integer id, String userId, String userName) {
        return assetsDomain.rejection(id, userId, userName);
    }

    /**
     * 审批驳回列表查询接口
     *
     * @param assetsSearchDo assetsSearchDo
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/9/5
     */
    public PageInfo<AsAssetsDo> rejectionList(AsAssetsSearchDo assetsSearchDo, int pageNum, int pageSize) {
        return assetsDomain.rejectionList(assetsSearchDo, pageNum, pageSize);
    }
}
