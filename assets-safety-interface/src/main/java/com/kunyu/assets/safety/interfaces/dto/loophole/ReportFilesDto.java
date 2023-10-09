/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.loophole;

import com.kunyu.assets.safety.interfaces.valid.common.FilesDownloadValid;
import com.kunyu.assets.safety.interfaces.valid.common.FilesTemplateDownloadValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
* @description
*
* @author yangliu
* @date 2023-08-28
*/
@Data
public class ReportFilesDto {

    private Integer id;

    private String loopholeReportId;

    private String loopholeReportName;

    private String userId;

    private String userName;

    private String unitId;
}
