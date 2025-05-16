package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.core.domain.entity.SysDept;
import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.manage.model.domain.ClassInfo;
import com.lz.manage.model.domain.TrainingInfo;
import com.lz.manage.model.vo.trainingSelectedInfo.TrainingSelectStatics;
import com.lz.manage.service.IClassInfoService;
import com.lz.manage.service.ITrainingInfoService;
import com.lz.system.service.ISysDeptService;
import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.TrainingSelectedInfoMapper;
import com.lz.manage.model.domain.TrainingSelectedInfo;
import com.lz.manage.service.ITrainingSelectedInfoService;
import com.lz.manage.model.dto.trainingSelectedInfo.TrainingSelectedInfoQuery;
import com.lz.manage.model.vo.trainingSelectedInfo.TrainingSelectedInfoVo;

/**
 * 实训选择Service业务层处理
 *
 * @author YY
 * @date 2025-05-12
 */
@Service
public class TrainingSelectedInfoServiceImpl extends ServiceImpl<TrainingSelectedInfoMapper, TrainingSelectedInfo> implements ITrainingSelectedInfoService {
    @Resource
    private TrainingSelectedInfoMapper trainingSelectedInfoMapper;

    @Resource
    private ITrainingInfoService trainingInfoService;

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysDeptService deptService;

    @Resource
    private IClassInfoService classInfoService;

    //region mybatis代码

    /**
     * 查询实训选择
     *
     * @param selectedId 实训选择主键
     * @return 实训选择
     */
    @Override
    public TrainingSelectedInfo selectTrainingSelectedInfoBySelectedId(Long selectedId) {
        return trainingSelectedInfoMapper.selectTrainingSelectedInfoBySelectedId(selectedId);
    }

    /**
     * 查询实训选择列表
     *
     * @param trainingSelectedInfo 实训选择
     * @return 实训选择
     */
    @Override
    public List<TrainingSelectedInfo> selectTrainingSelectedInfoList(TrainingSelectedInfo trainingSelectedInfo) {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId()) && SecurityUtils.hasRole("student")) {
            trainingSelectedInfo.setUserId(SecurityUtils.getUserId());
        }
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId()) && SecurityUtils.hasRole("teacher")) {
            trainingSelectedInfo.setTeacherId(SecurityUtils.getUserId());
        }
        List<TrainingSelectedInfo> trainingSelectedInfos = trainingSelectedInfoMapper.selectTrainingSelectedInfoList(trainingSelectedInfo);
        for (TrainingSelectedInfo info : trainingSelectedInfos) {
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getUserName());
            }
            SysDept sysDept = deptService.selectDeptById(info.getDeptId());
            if (StringUtils.isNotNull(sysDept)) {
                info.setDeptName(sysDept.getDeptName());
            }
            TrainingInfo trainingInfo = trainingInfoService.selectTrainingInfoByTrainingId(info.getTrainingId());
            if (StringUtils.isNotNull(trainingInfo)) {
                info.setTrainingTitle(trainingInfo.getTrainingTitle());
            }
            SysUser teacher = userService.selectUserById(info.getTeacherId());
            if (StringUtils.isNotNull(teacher)) {
                info.setTeacherName(teacher.getUserName());
            }
            ClassInfo classInfo = classInfoService.selectClassInfoByClassId(info.getClassId());
            if (StringUtils.isNotNull(classInfo)) {
                info.setClassName(classInfo.getClassName());
            }
        }
        return trainingSelectedInfos;
    }

    /**
     * 新增实训选择
     *
     * @param trainingSelectedInfo 实训选择
     * @return 结果
     */
    @Override
    public int insertTrainingSelectedInfo(TrainingSelectedInfo trainingSelectedInfo) {
        //查询实训是否存在
        TrainingInfo trainingInfo = trainingInfoService.selectTrainingInfoByTrainingId(trainingSelectedInfo.getTrainingId());
        if (StringUtils.isNull(trainingInfo)) {
            throw new ServiceException("实训不存在！！！");
        }
        trainingSelectedInfo.setClassId(trainingInfo.getClassId());
        trainingSelectedInfo.setStatus("0");
        trainingSelectedInfo.setSubmitStatus("0");
        trainingSelectedInfo.setDeptId(trainingInfo.getDeptId());
        trainingSelectedInfo.setTeacherId(trainingInfo.getUserId());
        trainingSelectedInfo.setUserId(SecurityUtils.getUserId());
        trainingSelectedInfo.setCreateTime(DateUtils.getNowDate());
        return trainingSelectedInfoMapper.insertTrainingSelectedInfo(trainingSelectedInfo);
    }

    /**
     * 修改实训选择
     *
     * @param trainingSelectedInfo 实训选择
     * @return 结果
     */
    @Override
    public int updateTrainingSelectedInfo(TrainingSelectedInfo trainingSelectedInfo) {
        //如果评分大于60
        if (StringUtils.isNotNull(trainingSelectedInfo.getScore())) {
            if (trainingSelectedInfo.getScore().compareTo(new BigDecimal(60)) > 0) {
                trainingSelectedInfo.setStatus("1");
            } else if (trainingSelectedInfo.getScore().equals(new BigDecimal(0))) {
                trainingSelectedInfo.setSubmitStatus("0");
            } else {
                trainingSelectedInfo.setStatus("2");
            }
        }
        trainingSelectedInfo.setUpdateTime(DateUtils.getNowDate());
        return trainingSelectedInfoMapper.updateTrainingSelectedInfo(trainingSelectedInfo);
    }

    /**
     * 批量删除实训选择
     *
     * @param selectedIds 需要删除的实训选择主键
     * @return 结果
     */
    @Override
    public int deleteTrainingSelectedInfoBySelectedIds(Long[] selectedIds) {
        return trainingSelectedInfoMapper.deleteTrainingSelectedInfoBySelectedIds(selectedIds);
    }

    /**
     * 删除实训选择信息
     *
     * @param selectedId 实训选择主键
     * @return 结果
     */
    @Override
    public int deleteTrainingSelectedInfoBySelectedId(Long selectedId) {
        return trainingSelectedInfoMapper.deleteTrainingSelectedInfoBySelectedId(selectedId);
    }

    //endregion
    @Override
    public QueryWrapper<TrainingSelectedInfo> getQueryWrapper(TrainingSelectedInfoQuery trainingSelectedInfoQuery) {
        QueryWrapper<TrainingSelectedInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = trainingSelectedInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long selectedId = trainingSelectedInfoQuery.getSelectedId();
        queryWrapper.eq(StringUtils.isNotNull(selectedId), "selected_id", selectedId);

        Long trainingId = trainingSelectedInfoQuery.getTrainingId();
        queryWrapper.eq(StringUtils.isNotNull(trainingId), "training_id", trainingId);

        String content = trainingSelectedInfoQuery.getContent();
        queryWrapper.eq(StringUtils.isNotEmpty(content), "content", content);


        String submitStatus = trainingSelectedInfoQuery.getSubmitStatus();
        queryWrapper.eq(StringUtils.isNotEmpty(submitStatus), "submit_status", submitStatus);

        Date submitTime = trainingSelectedInfoQuery.getSubmitTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginSubmitTime")) && StringUtils.isNotNull(params.get("endSubmitTime")), "submit_time", params.get("beginSubmitTime"), params.get("endSubmitTime"));


        String status = trainingSelectedInfoQuery.getStatus();
        queryWrapper.eq(StringUtils.isNotEmpty(status), "status", status);

        Long teacherId = trainingSelectedInfoQuery.getTeacherId();
        queryWrapper.eq(StringUtils.isNotNull(teacherId), "teacher_id", teacherId);

        Long userId = trainingSelectedInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Long deptId = trainingSelectedInfoQuery.getDeptId();
        queryWrapper.eq(StringUtils.isNotNull(deptId), "dept_id", deptId);

        Date createTime = trainingSelectedInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updatedBy = trainingSelectedInfoQuery.getUpdatedBy();
        queryWrapper.like(StringUtils.isNotEmpty(updatedBy), "updated_by", updatedBy);

        Date updateTime = trainingSelectedInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<TrainingSelectedInfoVo> convertVoList(List<TrainingSelectedInfo> trainingSelectedInfoList) {
        if (StringUtils.isEmpty(trainingSelectedInfoList)) {
            return Collections.emptyList();
        }
        return trainingSelectedInfoList.stream().map(TrainingSelectedInfoVo::objToVo).collect(Collectors.toList());
    }

    @Override
    public TrainingSelectStatics statics(TrainingSelectedInfo trainingSelectedInfo) {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId()) && SecurityUtils.hasRole("student")) {
            trainingSelectedInfo.setUserId(SecurityUtils.getUserId());
        }
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId()) && SecurityUtils.hasRole("teacher")) {
            trainingSelectedInfo.setTeacherId(SecurityUtils.getUserId());
        }
        //统计平均分
        BigDecimal avgScore = trainingSelectedInfoMapper.staticsAvgScore(trainingSelectedInfo);
        if (StringUtils.isNull(avgScore)) {
            avgScore = new BigDecimal(0);
        }
        //统计通过率
        BigDecimal passRate = trainingSelectedInfoMapper.staticsPassRate(trainingSelectedInfo);
        if (StringUtils.isNull(passRate)) {
            passRate = new BigDecimal(0);
        }
        //统计提交率
        BigDecimal submitRate = trainingSelectedInfoMapper.staticsSubmitRate(trainingSelectedInfo);
        if (StringUtils.isNull(submitRate)) {
            submitRate = new BigDecimal(0);
        }
        return new TrainingSelectStatics(avgScore, passRate, submitRate);
    }

}
