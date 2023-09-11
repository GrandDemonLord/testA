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
     * @description 修复工单审批查询--管理端
     *
     * @param loopholeSearchDto pageNum pageSize
     * @return LoLoopholeDo
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
        LoLoopholeSearchDo loLoopholeSearchDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeSearchDo(loopholeSearchDto);
        return ApiResponse.success(loopholeApplication.selectApproveOrder(loLoopholeSearchDo, pageNum, pageSize));
    }

    /**
     * @description 创建修复工单--管理端
     *
     * @param loopholeDto pageNum pageSize
     * @return LoLoopholeDo
     * @author poet_wei
     * @date 2023/9/8
     */
    @RequestMapping(path = "/saveRepairOrder", method = RequestMethod.POST)
    public ApiResponse<LoLoopholeDo> saveRepairOrder(@RequestBody LoLoopholeDto loopholeDto) {
        LoLoopholeDo loLoopholeDo = LoLoopholeDtoDoConverter.INSTANCE.getLoopholeDo(loopholeDto);
        loLoopholeDo.setUpdateBy(ThreadLocalUtil.getUserInfo().getUserId());
        return ApiResponse.success(loopholeApplication.saveRepairOrder(loLoopholeDo));
    }
}
