/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.loophole;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.loophole.LoopholeApplication;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.interfaces.converter.loophole.LoLoopholeDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.loophole.LoLoopholeDto;
import com.kunyu.assets.safety.interfaces.dto.loophole.LoLoopholeSearchDto;
import com.kunyu.assets.safety.interfaces.valid.loophole.LoLoopholeOrderDtoValid;
import com.kunyu.common.enums.RoleEnum;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import com.kunyu.common.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author poet_wei
 * @description 漏洞审批接口层
 * @date 2023-09-06 10:45
 */
@RestController
@RequestMapping("/loophole/approve")
public class LoopholeApproveController {

    @Autowired
    private LoopholeApplication loopholeApplication;


    /**
     * @param loopholeSearchDto pageNum pageSize
     * @return LoLoopholeDo
     * @description 修复工单审批查询
     * @author poet_wei
     * @date 2023/9/8
     */
    @RequestMapping(path = "/selectApproveOrder", method = RequestMethod.POST)
    public ApiResponse<PageInfo<LoLoopholeDo>> selectRepairWorkOrder(@RequestBody LoLoopholeSearchDto loopholeSearchDto,
                                                                     @RequestParam(defaultValue = "1") int pageNum,
                                                                     @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }

        checkPermissions();
        LoLoopholeSearchDo loLoopholeSearchDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeSearchDo(loopholeSearchDto);
        return ApiResponse.success(loopholeApplication.selectApproveOrder(loLoopholeSearchDo, pageNum, pageSize));
    }


    /**
     * @param loopholeDto pageNum pageSize
     * @return LoLoopholeDo
     * @description 创建修复工单
     * @author poet_wei
     * @date 2023/9/8
     */
    @RequestMapping(path = "/saveRepairOrder", method = RequestMethod.POST)
    public ApiResponse<LoLoopholeDo> saveRepairOrder(@Validated(LoLoopholeOrderDtoValid.class) @RequestBody LoLoopholeDto loopholeDto) {
        LoLoopholeSearchDo loLoopholeSearchDo = new LoLoopholeSearchDo();
        loLoopholeSearchDo.setRoleCode(ThreadLocalUtil.getUserInfo().getRoleCode());
        loLoopholeSearchDo.setUnitId(ThreadLocalUtil.getUserInfo().getUnitId());
        LoLoopholeDo loLoopholeDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeDo(loopholeDto);
        loLoopholeDo.setUpdateBy(ThreadLocalUtil.getUserInfo().getUserId());
        loLoopholeDo.setApproveUserId(ThreadLocalUtil.getUserInfo().getUserId());
        loLoopholeDo.setApproveUserName(ThreadLocalUtil.getUserInfo().getUserName());
        return ApiResponse.success(loopholeApplication.saveRepairOrder(loLoopholeDo, loLoopholeSearchDo));
    }

    /**
     * @param id 工单id
     * @return LoLoopholeDo
     * @description 漏洞工单审批通过
     * @author poet_wei
     * @date 2023/9/12
     */
    @RequestMapping(path = "/approved/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> approved(@PathVariable Integer id) {
        // 校验是否是管理员
        checkPermissions();
        LoLoopholeSearchDo loLoopholeSearchDo = getLoLoopholeSearchDo(id);
        return ApiResponse.success(loopholeApplication.approved(loLoopholeSearchDo));
    }

    /**
     * @param id 工单id
     * @return LoLoopholeDo
     * @description 漏洞工单审批驳回
     * @author poet_wei
     * @date 2023/9/12
     */
    @RequestMapping(path = "/rejection/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> rejection(@PathVariable Integer id) {
        // 校验是否是管理员
        checkPermissions();
        LoLoopholeSearchDo loLoopholeSearchDo = getLoLoopholeSearchDo(id);
        return ApiResponse.success(loopholeApplication.rejection(loLoopholeSearchDo));
    }

    /**
     * @param id 漏洞id
     * @return LoLoopholeDo
     * @description 漏洞信息忽略
     * @author poet_wei
     * @date 2023/9/12
     */
    @RequestMapping(path = "/ignore/{id}", method = RequestMethod.GET)
    public ApiResponse<Boolean> ignore(@PathVariable Integer id) {
        // 校验是否是管理员
        checkPermissions();
        LoLoopholeSearchDo loLoopholeSearchDo = getLoLoopholeSearchDo(id);
        return ApiResponse.success(loopholeApplication.ignore(loLoopholeSearchDo));
    }

    // 封装基础数据
    private static LoLoopholeSearchDo getLoLoopholeSearchDo(Integer id) {
        LoLoopholeSearchDo loLoopholeSearchDo = new LoLoopholeSearchDo();
        loLoopholeSearchDo.setId(id);
        loLoopholeSearchDo.setUserId(ThreadLocalUtil.getUserInfo().getUserId());
        loLoopholeSearchDo.setUnitId(ThreadLocalUtil.getUserInfo().getUnitId());
        loLoopholeSearchDo.setUserName(ThreadLocalUtil.getUserInfo().getUserName());
        loLoopholeSearchDo.setRoleCode(ThreadLocalUtil.getUserInfo().getRoleCode());
        return loLoopholeSearchDo;
    }

    // 权限校验
    private static void checkPermissions() {
        String roleCode = ThreadLocalUtil.getUserInfo().getRoleCode();
        if (!RoleEnum.SYSGENERALADMIN.getRoleId().equals(roleCode) && !RoleEnum.SYSSUBADMIN.getRoleId().equals(roleCode)) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "管理员才能操作");
        }
    }
}
