/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.legal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunyu.assets.safety.interfaces.valid.legal.LmLagalManagementDtoAddValid;
import com.kunyu.assets.safety.interfaces.valid.legal.LmLagalManagementDtoCheckValid;
import com.kunyu.assets.safety.interfaces.valid.legal.LmLagalManagementDtoUpdateValid;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 法律法规管理表
 *
 * @TableName lm_legal_management
 */
@Data
public class LmLegalManagementDto {

    /**
     * 法律法规id
     */
    @NotNull(message = "法律法规不能为空", groups = {LmLagalManagementDtoUpdateValid.class,LmLagalManagementDtoCheckValid.class})
    private Integer id;

    /**
     * 法律法规名称
     */
    @NotNull(message = "法律法规名称不能为空", groups = {LmLagalManagementDtoAddValid.class,  LmLagalManagementDtoUpdateValid.class})
    private String lawName;

    /**
     * 正在使用法律附件id
     */
    private String lawAttachmentUsingId;

    /**
     * 法律附件名称
     */
    private String lawAttachmentName;

    /**
     * 待审批法律附件id
     */
    private String lawAttachmentPendingId;

    /**
     * 年度
     */
    @NotNull(message = "未选择年度", groups = {LmLagalManagementDtoAddValid.class, LmLagalManagementDtoUpdateValid.class})
    private String annual;

    /**
     * 检查单位id
     */
    @NotNull(message = "未填写检查单位", groups = LmLagalManagementDtoCheckValid.class)
    private Integer checkUnitId;

    /**
     * 检查单位名称
     */
    @NotNull(message = "未填写检查单位", groups = LmLagalManagementDtoCheckValid.class)
    private String checkUnitName;

    /**
     * 负责人账号
     */
    @NotNull(message = "未填写负责人账号", groups = LmLagalManagementDtoCheckValid.class)
    private String responsiblePerson;

    /**
     * 负责人名称
     */
    @NotNull(message = "未填写负责人名称", groups = LmLagalManagementDtoCheckValid.class)
    private String responsiblePersonName;

    /**
     * 检查时间
     */
    private Date checkTime;

    /**
     * 适用对象
     */
    private String applicableObject;

    /**
     * 发布日期
     */
    private Date releaseDate;

    /**
     * 实施日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @NotNull(message = "未填写实施日期", groups = {LmLagalManagementDtoAddValid.class, LmLagalManagementDtoUpdateValid.class})
    private Date implementationDate;

}