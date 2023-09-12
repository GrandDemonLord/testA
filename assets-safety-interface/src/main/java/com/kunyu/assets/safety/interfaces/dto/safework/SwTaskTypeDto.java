/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.safework;

import com.kunyu.assets.safety.interfaces.valid.safework.SwTaskTypeAddValid;
import com.kunyu.assets.safety.interfaces.valid.safework.SwTaskTypeUpdateValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 任务类型Dto
 *
 * @author yangliu
 * @date 2023-09-07
 */
@Data
public class SwTaskTypeDto {
    @NotNull(message = "任务id不能为空", groups = SwTaskTypeUpdateValid.class)
    private Integer id;

    @NotBlank(message = "任务类型不能为空", groups = {SwTaskTypeAddValid.class, SwTaskTypeUpdateValid.class})
    private String taskType;

    @NotBlank(message = "任务说明不能为空", groups = {SwTaskTypeAddValid.class, SwTaskTypeUpdateValid.class})
    private String taskIllustrate;
}
