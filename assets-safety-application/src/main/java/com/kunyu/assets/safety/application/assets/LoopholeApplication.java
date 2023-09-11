/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.application.assets;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.domain.service.loophole.LoopholeDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author poet_wei
 * @description
 * @date 2023-09-06 10:50
 */
@Service
public class LoopholeApplication {


    @Autowired
    private LoopholeDomain loopholeDomain;

    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 漏洞信息条件分页查询
     * @author poet_wei
     * @date 2023/9/6
     */
    public PageInfo<LoLoopholeDo> listingList(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        return loopholeDomain.listingList(loLoopholeSearchDo, pageNum, pageSize);
    }

    /**
     * @description 新增漏洞信息
     *
     * @param loLoopholeDo
     * @return LoLoopholeDo
     * @author poet_wei
     * @date 2023/9/6
     */
    public LoLoopholeDo saveLoophole(LoLoopholeDo loLoopholeDo) {
        return loopholeDomain.saveLoophole(loLoopholeDo);
    }


    /**
     * @description 修复工单信息查询--用户端
     *
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @author poet_wei
     * @date 2023/9/8
     */
    public PageInfo<LoLoopholeDo> selectRepairWorkOrder(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        return loopholeDomain.selectRepairWorkOrder(loLoopholeSearchDo, pageNum, pageSize);
    }

    /**
     * @description 修复工单审批查询--管理端
     *
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @author poet_wei
     * @date 2023/9/8
     */
    public PageInfo<LoLoopholeDo> selectApproveOrder(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        return loopholeDomain.selectApproveOrder(loLoopholeSearchDo, pageNum, pageSize);
    }

    /**
     * @description 创建修复工单--管理端
     *
     * @param loLoopholeDo pageNum pageSize
     * @return LoLoopholeDo
     * @author poet_wei
     * @date 2023/9/8
     */
    public LoLoopholeDo saveRepairOrder(LoLoopholeDo loLoopholeDo) {
        return loopholeDomain.saveRepairOrder(loLoopholeDo);
    }
}
