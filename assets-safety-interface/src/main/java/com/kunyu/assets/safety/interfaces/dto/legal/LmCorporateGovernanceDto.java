/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.dto.legal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunyu.assets.safety.interfaces.valid.legal.LmCorporateGovernanceDtoAddValid;
import com.kunyu.assets.safety.interfaces.valid.legal.LmCorporateGovernanceDtoUpdateValid;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 企业制度管理表
 * @TableName lm_corporate_governance
 */
@Data
public class LmCorporateGovernanceDto {

    /**
     * 企业制度id
     */
    @NotNull(message = "企业制度不能为空", groups = LmCorporateGovernanceDtoUpdateValid.class)
    private Integer id;
    
    /**
     * 企业制度名称
     */
    @NotNull(message = "企业制度名称不能为空", groups = {LmCorporateGovernanceDtoAddValid.class, LmCorporateGovernanceDtoUpdateValid.class})
    private String corporateGovernanceName;

    /**
     * 企业制度附件id
     */
    private String corporateGovernanceAttachmentId;

    /**
     * 企业制度附件名称
     */
    private String corporateGovernanceAttachmentName;

    /**
     * 依据法律id
     */
    @NotNull(message = "依据法律不能为空", groups = {LmCorporateGovernanceDtoAddValid.class,LmCorporateGovernanceDtoUpdateValid.class})
    private List<Integer> lawIdList;

    /**
     * 依据法律名称
     */
    private List<String> lawNamesList;

    /**
     * 发布日期
     */
    private Date releaseDate;

    /**
     * 实施日期
     */
    @NotNull(message = "实施日期不能为空", groups = {LmCorporateGovernanceDtoAddValid.class,LmCorporateGovernanceDtoUpdateValid.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date implementationDate;

}