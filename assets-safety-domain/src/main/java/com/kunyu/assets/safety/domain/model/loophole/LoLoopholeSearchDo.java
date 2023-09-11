/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.loophole;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @author poet_wei
 * @description
 * @date 2023-09-06 11:53
 */
@Data
public class LoLoopholeSearchDo extends BaseModel {
    private String name;
    private String type;
    private String level;
    private String taskStatus;
    private Date taskStartTime;
    private Date taskEndTime;
}
