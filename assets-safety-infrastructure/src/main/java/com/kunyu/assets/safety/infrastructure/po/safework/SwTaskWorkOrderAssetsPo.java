/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.po.safework;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 任务工单子表
 *
 * @author yangliu
 * @date 2023-09-11
 */
@Data
public class SwTaskWorkOrderAssetsPo extends BaseModel {
    private Integer id;

    /**
     * sw_task_work_order表id
     */
    private Integer baseId;

    /**
     * as_assets表id
     */
    private Integer assetId;

    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private Integer status;
}
