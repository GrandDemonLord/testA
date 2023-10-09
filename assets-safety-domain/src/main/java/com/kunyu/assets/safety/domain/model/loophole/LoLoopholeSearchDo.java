/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.loophole;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

/**
 * @author poet_wei
 * @description
 * @date 2023-09-06 11:53
 */
@Data
public class LoLoopholeSearchDo extends BaseModel {

    private Integer id;

    /**
     * 漏洞名称
     */
    private String name;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 单位id
     */
    private String unitId;

    /**
     * 角色id
     */
    private String roleCode;

    /**
     * 漏洞类型id
     */
    private Integer loopholeTypeId;

    /**
     * 漏洞类型名称
     */
    private String loopholeTypeName;

    /**
     * 漏洞等级id
     */
    private Integer loopholeLevelId;

    /**
     * 漏洞等级名称
     */
    private String loopholeLevelName;

    /**
     * 漏洞审批状态
     */
    private String approveStatus;

    /**
     * 处置人id
     */
    private String processedId;

    /**
     * 处置人
     */
    private String processedBy;

    /**
     * 任务周期开始时间
     */
    private String taskStartTime;

    /**
     * 任务周期结束时间
     */
    private String taskEndTime;

    /**
     * 任务开始时间
     */
    private String orderStartTime;

    /**
     * 任务结束时间
     */
    private String orderEndTime;

    /**
     * 工单报告状态
     */
    private String reportStatus;


}
