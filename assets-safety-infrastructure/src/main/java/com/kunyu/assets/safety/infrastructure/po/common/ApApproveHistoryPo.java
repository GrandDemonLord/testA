/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.po.common;

import lombok.Data;

import java.util.Date;

/**
 * 审批记录表Po
 *
 * @author yangliu
 * @date 2023-09-07
 */
@Data
public class ApApproveHistoryPo {
    /**
     * id
     */
    private Integer id;

    /**
     * 主表id
     */
    private Integer baseId;

    /**
     * 表单类型code
     */
    private String formdataTypeCode;

    /**
     * 表单类型name
     */
    private String formdataTypeName;

    /**
     * 表单模块
     */
    private String formdataModule;

    /**
     * 流程节点：提交、管理员审批、处置人处置
     */
    private String instanceNode;

    /**
     * 处理人账号
     */
    private String approveUserId;

    /**
     * 处理人姓名
     */
    private String approveUserName;

    /**
     * 到达时间
     */
    private Date approveStartTime;

    /**
     * 处理时间
     */
    private Date approveEndTime;

    /**
     * 审批结果：通过、驳回
     */
    private String approveResult;

    /**
     * 状态，0：可用、1：删除
     */
    private Integer status;
}