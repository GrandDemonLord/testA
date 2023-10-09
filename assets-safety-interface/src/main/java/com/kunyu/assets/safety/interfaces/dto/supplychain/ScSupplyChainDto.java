/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.supplychain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunyu.assets.safety.interfaces.valid.supplychain.ScSuppyChainAddValid;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 供应链管理表 供应链Dto
 * @TableName sc_supply_chain
 */
@Data
public class ScSupplyChainDto {

    /**
     * 产品名称
     */
    @NotNull(message = "产品名称不能为空", groups = ScSuppyChainAddValid.class)
    private String productName;

    /**
     * 供应商名称
     */
    @NotNull(message = "供应商名称不能为空", groups = ScSuppyChainAddValid.class)
    private String supplierName;

    /**
     * 采购单位 id
     */
    @NotNull(message = "采购单位 id不能为空", groups = ScSuppyChainAddValid.class)
    private String purchasingUnitId;

    /**
     * 采购单位名称
     */
    @NotNull(message = "采购单位名称不能为空", groups = ScSuppyChainAddValid.class)
    private String purchasingUnitName;

    /**
     * 采购部门 id
     */
    @NotNull(message = "采购部门 id不能为空", groups = ScSuppyChainAddValid.class)
    private String purchasingDeptId;

    /**
     * 采购部门名称
     */
    @NotNull(message = "采购部门名称不能为空", groups = ScSuppyChainAddValid.class)
    private String purchasingDeptName;

    /**
     * 评分表类型
     */
    @NotNull(message = "评分表类型不能为空", groups = ScSuppyChainAddValid.class)
    private Integer scoreCardCode;


    /**
     * 评分表名称
     */
    @NotNull(message = "评分表名称不能为空", groups = ScSuppyChainAddValid.class)
    private String scoreCardName;
    /**
     * 评分表内容
     */
    private String scoreCardContent;

    /**
     * 评估日期 
     */
    @NotNull(message = "评估日期不能为空", groups = ScSuppyChainAddValid.class)
    @FutureOrPresent(message = "评估日期不能为过去时间", groups = ScSuppyChainAddValid.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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