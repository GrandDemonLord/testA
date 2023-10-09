/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.common.enums.common;

import com.kunyu.common.models.BaseModel;
import lombok.Data;

/**
 * @description 用户表领域对象
 * @author yangliu
 * @date 2023-08-02
 */
@Data
public class UserDo extends BaseModel {
    private String userId;
    private String userName;
    private String password;
    private Boolean openingUp;
    private String groupId;
    private String roleId;
    private String groupName;
    private String roleName;
}
