/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.respository.assets;

import com.kunyu.assets.safety.domain.model.assets.AsAssetsApproveBuilder;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;

import java.util.List;

/**
* 资产数据仓储接口
*
* @author yangliu
* @date 2023-08-25
*/
public interface IAsAssetsRepository {
    /**
     * 资产上架清单
     *
     * @param assetsSearchDo assetsSearchDo
     * @return List AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    List<AsAssetsDo> listingList(AsAssetsSearchDo assetsSearchDo);

    /**
     * 新增资产上架
     *
     * @param asAssetsDo asAssetsDo
     * @return ApiResponse asAssetsDo
     */
    AsAssetsDo listing(AsAssetsDo asAssetsDo);

    /**
     * 上架驳回的资产，重新申请上架
     *
     * @param asAssetsDo asAssetsDo
     * @return ApiResponse asAssetsDo
     * @author yangliu
     * @date 2023/9/5
     */
    AsAssetsDo listingReapply(AsAssetsDo asAssetsDo);

    List<AsAssetsDo> batchListing(List<AsAssetsDo> asAssetsDos);

    /**
     * 资产下架清单
     *
     * @param assetsSearchDo assetsSearchDo
     * @return List AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    List<AsAssetsDo> delistList(AsAssetsSearchDo assetsSearchDo);

    /**
     * 资产下架
     *
     * @param asAssetsDo asAssetsDo
     * @return ApiResponse asAssetsDo
     */
    boolean delist(AsAssetsDo asAssetsDo);

    /**
     * 下架驳回的资产，重新申请下架
     *
     * @param asAssetsDo asAssetsDo
     * @return Boolean Boolean
     */
    boolean delistReapply(AsAssetsDo asAssetsDo);

    /**
     * 通过资产id查询资产明细
     *
     * @param id id
     * @return AsAssetsDo
     * @author yangliu
     * @date 2023/8/28
     */
    AsAssetsDo getAsAssetsById(Integer id);

    /**
     * 待审批列表
     *
     * @param assetsSearchDo assetsSearchDto
     * @return List AsAssetsDo
     * @author yangliu
     * @date 2023/8/29
     */
    List<AsAssetsDo> pendingList(AsAssetsSearchDo assetsSearchDo);

    /**
     * 资产删除
     *
     * @param id id
     * @param status status
     * @param userId userId
     * @return boolean
     * @author yangliu
     * @date 2023/8/30
     */
    boolean deleteById(Integer id, int status, String userId);

    /**
     * 审批通过
     *
     * @param asAssetsApproveBuilder asAssetsApproveBuilder
     * @return boolean
     * @author yangliu
     * @date 2023/8/30
     */
    boolean approved(AsAssetsApproveBuilder asAssetsApproveBuilder);

    /**
     * 审批驳回
     *
     * @param asAssetsApproveBuilder asAssetsApproveBuilder
     * @return boolean
     * @author yangliu
     * @date 2023/8/30
     */
    boolean rejection(AsAssetsApproveBuilder asAssetsApproveBuilder);

    /**
     * 资产总清单查询
     *
     * @param assetsSearchDo assetsSearchDo
     * @return null
     * @author yangliu
     * @date 2023/9/5
     */
    List<AsAssetsDo> masterList(AsAssetsSearchDo assetsSearchDo);

    /**
     * 审批驳回列表查询接口
     *
     * @param assetsSearchDo assetsSearchDo
     * @return List AsAssetsDo
     * @author yangliu
     * @date 2023/9/5
     */
    List<AsAssetsDo> rejectionList(AsAssetsSearchDo assetsSearchDo);
}
