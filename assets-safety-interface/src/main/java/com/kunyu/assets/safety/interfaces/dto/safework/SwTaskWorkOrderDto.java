/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.safework;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunyu.assets.safety.interfaces.valid.safework.SwTaskWorkOrderAddValid;
import com.kunyu.assets.safety.interfaces.valid.safework.SwTaskWorkOrderUpdateValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 任务工单Dto
 *
 * @author yangliu
 * @date 2023-09-09
 */
@Data
public class SwTaskWorkOrderDto {

    @NotNull(message = "任务id不能为空", groups = {SwTaskWorkOrderUpdateValid.class})
    private Integer id;

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String taskName;

    /**
     * 任务类型id
     */
    @NotBlank(message = "任务名称不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String taskTypeId;

    /**
     * 任务类型name
     */
    @NotBlank(message = "任务名称不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String taskTypeName;

    /**
     * 任务级别id
     */
    @NotBlank(message = "任务级别不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String taskLevelId;

    /**
     * 任务级别name
     */
    @NotBlank(message = "任务级别不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String taskLevelName;

    /**
     * 任务周期-开始时间
     */
    @NotNull(message = "任务周期-开始时间不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date taskStartTime;

    /**
     * 任务周期-结束时间
     */
    @NotNull(message = "任务周期-结束时间不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date taskEndTime;

    /**
     * 涉及资产
     */
    private String assetName;

    /**
     * 直属单位id
     */
    @NotBlank(message = "直属单位不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String owningUnitId;

    /**
     * 直属单位name
     */
    @NotBlank(message = "直属单位不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String owningUnitName;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String responsiblePerson;

    /**
     * 负责人联系方式
     */
    @NotBlank(message = "负责人联系方式不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String contactInformation;

    /**
     * 任务目标
     */
    @NotBlank(message = "任务目标不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String taskObjectives;

    /**
     * 处置人
     */
    @NotBlank(message = "处置人不能为空", groups = {SwTaskWorkOrderAddValid.class, SwTaskWorkOrderUpdateValid.class})
    private String processedBy;

    /**
     * 涉及资产
     */
    private List<Integer> assetIds;
}
