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
     * @param loLoopholeDo
     * @return LoLoopholeDo
     * @description 新增漏洞信息
     * @author poet_wei
     * @date 2023/9/6
     */
    public LoLoopholeDo saveLoophole(LoLoopholeDo loLoopholeDo) {
        return loopholeDomain.saveLoophole(loLoopholeDo);
    }


    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 修复工单信息查询--用户端
     * @author poet_wei
     * @date 2023/9/8
     */
    public PageInfo<LoLoopholeDo> selectRepairWorkOrder(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        return loopholeDomain.selectRepairWorkOrder(loLoopholeSearchDo, pageNum, pageSize);
    }

    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 修复工单审批查询--管理端
     * @author poet_wei
     * @date 2023/9/8
     */
    public PageInfo<LoLoopholeDo> selectApproveOrder(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        return loopholeDomain.selectApproveOrder(loLoopholeSearchDo, pageNum, pageSize);
    }

    /**
     * @param loLoopholeDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 创建修复工单--管理端
     * @author poet_wei
     * @date 2023/9/8
     */
    public LoLoopholeDo saveRepairOrder(LoLoopholeDo loLoopholeDo) {
        return loopholeDomain.saveRepairOrder(loLoopholeDo);
    }

    /**
     * @param id 漏洞id
     * @return LoLoopholeDo
     * @description 创建修复工单--管理端
     * @author poet_wei
     * @date 2023/9/11
     */
    public LoLoopholeDo selectLoopholeById(Integer id) {
        return loopholeDomain.selectLoopholeById(id);
    }

    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 漏洞信息条件分页查询
     * @author poet_wei
     * @date 2023/9/6
     */
    public PageInfo<LoLoopholeDo> adminListingList(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        return loopholeDomain.adminListingList(loLoopholeSearchDo, pageNum, pageSize);
    }

    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告开始
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean loopholeReortStart(Integer id, String updateBy) {
        return loopholeDomain.loopholeReortStart(id, updateBy);
    }

    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告结束
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean loopholeReortEnd(Integer id, String updateBy) {
        return loopholeDomain.loopholeReortEnd(id, updateBy);
    }

    /**
     * @param id 工单id updateBy 更新人
     * @return LoLoopholeDo
     * @description 漏洞工单审批通过
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean approved(Integer id, String updateId, String updateName) {
        return loopholeDomain.approved(id, updateId, updateName);
    }

    /**
     * @param id 工单id
     * @return LoLoopholeDo
     * @description 漏洞工单审批驳回
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean rejection(Integer id, String updateId, String updateName) {
        return loopholeDomain.rejection(id, updateId, updateName);
    }
}
