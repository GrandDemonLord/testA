/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.legal;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 法律法规管理表查询Dto
 * @TableName lm_legal_management
 */
@Data
public class LmLegalManagementSearchDto {

    /**
     * 法律法规id
     */
    private Integer id;

    /**
     * 法律法规名称
     */
    private String lawName;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 年度
     */
    private String annual;

    /**
     * 单位id
     */
    private String unitId;

    /**
     * 角色id
     */
    private String roleCode;

    /**
     * 处置人id
     */
    private String processedId;

    /**
     * 检查时间
     */
    private Date checkTime;

    /**
     * 发布日期
     */
    private Date releaseDate;

    /**
     * 实施日期
     */
    private Date implementationDate;

    /**
     * ids
     */
    private List<Integer> ids; // 导出excel
}