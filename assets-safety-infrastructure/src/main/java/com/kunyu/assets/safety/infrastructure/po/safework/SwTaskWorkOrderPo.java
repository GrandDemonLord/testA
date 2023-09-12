/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.po.safework;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 任务工单Po
 *
 * @author yangliu
 * @date 2023-09-09
 */
@Data
public class SwTaskWorkOrderPo extends BaseModel {
    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务类型id
     */
    private String taskTypeId;

    /**
     * 任务类型name
     */
    private String taskTypeName;

    /**
     * 任务级别id
     */
    private String taskLevelId;

    /**
     * 任务级别name
     */
    private String taskLevelName;

    /**
     * 任务周期-开始时间
     */
    private Date taskStartTime;

    /**
     * 任务周期-结束时间
     */
    private Date taskEndTime;

    /**
     * 涉及资产
     */
    private String assetName;

    /**
     * 直属单位id
     */
    private String owningUnitId;

    /**
     * 直属单位name
     */
    private String owningUnitName;

    /**
     * 负责人
     */
    private String responsiblePerson;

    /**
     * 负责人联系方式
     */
    private String contactInformation;

    /**
     * 任务目标
     */
    private String taskObjectives;

    /**
     * 处置人
     */
    private String processedBy;

    /**
     * 审批人账号
     */
    private String approveUserId;

    /**
     * 审批人姓名
     */
    private String approveUserName;

    /**
     * 审批时间
     */
    private Date approveTime;

    /**
     * 审批状态，pending：待审批、approved：审批通过、rejection：审批驳回
     */
    private String approveStatus;

    private List<SwTaskWorkOrderAssetsPo> assets;
}
