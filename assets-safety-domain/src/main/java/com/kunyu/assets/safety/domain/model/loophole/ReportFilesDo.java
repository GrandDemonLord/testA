/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.loophole;

import lombok.Data;

@Data
public class ReportFilesDo {

    private Integer id;

    private String loopholeReportId;

    private String loopholeReportName;

    private String userId;

    private String userName;

    private String unitId;

}