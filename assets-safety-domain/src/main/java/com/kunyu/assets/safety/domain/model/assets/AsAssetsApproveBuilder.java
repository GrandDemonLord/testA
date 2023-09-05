/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.domain.model.assets;

import lombok.Builder;
import lombok.Data;

/**
* 资产审批更新模型
*
* @author yangliu
* @date 2023-08-30
*/
@Data
@Builder
public class AsAssetsApproveBuilder {
    private Integer id;

    /**
     * 审批状态，pending：待审批、approved：审批通过、rejection：审批驳回
     */
    private String approveStatus;

    /**
     * 资产状态，资产状态，valid：使用中、inValid：已下架
     */
    private String assetStatus;

    /**
     * 审批人账号
     */
    private String approveUserId;

    /**
     * 审批人姓名
     */
    private String approveUserName;
}
