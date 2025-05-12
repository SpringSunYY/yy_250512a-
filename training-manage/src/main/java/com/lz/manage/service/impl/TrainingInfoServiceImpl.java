package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.annotation.DataScope;
import com.lz.common.core.domain.entity.SysDept;
import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.system.service.ISysDeptService;
import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.TrainingInfoMapper;
import com.lz.manage.model.domain.TrainingInfo;
import com.lz.manage.service.ITrainingInfoService;
import com.lz.manage.model.dto.trainingInfo.TrainingInfoQuery;
import com.lz.manage.model.vo.trainingInfo.TrainingInfoVo;

/**
 * 实训信息Service业务层处理
 *
 * @author YY
 * @date 2025-05-12
 */
@Service
public class TrainingInfoServiceImpl extends ServiceImpl<TrainingInfoMapper, TrainingInfo> implements ITrainingInfoService {
    @Resource
    private TrainingInfoMapper trainingInfoMapper;

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysDeptService deptService;
    //region mybatis代码

    /**
     * 查询实训信息
     *
     * @param trainingId 实训信息主键
     * @return 实训信息
     */
    @Override
    public TrainingInfo selectTrainingInfoByTrainingId(Long trainingId) {
        return trainingInfoMapper.selectTrainingInfoByTrainingId(trainingId);
    }

    /**
     * 查询实训信息列表
     *
     * @param trainingInfo 实训信息
     * @return 实训信息
     */
    @DataScope(deptAlias = "tb_training_info",userAlias = "tb_training_info")
    @Override
    public List<TrainingInfo> selectTrainingInfoList(TrainingInfo trainingInfo) {
        List<TrainingInfo> trainingInfos = trainingInfoMapper.selectTrainingInfoList(trainingInfo);
        for (TrainingInfo info : trainingInfos) {
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getUserName());
            }
            SysDept sysDept = deptService.selectDeptById(info.getDeptId());
            if (StringUtils.isNotNull(sysDept)) {
                info.setDeptName(sysDept.getDeptName());
            }
        }
        return trainingInfos;
    }

    /**
     * 新增实训信息
     *
     * @param trainingInfo 实训信息
     * @return 结果
     */
    @Override
    public int insertTrainingInfo(TrainingInfo trainingInfo) {
        trainingInfo.setUserId(SecurityUtils.getUserId());
        trainingInfo.setDeptId(SecurityUtils.getDeptId());
        trainingInfo.setCreateTime(DateUtils.getNowDate());
        return trainingInfoMapper.insertTrainingInfo(trainingInfo);
    }

    /**
     * 修改实训信息
     *
     * @param trainingInfo 实训信息
     * @return 结果
     */
    @Override
    public int updateTrainingInfo(TrainingInfo trainingInfo) {
        trainingInfo.setUpdatedBy(SecurityUtils.getUsername());
        trainingInfo.setUpdateTime(DateUtils.getNowDate());
        return trainingInfoMapper.updateTrainingInfo(trainingInfo);
    }

    /**
     * 批量删除实训信息
     *
     * @param trainingIds 需要删除的实训信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainingInfoByTrainingIds(Long[] trainingIds) {
        return trainingInfoMapper.deleteTrainingInfoByTrainingIds(trainingIds);
    }

    /**
     * 删除实训信息信息
     *
     * @param trainingId 实训信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainingInfoByTrainingId(Long trainingId) {
        return trainingInfoMapper.deleteTrainingInfoByTrainingId(trainingId);
    }

    //endregion
    @Override
    public QueryWrapper<TrainingInfo> getQueryWrapper(TrainingInfoQuery trainingInfoQuery) {
        QueryWrapper<TrainingInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = trainingInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long trainingId = trainingInfoQuery.getTrainingId();
        queryWrapper.eq(StringUtils.isNotNull(trainingId), "training_id", trainingId);

        String trainingTitle = trainingInfoQuery.getTrainingTitle();
        queryWrapper.like(StringUtils.isNotEmpty(trainingTitle), "training_title", trainingTitle);

        Long userId = trainingInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Long deptId = trainingInfoQuery.getDeptId();
        queryWrapper.eq(StringUtils.isNotNull(deptId), "dept_id", deptId);

        Date createTime = trainingInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updatedBy = trainingInfoQuery.getUpdatedBy();
        queryWrapper.like(StringUtils.isNotEmpty(updatedBy), "updated_by", updatedBy);

        Date updateTime = trainingInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<TrainingInfoVo> convertVoList(List<TrainingInfo> trainingInfoList) {
        if (StringUtils.isEmpty(trainingInfoList)) {
            return Collections.emptyList();
        }
        return trainingInfoList.stream().map(TrainingInfoVo::objToVo).collect(Collectors.toList());
    }

}
