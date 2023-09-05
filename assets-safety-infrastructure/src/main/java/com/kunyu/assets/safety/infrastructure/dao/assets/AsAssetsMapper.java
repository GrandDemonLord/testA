/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.dao.assets;

import com.kunyu.assets.safety.domain.model.assets.AsAssetsApproveBuilder;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.infrastructure.po.assets.AsAssetsPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资产数据持久层接口
 *
 * @author yangliu
 * @date 2023-08-25
 */
public interface AsAssetsMapper {

    /**
     * 资产上架清单
     *
     * @param assetsSearchDo assetsSearchDo
     * @return List AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    List<AsAssetsPo> listingList(AsAssetsSearchDo assetsSearchDo);

    /**
     * 新增资产上架
     *
     * @param asAssetsPo asAssetsPo
     * @return int int
     * @author yangliu
     * @date 2023/8/25
     */
    int saveListing(AsAssetsPo asAssetsPo);

    /**
     * 上架驳回的资产，重新申请上架
     *
     * @param asAssetsPo asAssetsPo
     * @return int int
     * @author yangliu
     * @date 2023/9/5
     */
    int saveListingReapply(AsAssetsPo asAssetsPo);

    int batchListing(List<AsAssetsPo> asAssetsPos);

    /**
     * 资产下架清单
     *
     * @param assetsSearchDo assetsSearchDo
     * @return List AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    List<AsAssetsPo> delistList(AsAssetsSearchDo assetsSearchDo);

    /**
     * 资产下架
     *
     * @param asAssetsPo asAssetsPo
     * @return int int
     */
    int delist(AsAssetsPo asAssetsPo);

    /**
     * 下架驳回的资产，重新申请下架
     *
     * @param asAssetsPo asAssetsPo
     * @return int int
     */
    int delistReapply(AsAssetsPo asAssetsPo);

    /**
     * 通过资产id查询资产明细
     *
     * @param id id
     * @return AsAssetsPo
     * @author yangliu
     * @date 2023/8/28
     */
    AsAssetsPo getAsAssetsById(Integer id);

    /**
     * 待审批列表
     *
     * @param assetsSearchDo assetsSearchDto
     * @return List AsAssetsPo
     * @author yangliu
     * @date 2023/8/29
     */
    List<AsAssetsPo> pendingList(AsAssetsSearchDo assetsSearchDo);

    /**
     * 资产删除
     *
     * @param id id
     * @param status status
     * @param userId userId
     * @return int
     * @author yangliu
     * @date 2023/8/30
     */
    int deleteById(@Param("id") Integer id, @Param("status") int status,  @Param("userId") String userId);

    /**
     * 审批通过
     *
     * @param asAssetsApproveBuilder asAssetsApproveBuilder
     * @return int
     * @author yangliu
     * @date 2023/8/30
     */
    int approved(AsAssetsApproveBuilder asAssetsApproveBuilder);

    /**
     * 审批驳回
     *
     * @param asAssetsApproveBuilder asAssetsApproveBuilder
     * @return int
     * @author yangliu
     * @date 2023/8/30
     */
    int rejection(AsAssetsApproveBuilder asAssetsApproveBuilder);

    /**
     * 资产总清单查询
     *
     * @param assetsSearchDo assetsSearchDo
     * @return null
     * @author yangliu
     * @date 2023/9/5
     */
    List<AsAssetsPo> masterList(AsAssetsSearchDo assetsSearchDo);

    /**
     * 审批驳回列表查询接口
     *
     * @param assetsSearchDo assetsSearchDo
     * @return List AsAssetsDo
     * @author yangliu
     * @date 2023/9/5
     */
    List<AsAssetsPo> rejectionList(AsAssetsSearchDo assetsSearchDo);
}




