/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.loophole;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author poet_wei
 * @description
 * @date 2023-09-06 11:53
 */
@Data
public class LoLoopholeSearchDo extends BaseModel {
    private String name;
    private String type;
    private String level;
    private String taskStatus;

    /**
     * 任务周期开始时间
     */
    private Date taskStartTime;

    /**
     * 任务周期结束时间
     */
    private Date taskEndTime;

    /**
     * 任务开始时间
     */
    private Date orderStartTime;

    /**
     * 任务结束时间
     */
    private Date orderEndTime;

    /**
     * 工单报告状态
     */
    private String reportStatus;
}
