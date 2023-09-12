/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.dao.safework;

import com.kunyu.assets.safety.infrastructure.po.safework.SwTaskWorkOrderAssetsPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务工单子表持久层接口
 *
 * @author yangliu
 * @date 2023-09-09
 */
public interface SwTaskWorkOrderAssetsMapper {
    int deleteTaskWorkOrderAssetsByBaseId(@Param("baseId") Integer baseId, @Param("status") int status, @Param("userId") String userId);

    int addTaskWorkOrderAssets(List<SwTaskWorkOrderAssetsPo> assetsPos);
}
