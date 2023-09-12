/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.po.safework;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

/**
 * 任务类型po
 *
 * @author yangliu
 * @date 2023-09-07
 */
@Data
public class SwTaskTypePo extends BaseModel {
    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 任务说明
     */
    private String taskIllustrate;
}
