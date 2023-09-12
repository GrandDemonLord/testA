/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.respositoryimpl.safework;

import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderAssetsDo;
import com.kunyu.assets.safety.domain.respository.safework.ISwTaskWorkOrderAssetsRepository;
import com.kunyu.assets.safety.infrastructure.converter.safework.SwDoPoConverter;
import com.kunyu.assets.safety.infrastructure.dao.safework.SwTaskWorkOrderAssetsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务工单子表仓储实现类
 *
 * @author yangliu
 * @date 2023-09-11
 */
@Repository
public class SwTaskWorkOrderAssetsRepositoryImpl implements ISwTaskWorkOrderAssetsRepository {

    @Autowired
    private SwTaskWorkOrderAssetsMapper swTaskWorkOrderAssetsMapper;

    @Override
    public boolean deleteTaskWorkOrderAssetsByBaseId(Integer baseId, int status, String userId) {
        return swTaskWorkOrderAssetsMapper.deleteTaskWorkOrderAssetsByBaseId(baseId, status, userId) > 0 ? true : false;
    }

    @Override
    public boolean addTaskWorkOrderAssets(List<SwTaskWorkOrderAssetsDo> assetsDos) {
        return swTaskWorkOrderAssetsMapper.addTaskWorkOrderAssets(SwDoPoConverter.INSTANCE.getSwTaskWorkOrderAssetsPos(assetsDos)) > 0 ? true : false;
    }
}
