/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.service.assets;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.common.enums.assets.AssetsOptTypeEnum;
import com.kunyu.assets.safety.common.enums.assets.AssetsStatusEnum;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsApproveBuilder;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.domain.respository.assets.IAsAssetsRepository;
import com.kunyu.assets.safety.common.enums.common.ApproveStatusEnum;
import com.kunyu.common.enums.ModelStatusEnum;
import com.kunyu.common.exception.PlatformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
* 资产安全管理模块领域层
*
* @author yangliu
* @date 2023-08-25
*/
@Service
public class AssetsDomain {

    @Autowired
    private IAsAssetsRepository iAsAssetsRepository;

    /**
     * 资产上架清单 该清单包含个人名下所有的资产数据
     *
     * @param assetsSearchDo assetsSearchDo
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    public PageInfo<AsAssetsDo> listingList(AsAssetsSearchDo assetsSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AsAssetsDo> asAssetsDos = iAsAssetsRepository.listingList(assetsSearchDo);
        if (CollectionUtils.isEmpty(asAssetsDos)) {
            return null;
        }
        return new PageInfo<>(asAssetsDos);
    }

    /**
     * 新增资产上架
     *
     * @param asAssetsDo asAssetsDo
     * @return ApiResponse asAssetsDo
     */
    public AsAssetsDo listing(AsAssetsDo asAssetsDo) {
        asAssetsDo.setStatus(ModelStatusEnum.VAILD.getCode());
        asAssetsDo.setTypeOfOperation(AssetsOptTypeEnum.LISTING.getCode());
        asAssetsDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
        return iAsAssetsRepository.listing(asAssetsDo);
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
        AsAssetsDo asAssetsDoExist = iAsAssetsRepository.getAsAssetsById(asAssetsDo.getId());
        if (null == asAssetsDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "资产不存在。");
        }
        if (!ObjectUtils.isEmpty(asAssetsDoExist.getApproveStatus())
                && ApproveStatusEnum.SOURCEREJECTION.getCode().equals(asAssetsDoExist.getApproveStatus())) {
            asAssetsDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
            return iAsAssetsRepository.listingReapply(asAssetsDo);
        }
        throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "只能操作审批驳回的资产。");
    }

    public List<AsAssetsDo> batchListing(List<AsAssetsDo> asAssetsDos, String userId) {
        for (AsAssetsDo asAssetsDo : asAssetsDos) {
            asAssetsDo.setStatus(ModelStatusEnum.VAILD.getCode());
            asAssetsDo.setTypeOfOperation(AssetsOptTypeEnum.LISTING.getCode());
            asAssetsDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
            asAssetsDo.setCreateBy(userId);
        }
        return iAsAssetsRepository.batchListing(asAssetsDos);
    }

    /**
     * 资产下架清单 该清单包含个人名下上架审批通过的 & 下架审批通过的 $ 下架审批中的
     *
     * @param assetsSearchDo assetsSearchDo
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo AsAssetsDo
     * @author yangliu
     * @date 2023/8/25
     */
    public PageInfo<AsAssetsDo> delistList(AsAssetsSearchDo assetsSearchDo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AsAssetsDo> asAssetsDos = iAsAssetsRepository.delistList(assetsSearchDo);
        if (CollectionUtils.isEmpty(asAssetsDos)) {
            return null;
        }
        return new PageInfo<>(asAssetsDos);
    }

    /**
     * 资产下架
     *
     * @param asAssetsDo asAssetsDo
     * @return Boolean Boolean
     */
    public boolean delist(AsAssetsDo asAssetsDo) {
        AsAssetsDo asAssetsDoExist = iAsAssetsRepository.getAsAssetsById(asAssetsDo.getId());
        if (null == asAssetsDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "资产不存在。");
        }
        if (!ObjectUtils.isEmpty(asAssetsDoExist.getAssetStatus())
                && AssetsStatusEnum.VALID.getCode().equals(asAssetsDoExist.getAssetStatus())) {
            asAssetsDo.setTypeOfOperation(AssetsOptTypeEnum.DELIST.getCode());
            asAssetsDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
            return iAsAssetsRepository.delist(asAssetsDo);
        }
        throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "只能下架使用中的资产。");
    }

    /**
     * 下架驳回的资产，重新申请下架
     *
     * @param asAssetsDo asAssetsDo
     * @return Boolean Boolean
     */
    public boolean delistReapply(AsAssetsDo asAssetsDo) {
        AsAssetsDo asAssetsDoExist = iAsAssetsRepository.getAsAssetsById(asAssetsDo.getId());
        if (null == asAssetsDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "资产不存在。");
        }
        if (!ObjectUtils.isEmpty(asAssetsDoExist.getApproveStatus())
                && ApproveStatusEnum.SOURCEREJECTION.getCode().equals(asAssetsDoExist.getApproveStatus())) {
            asAssetsDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
            return iAsAssetsRepository.delistReapply(asAssetsDo);
        }
        throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "只能操作审批驳回的资产。");
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
        PageHelper.startPage(pageNum, pageSize);
        assetsSearchDo.setApproveStatus(ApproveStatusEnum.SOURCEPENDING.getCode());
        List<AsAssetsDo> asAssetsDos = iAsAssetsRepository.pendingList(assetsSearchDo);
        if (CollectionUtils.isEmpty(asAssetsDos)) {
            return null;
        }
        return new PageInfo<>(asAssetsDos);
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
        AsAssetsDo asAssetsDoExist = iAsAssetsRepository.getAsAssetsById(id);
        if (null == asAssetsDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "资产不存在。");
        }
        // todo 哪些状态的可以删除？已下架的？审批驳回的？
        return iAsAssetsRepository.deleteById(id, ModelStatusEnum.INVALID.getCode(), userId);
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
        AsAssetsDo asAssetsDoExist = iAsAssetsRepository.getAsAssetsById(id);
        if (null == asAssetsDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "资产不存在。");
        }
        // 只有待审批的数据才可进行此操作，防止数据被恶意篡改
        if (asAssetsDoExist.getApproveStatus().equals(ApproveStatusEnum.SOURCEPENDING.getCode())) {
            String assetStatus = getAssetStatus(asAssetsDoExist);
            return iAsAssetsRepository.approved(AsAssetsApproveBuilder.builder()
                    .id(id)
                    .approveStatus(ApproveStatusEnum.SOURCEAPPROVED.getCode())
                    .assetStatus(assetStatus)
                    .approveUserId(userId)
                    .approveUserName(userName)
                    .build());
        }
        throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
    }

    private String getAssetStatus(AsAssetsDo asAssetsDoExist) {
        String assetStatus = "";
        if (AssetsOptTypeEnum.LISTING.getCode().equals(asAssetsDoExist.getTypeOfOperation())) {
            assetStatus = AssetsStatusEnum.VALID.getCode();
        }
        if (AssetsOptTypeEnum.DELIST.getCode().equals(asAssetsDoExist.getTypeOfOperation())) {
            assetStatus = AssetsStatusEnum.INVALID.getCode();
        }
        return assetStatus;
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
        AsAssetsDo asAssetsDoExist = iAsAssetsRepository.getAsAssetsById(id);
        if (null == asAssetsDoExist) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "资产不存在。");
        }
        // 只有待审批的数据才可进行此操作，防止数据被恶意篡改
        if (asAssetsDoExist.getApproveStatus().equals(ApproveStatusEnum.SOURCEPENDING.getCode())) {
            return iAsAssetsRepository.rejection(AsAssetsApproveBuilder.builder()
                    .id(id)
                    .approveStatus(ApproveStatusEnum.SOURCEREJECTION.getCode())
                    .approveUserId(userId)
                    .approveUserName(userName)
                    .build());
        }
        throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "非法操作。");
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
        PageHelper.startPage(pageNum, pageSize);
        assetsSearchDo.setApproveStatus(ApproveStatusEnum.SOURCEREJECTION.getCode());
        List<AsAssetsDo> asAssetsDos = iAsAssetsRepository.rejectionList(assetsSearchDo);
        if (CollectionUtils.isEmpty(asAssetsDos)) {
            return null;
        }
        return new PageInfo<>(asAssetsDos);
    }
}
