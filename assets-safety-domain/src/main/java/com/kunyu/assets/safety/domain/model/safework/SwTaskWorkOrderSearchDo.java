/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.safework;

import lombok.Data;

/**
 * 任务工单查询Do
 *
 * @author yangliu
 * @date 2023-09-09
 */
@Data
public class SwTaskWorkOrderSearchDo {
    private String taskName;
    private String userId;
    private String approveStatus;
}
