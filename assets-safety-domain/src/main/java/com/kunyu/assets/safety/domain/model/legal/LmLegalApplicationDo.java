/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.legal;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

/**
 * 法律法规子表----适用对象
 * @TableName lm_legal_application
 */
@Data
public class LmLegalApplicationDo extends BaseModel {

    /**
     * 法律法规id
     */
    private Integer lawId;

    /**
     * 法律法规适用对象单位id
     */
    private Integer applicableObjectUnitId;

    /**
     * 法律法规适用对象单位名称
     */
    private String applicableObjectUnitName;

    /**
     * 法律法规适用对象部门id
     */
    private Integer applicableObjectDeptId;

    /**
     * 法律法规适用对象部门名称
     */
    private String applicableObjectDeptName;
    
}