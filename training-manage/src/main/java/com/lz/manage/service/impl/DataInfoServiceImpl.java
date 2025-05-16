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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.manage.model.domain.ArchiveInfo;
import com.lz.manage.model.domain.ClassInfo;
import com.lz.manage.service.IClassInfoService;
import com.lz.system.service.ISysDeptService;
import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.DataInfoMapper;
import com.lz.manage.model.domain.DataInfo;
import com.lz.manage.service.IDataInfoService;
import com.lz.manage.model.dto.dataInfo.DataInfoQuery;
import com.lz.manage.model.vo.dataInfo.DataInfoVo;

/**
 * 资料信息Service业务层处理
 *
 * @author YY
 * @date 2025-05-16
 */
@Service
public class DataInfoServiceImpl extends ServiceImpl<DataInfoMapper, DataInfo> implements IDataInfoService {
    @Resource
    private DataInfoMapper dataInfoMapper;


    @Resource
    private ISysUserService userService;

    @Resource
    private ISysDeptService deptService;

    @Resource
    private IClassInfoService classInfoService;

    //region mybatis代码

    /**
     * 查询资料信息
     *
     * @param dataId 资料信息主键
     * @return 资料信息
     */
    @Override
    public DataInfo selectDataInfoByDataId(Long dataId) {
        return dataInfoMapper.selectDataInfoByDataId(dataId);
    }

    /**
     * 查询资料信息列表
     *
     * @param dataInfo 资料信息
     * @return 资料信息
     */
    @Override
    public List<DataInfo> selectDataInfoList(DataInfo dataInfo) {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId()) && SecurityUtils.hasRole("student")
                || !SecurityUtils.isAdmin(SecurityUtils.getUserId()) && SecurityUtils.hasRole("teacher")) {
            Long classId = SecurityUtils.getLoginUser().getUser().getClassId();
            if (StringUtils.isNull(classId)) {
                classId = 0L;
            }
            dataInfo.setClassId(classId);
        }
        List<DataInfo> dataInfos = dataInfoMapper.selectDataInfoList(dataInfo);
        for (DataInfo info : dataInfos) {
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getUserName());
            }
            SysDept sysDept = deptService.selectDeptById(info.getDeptId());
            if (StringUtils.isNotNull(sysDept)) {
                info.setDeptName(sysDept.getDeptName());
            }
            ClassInfo classInfo = classInfoService.selectClassInfoByClassId(info.getClassId());
            if (StringUtils.isNotNull(classInfo)) {
                info.setClassName(classInfo.getClassName());
            }
        }
        return dataInfos;
    }

    /**
     * 新增资料信息
     *
     * @param dataInfo 资料信息
     * @return 结果
     */
    @Override
    public int insertDataInfo(DataInfo dataInfo) {
        checkClass(dataInfo);
        dataInfo.setUserId(SecurityUtils.getUserId());
        dataInfo.setCreateTime(DateUtils.getNowDate());
        return dataInfoMapper.insertDataInfo(dataInfo);
    }

    private void checkClass(DataInfo dataInfo) {
        //查询班级
        ClassInfo classInfo = classInfoService.selectClassInfoByClassId(dataInfo.getClassId());
        if (StringUtils.isNull(classInfo)) {
            throw new ServiceException("该学生未绑定班级！！！");
        }
        if (StringUtils.isNull(classInfo.getDeptId())) {
            throw new ServiceException("该班级未绑定学院！！！");
        }
        dataInfo.setDeptId(classInfo.getDeptId());
        dataInfo.setClassId(dataInfo.getClassId());
    }

    /**
     * 修改资料信息
     *
     * @param dataInfo 资料信息
     * @return 结果
     */
    @Override
    public int updateDataInfo(DataInfo dataInfo) {
        checkClass(dataInfo);
        dataInfo.setUpdatedBy(SecurityUtils.getUsername());
        dataInfo.setUpdateTime(DateUtils.getNowDate());
        return dataInfoMapper.updateDataInfo(dataInfo);
    }

    /**
     * 批量删除资料信息
     *
     * @param dataIds 需要删除的资料信息主键
     * @return 结果
     */
    @Override
    public int deleteDataInfoByDataIds(Long[] dataIds) {
        return dataInfoMapper.deleteDataInfoByDataIds(dataIds);
    }

    /**
     * 删除资料信息信息
     *
     * @param dataId 资料信息主键
     * @return 结果
     */
    @Override
    public int deleteDataInfoByDataId(Long dataId) {
        return dataInfoMapper.deleteDataInfoByDataId(dataId);
    }

    //endregion
    @Override
    public QueryWrapper<DataInfo> getQueryWrapper(DataInfoQuery dataInfoQuery) {
        QueryWrapper<DataInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = dataInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long dataId = dataInfoQuery.getDataId();
        queryWrapper.eq(StringUtils.isNotNull(dataId), "data_id", dataId);

        String dataTitle = dataInfoQuery.getDataTitle();
        queryWrapper.eq(StringUtils.isNotEmpty(dataTitle), "data_title", dataTitle);

        Long userId = dataInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Long deptId = dataInfoQuery.getDeptId();
        queryWrapper.eq(StringUtils.isNotNull(deptId), "dept_id", deptId);

        Long classId = dataInfoQuery.getClassId();
        queryWrapper.eq(StringUtils.isNotNull(classId), "class_id", classId);

        Date createTime = dataInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updatedBy = dataInfoQuery.getUpdatedBy();
        queryWrapper.like(StringUtils.isNotEmpty(updatedBy), "updated_by", updatedBy);

        Date updateTime = dataInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<DataInfoVo> convertVoList(List<DataInfo> dataInfoList) {
        if (StringUtils.isEmpty(dataInfoList)) {
            return Collections.emptyList();
        }
        return dataInfoList.stream().map(DataInfoVo::objToVo).collect(Collectors.toList());
    }

}
