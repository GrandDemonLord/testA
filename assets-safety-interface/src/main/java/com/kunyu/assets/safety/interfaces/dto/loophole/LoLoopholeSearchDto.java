/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.loophole;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author poet_wei
 * @description 漏洞信息查询Dto
 * @date 2023-09-06 11:37
 */
@Data
public class LoLoopholeSearchDto {

    private String name;

    private String dataTypeCode;

    private String dataTypeName;

    private Integer loopholeTypeId;

    private String loopholeTypeName;

    private Integer loopholeLevelId;

    private String loopholeLevelName;

    private String approveStatus;

    private String taskStartTime;

    private String taskEndTime;

}
