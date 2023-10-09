/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.application.loophole;

import com.github.pagehelper.PageInfo;
import com.kunyu.assets.safety.common.enums.common.UserDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeDo;
import com.kunyu.assets.safety.domain.model.loophole.LoLoopholeSearchDo;
import com.kunyu.assets.safety.domain.service.loophole.LoopholeDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageInfo<LoLoopholeDo> selectLoophole(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        return loopholeDomain.selectLoophole(loLoopholeSearchDo, pageNum, pageSize);
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
    public LoLoopholeDo saveRepairOrder(LoLoopholeDo loLoopholeDo, LoLoopholeSearchDo loLoopholeSearchDo) {
        return loopholeDomain.saveRepairOrder(loLoopholeDo, loLoopholeSearchDo);
    }

    /**
     * @param loLoopholeSearchDo pageNum pageSize
     * @return LoLoopholeDo
     * @description 漏洞信息条件分页查询
     * @author poet_wei
     * @date 2023/9/6
     */
    public PageInfo<LoLoopholeDo> adminSelectLoophole(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        return loopholeDomain.adminSelectLoophole(loLoopholeSearchDo, pageNum, pageSize);
    }

    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告开始
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean loopholeReportStart(Integer id, String updateBy) {
        return loopholeDomain.loopholeReportStart(id, updateBy);
    }

    /**
     * @param id 漏洞id
     * @return Boolean
     * @description 漏洞报告结束
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean loopholeReportEnd(Integer id, String userId) {
        return loopholeDomain.loopholeReportEnd(id, userId);
    }

    /**
     * @param loLoopholeSearchDo 工单id updateBy 更新人
     * @return LoLoopholeDo
     * @description 漏洞工单审批通过
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean approved(LoLoopholeSearchDo loLoopholeSearchDo) {
        return loopholeDomain.approved(loLoopholeSearchDo);
    }

    /**
     * @param loLoopholeSearchDo 工单id
     * @return LoLoopholeDo
     * @description 漏洞工单审批驳回
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean rejection(LoLoopholeSearchDo loLoopholeSearchDo) {
        return loopholeDomain.rejection(loLoopholeSearchDo);
    }

    /**
     * @param loLoopholeSearchDo 漏洞id
     * @return LoLoopholeDo
     * @description 漏洞信息忽略
     * @author poet_wei
     * @date 2023/9/12
     */
    public Boolean ignore(LoLoopholeSearchDo loLoopholeSearchDo) {
        return loopholeDomain.ignore(loLoopholeSearchDo);
    }

    /**
     * @param
     * @return LoLoopholeDo
     * @description 查询代办任务
     * @author poet_wei
     * @date 2023/9/15
     */
    public PageInfo<LoLoopholeDo> selectTaskQuery(LoLoopholeSearchDo loLoopholeSearchDo, int pageNum, int pageSize) {
        return loopholeDomain.selectTaskQuery(loLoopholeSearchDo, pageNum, pageSize);
    }

    /**
     * @param id     漏洞id
     * @param userId 用户id
     * @return
     */
    public Boolean deleteReport(Integer id, String userId) {
        return loopholeDomain.deleteReport(id, userId);
    }

    /**
     * @param id loopholeReportId loopholeReportName userId
     * @return null
     * @description 漏洞报告上传
     * @author poet_wei
     * @date 2023/9/19
     */
    public int upload(Integer id, String loopholeReportId, String loopholeReportName, String userId) {
        return loopholeDomain.upload(id, loopholeReportId, loopholeReportName, userId);
    }

    /**
     * @param id userId
     * @return null
     * @description 对数据进行校验
     * @author poet_wei
     * @date 2023/9/20
     */
    public void verifyLoopholeReportStatus(Integer id, String userId) {
        loopholeDomain.verifyLoopholeReportStatus(id, userId);
    }

    /**
     * 新增漏洞批量导入 批量导入
     *
     * @param loLoopholeDos userId
     * @return LoLoopholeDo
     */
    public List<LoLoopholeDo> batchSaveLoophole(List<LoLoopholeDo> loLoopholeDos, String userId) {
       return loopholeDomain.batchSaveLoophole(loLoopholeDos, userId);
    }


    /**
     * @description 获取负责人的单位信息
     *
     * @param userName 负责人名称
     * @return UserDo
     * @author poet_wei
     * @date 2023/9/21
     */
    public UserDo getUserInfo(String userName) {
        return loopholeDomain.getUserInfo(userName);
    }
}
