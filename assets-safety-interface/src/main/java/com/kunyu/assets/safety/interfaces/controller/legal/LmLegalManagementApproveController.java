/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.legal;

import com.kunyu.assets.safety.application.legal.LegalManagementApplication;
import com.kunyu.assets.safety.domain.model.legal.LmLegalManagementSearchDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.interfaces.dto.legal.LmLegalManagementSearchDto;
import com.kunyu.common.enums.RoleEnum;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.models.UserInfo;
import com.kunyu.common.result.ApiResponse;
import com.kunyu.common.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author poet_wei
 * @description 法律法规信息审批接口层
 * @date 2023-09-26 17:45
 */
@RestController
@RequestMapping("/legal/approve")
public class LmLegalManagementApproveController {

    @Autowired
    private LegalManagementApplication legalManagementApplication;


    /**
     * @param id 工单id
     * @return LoLoopholeDo
     * @description 法规工单审批通过
     * @author poet_wei
     * @date 2023/9/12
     */
    @RequestMapping(path = "/approved/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> approved(@PathVariable Integer id) {
        // 校验是否是管理员
        checkPermissions();
        LmLegalManagementSearchDo loLoopholeSearchDo = getLoLoopholeSearchDo(id);
        return ApiResponse.success(legalManagementApplication.approved(loLoopholeSearchDo));
    }

    /**
     * @param id 工单id
     * @return LoLoopholeDo
     * @description 法规工单审批驳回
     * @author poet_wei
     * @date 2023/9/12
     */
    @RequestMapping(path = "/rejection/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> rejection(@PathVariable Integer id) {
        // 校验是否是管理员
        checkPermissions();
        LmLegalManagementSearchDo loLoopholeSearchDo = getLoLoopholeSearchDo(id);
        return ApiResponse.success(legalManagementApplication.rejection(loLoopholeSearchDo));
    }

    // 封装基础数据
    private static LmLegalManagementSearchDo getLoLoopholeSearchDo(Integer id) {
        LmLegalManagementSearchDo searchDo = new LmLegalManagementSearchDo();
        searchDo.setId(id);
        searchDo.setUserId(ThreadLocalUtil.getUserInfo().getUserId());
        searchDo.setUnitId(ThreadLocalUtil.getUserInfo().getUnitId());
        searchDo.setUserName(ThreadLocalUtil.getUserInfo().getUserName());
        searchDo.setRoleCode(ThreadLocalUtil.getUserInfo().getRoleCode());
        return searchDo;
    }

    // 权限校验是否是总厂管理员
    private static UserInfo checkPermissions() {
        UserInfo userInfo = ThreadLocalUtil.getUserInfo();
        if (!RoleEnum.SYSGENERALADMIN.getRoleId().equals(userInfo.getRoleCode())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "无权限操作。");
        }
        return userInfo;
    }

    // 权限校验是否是分厂管理员
    private static UserInfo checkBranchPermissions() {
        UserInfo userInfo = ThreadLocalUtil.getUserInfo();
        if (!RoleEnum.SYSSUBADMIN.getRoleId().equals(userInfo.getRoleCode())) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "无权限操作。");
        }
        return userInfo;
    }
}
