/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.respository.safework;

import com.kunyu.assets.safety.domain.model.safework.SwTaskWorkOrderAssetsDo;

import java.util.List;

/**
 * 任务工单子表仓储接口
 *
 * @author yangliu
 * @date 2023-09-09
 */
public interface ISwTaskWorkOrderAssetsRepository {

    /**
     * 删除任务工单子表
     *
     * @param baseId baseId
     * @param status status
     * @param userId userId
     * @return boolean
     * @author yangliu
     * @date 2023/9/11
     */
    boolean deleteTaskWorkOrderAssetsByBaseId(Integer baseId, int status, String userId);

    /**
     * 新增任务工单子表
     * 
     * @param assetsDos assetsDos
     * @return boolean
     * @author yangliu
     * @date 2023/9/11
     */
    boolean addTaskWorkOrderAssets(List<SwTaskWorkOrderAssetsDo> assetsDos);
}
