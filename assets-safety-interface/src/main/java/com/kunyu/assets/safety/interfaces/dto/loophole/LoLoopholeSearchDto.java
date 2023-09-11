/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.loophole;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author poet_wei
 * @description 漏洞信息查询Dto
 * @date 2023-09-06 11:37
 */
@Data
public class LoLoopholeSearchDto {
    private String name;
    private String type;
    private String level;
    private String taskStatus;
    /**
     * 任务开始时间
     */

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date taskStartTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date taskEndTime;

}
