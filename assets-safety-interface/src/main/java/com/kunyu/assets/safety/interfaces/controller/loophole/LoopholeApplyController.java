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
import com.kunyu.common.enums.RoleEnum;
import com.kunyu.common.exception.PlatformException;
import com.kunyu.common.result.ApiResponse;
import com.kunyu.common.util.RoleUtils;
import com.kunyu.common.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author poet_wei
 * @description 漏洞信息管理接口层
 * @date 2023-09-06 10:43
 */
@RestController
@RequestMapping("/loophole/apply")
public class LoopholeApplyController {


    @Autowired
    private LoopholeApplication loopholeApplication;


    /**
     * @description 漏洞信息条件分页查询
     *
     * @param loopholeSearchDto pageNum pageSize
     * @return LoLoopholeDo
     * @author poet_wei
     * @date 2023/9/6
     */
    @RequestMapping(path = "/listingList", method = RequestMethod.POST)
    public ApiResponse<PageInfo<LoLoopholeDo>> listingList(@RequestBody LoLoopholeSearchDto loopholeSearchDto,
                                                           @RequestParam(defaultValue = "1") int pageNum,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        LoLoopholeSearchDo loLoopholeSearchDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeSearchDo(loopholeSearchDto);
        String roleCode = ThreadLocalUtil.getUserInfo().getRoleCode();
        // 校验单位是否存在

        // 校验部门是否存在

        // 校验部门负责人是否存在

        if(RoleEnum.SYSENGINEER.getRoleId().equals(roleCode) || RoleEnum.SYSUSER.getRoleId().equals(roleCode)){
            loLoopholeSearchDo.setCreateBy(ThreadLocalUtil.getUserInfo().getUserId());
        }
        return ApiResponse.success(loopholeApplication.listingList(loLoopholeSearchDo, pageNum, pageSize));
    }

    /**
     * @description 新增漏洞信息
     *
     * @param loopholeDto
     * @return LoLoopholeDo
     * @author poet_wei
     * @date 2023/9/6
     */
    @RequestMapping(path = "/saveLoophole", method = RequestMethod.POST)
    public ApiResponse<LoLoopholeDo> saveLoophole(@RequestBody LoLoopholeDto loopholeDto) {
        LoLoopholeDo loLoopholeDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeDo(loopholeDto);
        loLoopholeDo.setCreateBy(ThreadLocalUtil.getUserInfo().getUserId());
        loLoopholeDo.setUpdateBy(ThreadLocalUtil.getUserInfo().getUserId());
        return ApiResponse.success(loopholeApplication.saveLoophole(loLoopholeDo));
    }

    /**
     * @description 修复工单信息查询--用户端
     *
     * @param loopholeSearchDto pageNum pageSize
     * @return LoLoopholeDo
     * @author poet_wei
     * @date 2023/9/8
     */
    @RequestMapping(path = "/selectRepairOrder", method = RequestMethod.POST)
    public ApiResponse<PageInfo<LoLoopholeDo>> selectRepairWorkOrder(@RequestBody LoLoopholeSearchDto loopholeSearchDto,
                                                           @RequestParam(defaultValue = "1") int pageNum,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize > 200) {
            throw new PlatformException(HttpStatus.BAD_REQUEST.value(), "每页条数不能超过200。");
        }
        LoLoopholeSearchDo loLoopholeSearchDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeSearchDo(loopholeSearchDto);
        // 校验是否是普通用户
        String roleCode = ThreadLocalUtil.getUserInfo().getRoleCode();
        if (RoleUtils.isUserByCode(roleCode)) {
            loLoopholeSearchDo.setCreateBy(ThreadLocalUtil.getUserInfo().getUserId());
        }
        return ApiResponse.success(loopholeApplication.selectRepairWorkOrder(loLoopholeSearchDo, pageNum, pageSize));
    }

}
