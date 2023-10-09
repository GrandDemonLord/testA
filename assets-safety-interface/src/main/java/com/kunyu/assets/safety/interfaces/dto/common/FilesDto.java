/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.common;

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
public class FilesDto {

    @NotBlank(message = "附件id不能为空", groups = FilesDownloadValid.class)
    private String fileId;

    @NotBlank(message = "附件名称不能为空", groups = {FilesDownloadValid.class, FilesTemplateDownloadValid.class})
    private String fileName;

    @NotBlank(message = "附件类型不能为空", groups = FilesTemplateDownloadValid.class)
    private String fileType; // 用于模板下载
}
