/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.loophole;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunyu.assets.safety.interfaces.valid.loophole.LoLoopholeDtoValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author poet_wei
 * @description 漏洞信息Dto
 * @date 2023-09-06 14:04
 */
@Data
public class LoLoopholeDto {

    private Integer id;
    /**
     * 漏洞名称
     */
    @NotBlank(message = "漏洞名称不能为空", groups = LoLoopholeDtoValid.class)
    private String name;

    /**
     * 漏洞类型
     */
    @NotBlank(message = "漏洞类型不能为空", groups = LoLoopholeDtoValid.class)
    private String type;

    /**
     * 漏洞等级
     */
    @NotBlank(message = "漏洞等级不能为空", groups = LoLoopholeDtoValid.class)
    private String level;

    /**
     * 漏洞来源
     */
    @NotBlank(message = "漏洞来源不能为空", groups = LoLoopholeDtoValid.class)
    private String source;

    /**
     * 目标地址
     */
    @NotBlank(message = "目标地址不能为空", groups = LoLoopholeDtoValid.class)
    private String targetAddress;

    /**
     * 受影响的组件
     */
    @NotBlank(message = "受影响的组件不能为空", groups = LoLoopholeDtoValid.class)
    private String affectedComponents;

    /**
     * 单位（用户组）
     */
    @NotBlank(message = "单位不能为空", groups = LoLoopholeDtoValid.class)
    private String groupUnit;
    /**
     * 单位id（用户组）
     */
    private String groupUnitId;

    /**
     * 部门（用户组）
     */
    @NotBlank(message = "漏洞名称不能为空", groups = LoLoopholeDtoValid.class)
    private String groupDepartment;

    /**
     * 部门id（用户组）
     */
    private String groupDepartmentId;

    /**
     * 任务开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date taskStartTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date taskEndTime;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = LoLoopholeDtoValid.class)
    private String responsible;

    /**
     * 负责人联系方式
     */
    private String phone;

    /**
     * 修复建议
     */
    @NotBlank(message = "修复建议不能为空", groups = LoLoopholeDtoValid.class)
    private String fixRecommendation;

    /**
     * 设备处置人
     */
    @NotBlank(message = "处置人不能为空", groups = LoLoopholeDtoValid.class)
    private String disposer;
}
