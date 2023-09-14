/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.interfaces.controller.loophole;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.application.assets.LoopholeApplication;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.interfaces.converter.loophole.LoLoopholeDtoDoConverter;
import com.kunyu.assets.safety.interfaces.dto.loophole.LoLoopholeDto;
import com.kunyu.assets.safety.interfaces.dto.loophole.LoLoopholeSearchDto;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import com.kunyu.common.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static com.kunyu.assets.safety.interfaces.controller.loophole.LoopholeApplyController.isAdminByCode;

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
     * @description 修复工单审批查询--管理端
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
     * @description 创建修复工单--管理端
     * @author poet_wei
     * @date 2023/9/8
     */
    @RequestMapping(path = "/saveRepairOrder", method = RequestMethod.POST)
    public ApiResponse<LoLoopholeDo> saveRepairOrder(@RequestBody LoLoopholeDto loopholeDto) {
        checkPermissions();
        LoLoopholeDo loLoopholeDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeDo(loopholeDto);
        loLoopholeDo.setUpdateBy(ThreadLocalUtil.getUserInfo().getUserId());
        return ApiResponse.success(loopholeApplication.saveRepairOrder(loLoopholeDo));
    }

    /**
     * @param id 漏洞id
     * @return LoLoopholeDo
     * @description 查询漏洞byId--管理端
     * @author poet_wei
     * @date 2023/9/11
     */
    @RequestMapping(path = "/selectLoopholeById", method = RequestMethod.GET)
    public ApiResponse<LoLoopholeDo> selectLoopholeById(@RequestParam("id") Integer id) {
        // 校验是否是管理员
        checkPermissions();
        return ApiResponse.success(loopholeApplication.selectLoopholeById(id));
    }


    // 权限校验
    private static String checkPermissions() {
        String roleCode = ThreadLocalUtil.getUserInfo().getRoleCode();
        if (!isAdminByCode(roleCode)) {
            throw new PlatformException(HttpStatus.UNAUTHORIZED.value(), "非管理员不可以操作。");
        }
        return roleCode;
    }

    /**
     * @param loopholeSearchDto pageNum pageSize
     * @return LoLoopholeDo
     * @description 漏洞信息条件分页查询
     * @author poet_wei
     * @date 2023/9/6
     */
    @RequestMapping(path = "/adminListingList", method = RequestMethod.POST)
    public ApiResponse<PageInfo<LoLoopholeDo>> adminListingList(@RequestBody LoLoopholeSearchDto loopholeSearchDto,
                                                                @RequestParam(defaultValue = "1") int pageNum,
                                                                @RequestParam(defaultValue = "10") int pageSize) {
        // 校验是否是管理员
        checkPermissions();
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        LoLoopholeSearchDo loLoopholeSearchDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeSearchDo(loopholeSearchDto);
        return ApiResponse.success(loopholeApplication.adminListingList(loLoopholeSearchDo, pageNum, pageSize));
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
        String updateId = ThreadLocalUtil.getUserInfo().getUserId();
        String updateName = ThreadLocalUtil.getUserInfo().getUserId();
        return ApiResponse.success(loopholeApplication.approved(id, updateId, updateName));
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
        String updateId = ThreadLocalUtil.getUserInfo().getUserId();
        String updateName = ThreadLocalUtil.getUserInfo().getUserId();
        return ApiResponse.success(loopholeApplication.rejection(id, updateId, updateName));
    }
}
