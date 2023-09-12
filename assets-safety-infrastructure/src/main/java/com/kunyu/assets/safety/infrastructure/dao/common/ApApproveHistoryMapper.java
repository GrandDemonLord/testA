package com.kunyu.assets.safety.infrastructure.dao.common;

import com.kunyu.assets.safety.domain.model.common.ApApproveHistorySearchDo;
import com.kunyu.assets.safety.infrastructure.po.common.ApApproveHistoryPo;

import java.util.List;

/**
 * 审批记录持久层接口
 *
 * @author yangliu
 * @date 2023-09-07
 */
public interface ApApproveHistoryMapper {

    /**
     * 批量插入审批历史
     *
     * @param apApproveHistoryPos apApproveHistoryPos
     * @return int
     * @author yangliu
     * @date 2023/9/12
     */
    int addApproveHistory(List<ApApproveHistoryPo> apApproveHistoryPos);

    /**
     * 审批历史查询
     *
     * @param searchDo searchDo
     * @return List ApApproveHistoryDo
     * @author yangliu
     * @date 2023/9/12
     */
    List<ApApproveHistoryPo> historyList(ApApproveHistorySearchDo searchDo);

    /**
     * 审批历史查询 单条
     *
     * @param searchDo searchDo
     * @return ApApproveHistoryPo
     * @author yangliu
     * @date 2023/9/12
     */
    ApApproveHistoryPo history(ApApproveHistorySearchDo searchDo);

    /**
     * 更新审批历史
     *
     * @param historyPo
     * @return int
     * @author yangliu
     * @date 2023/9/12
     */
    int updateHistory(ApApproveHistoryPo historyPo);
}




