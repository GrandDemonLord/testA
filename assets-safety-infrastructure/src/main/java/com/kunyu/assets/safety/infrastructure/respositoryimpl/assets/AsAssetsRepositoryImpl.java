/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.respositoryimpl.assets;

import com.kunyu.assets.safety.domain.model.assets.AsAssetsApproveBuilder;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsDo;
import com.kunyu.assets.safety.domain.model.assets.AsAssetsSearchDo;
import com.kunyu.assets.safety.domain.respository.assets.IAsAssetsRepository;
import com.kunyu.assets.safety.infrastructure.converter.assets.AsDoPoConverter;
import com.kunyu.assets.safety.infrastructure.dao.assets.AsAssetsMapper;
import com.kunyu.assets.safety.infrastructure.po.assets.AsAssetsPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 资产数据仓储接口实现类
*
* @author yangliu
* @date 2023-08-25
*/
@Repository
public class AsAssetsRepositoryImpl implements IAsAssetsRepository {
    @Autowired
    private AsAssetsMapper asAssetsMapper;

    @Override
    public List<AsAssetsDo> listingList(AsAssetsSearchDo assetsSearchDo) {
        List<AsAssetsPo> asAssetsPos = asAssetsMapper.listingList(assetsSearchDo);
        return AsDoPoConverter.INSTANCE.getAsAssetsDos(asAssetsPos);
    }

    @Override
    public AsAssetsDo listing(AsAssetsDo asAssetsDo) {
        AsAssetsPo asAssetsPo = AsDoPoConverter.INSTANCE.getAsAssetsPo(asAssetsDo);
        asAssetsMapper.saveListing(asAssetsPo);
        asAssetsDo.setId(asAssetsPo.getId());
        return asAssetsDo;
    }

    @Override
    public AsAssetsDo listingReapply(AsAssetsDo asAssetsDo) {
        AsAssetsPo asAssetsPo = AsDoPoConverter.INSTANCE.getAsAssetsPo(asAssetsDo);
        asAssetsMapper.saveListingReapply(asAssetsPo);
        return asAssetsDo;
    }

    @Override
    public List<AsAssetsDo> batchListing(List<AsAssetsDo> asAssetsDos) {
        List<AsAssetsPo> asAssetsPos = AsDoPoConverter.INSTANCE.getAsAssetsPos(asAssetsDos);
        asAssetsMapper.batchListing(asAssetsPos);
        return asAssetsDos;
    }

    @Override
    public List<AsAssetsDo> delistList(AsAssetsSearchDo assetsSearchDo) {
        List<AsAssetsPo> asAssetsPos = asAssetsMapper.delistList(assetsSearchDo);
        return AsDoPoConverter.INSTANCE.getAsAssetsDos(asAssetsPos);
    }

    @Override
    public boolean delist(AsAssetsDo asAssetsDo) {
        AsAssetsPo asAssetsPo = AsDoPoConverter.INSTANCE.getAsAssetsPo(asAssetsDo);
        return asAssetsMapper.delist(asAssetsPo) > 0 ? true : false;
    }

    @Override
    public boolean delistReapply(AsAssetsDo asAssetsDo) {
        AsAssetsPo asAssetsPo = AsDoPoConverter.INSTANCE.getAsAssetsPo(asAssetsDo);
        return asAssetsMapper.delistReapply(asAssetsPo) > 0 ? true : false;
    }

    @Override
    public AsAssetsDo getAsAssetsById(Integer id) {
        AsAssetsPo asAssetsPo = asAssetsMapper.getAsAssetsById(id);
        return AsDoPoConverter.INSTANCE.getAsAssetsDo(asAssetsPo);
    }

    @Override
    public List<AsAssetsDo> pendingList(AsAssetsSearchDo assetsSearchDo) {
        List<AsAssetsPo> asAssetsPos = asAssetsMapper.pendingList(assetsSearchDo);
        return AsDoPoConverter.INSTANCE.getAsAssetsDos(asAssetsPos);
    }

    @Override
    public boolean deleteById(Integer id, int status, String userId) {
        return asAssetsMapper.deleteById(id, status, userId) > 0 ? true : false;
    }

    @Override
    public boolean approved(AsAssetsApproveBuilder asAssetsApproveBuilder) {
        return asAssetsMapper.approved(asAssetsApproveBuilder) > 0 ? true : false;
    }

    @Override
    public boolean rejection(AsAssetsApproveBuilder asAssetsApproveBuilder) {
        return asAssetsMapper.rejection(asAssetsApproveBuilder) > 0 ? true : false;
    }

    @Override
    public List<AsAssetsDo> masterList(AsAssetsSearchDo assetsSearchDo) {
        List<AsAssetsPo> asAssetsPos = asAssetsMapper.masterList(assetsSearchDo);
        return AsDoPoConverter.INSTANCE.getAsAssetsDos(asAssetsPos);
    }

    @Override
    public List<AsAssetsDo> rejectionList(AsAssetsSearchDo assetsSearchDo) {
        List<AsAssetsPo> asAssetsPos = asAssetsMapper.rejectionList(assetsSearchDo);
        return AsDoPoConverter.INSTANCE.getAsAssetsDos(asAssetsPos);
    }
}
