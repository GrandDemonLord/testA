/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.po.supplychain;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.time.LocalDate;

/**
 * 供应链管理表 供应链Do
 * @TableName sc_supply_chain
 */
@Data
public class ScSupplyChainPo extends BaseModel {

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 采购单位 id
     */
    private String purchasingUnitId;

    /**
     * 采购单位名称
     */
    private String purchasingUnitName;

    /**
     * 采购部门 id
     */
    private String purchasingDeptId;

    /**
     * 采购部门名称
     */
    private String purchasingDeptName;

    /**
     * 评分表类型
     */
    private Integer scoreCardCode;

    /**
     * 评分表名称
     */
    private String scoreCardName;

    /**
     * 评分表内容
     */
    private String scoreCardContent;

    /**
     * 评估日期 
     */
    private LocalDate assessmentDate;

    /**
     * 评估状态
     */
    private String assessmentStatus;

    /**
     * 评分
     */
    private String score;

}