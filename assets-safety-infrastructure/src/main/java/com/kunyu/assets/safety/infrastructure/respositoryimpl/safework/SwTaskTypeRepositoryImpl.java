/*
 * Copyright (c) Shanghai Qitong Information Technology Co., LTD 2023-2099
 */

package com.kunyu.assets.safety.infrastructure.respositoryimpl.safework;

import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeDo;
import com.kunyu.assets.safety.domain.model.safework.SwTaskTypeSearchDo;
import com.kunyu.assets.safety.domain.respository.safework.ISwTaskTypeRepository;
import com.kunyu.assets.safety.infrastructure.converter.safework.SwDoPoConverter;
import com.kunyu.assets.safety.infrastructure.dao.safework.SwTaskTypeMapper;
import com.kunyu.assets.safety.infrastructure.po.safework.SwTaskTypePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务类型仓储接口实现类
 *
 * @author yangliu
 * @date 2023-09-07
 */
@Repository
public class SwTaskTypeRepositoryImpl implements ISwTaskTypeRepository {

    @Autowired
    private SwTaskTypeMapper taskTypeMapper;

    @Override
    public List<SwTaskTypeDo> taskTypeList(SwTaskTypeSearchDo searchDo) {
        return SwDoPoConverter.INSTANCE.getSwTaskTypeDos(taskTypeMapper.taskTypeList(searchDo));
    }

    @Override
    public SwTaskTypeDo addTaskType(SwTaskTypeDo taskTypeDo) {
        SwTaskTypePo taskTypePo = SwDoPoConverter.INSTANCE.getSwTaskTypePo(taskTypeDo);
        taskTypeMapper.addTaskType(taskTypePo);
        taskTypeDo.setId(taskTypePo.getId());
        return taskTypeDo;
    }

    @Override
    public boolean updateTaskType(SwTaskTypeDo taskTypeDo) {
        return taskTypeMapper.updateTaskType(SwDoPoConverter.INSTANCE.getSwTaskTypePo(taskTypeDo)) > 0 ? true : false;
    }

    @Override
    public boolean deleteTaskTypeById(Integer id, int status, String userId) {
        return taskTypeMapper.deleteTaskTypeById(id, status, userId) > 0 ? true : false;
    }

    @Override
    public SwTaskTypeDo getTaskTypeById(Integer id) {
        return SwDoPoConverter.INSTANCE.getSwTaskTypeDo(taskTypeMapper.getTaskTypeById(id));
    }
}
