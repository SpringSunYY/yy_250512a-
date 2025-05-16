package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.ClassInfoMapper;
import com.lz.manage.model.domain.ClassInfo;
import com.lz.manage.service.IClassInfoService;
import com.lz.manage.model.dto.classInfo.ClassInfoQuery;
import com.lz.manage.model.vo.classInfo.ClassInfoVo;

/**
 * 班级信息Service业务层处理
 * 
 * @author YY
 * @date 2025-05-16
 */
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements IClassInfoService
{
    @Resource
    private ClassInfoMapper classInfoMapper;

    //region mybatis代码
    /**
     * 查询班级信息
     * 
     * @param classId 班级信息主键
     * @return 班级信息
     */
    @Override
    public ClassInfo selectClassInfoByClassId(Long classId)
    {
        return classInfoMapper.selectClassInfoByClassId(classId);
    }

    /**
     * 查询班级信息列表
     * 
     * @param classInfo 班级信息
     * @return 班级信息
     */
    @Override
    public List<ClassInfo> selectClassInfoList(ClassInfo classInfo)
    {
        return classInfoMapper.selectClassInfoList(classInfo);
    }

    /**
     * 新增班级信息
     * 
     * @param classInfo 班级信息
     * @return 结果
     */
    @Override
    public int insertClassInfo(ClassInfo classInfo)
    {
        classInfo.setCreateTime(DateUtils.getNowDate());
        return classInfoMapper.insertClassInfo(classInfo);
    }

    /**
     * 修改班级信息
     * 
     * @param classInfo 班级信息
     * @return 结果
     */
    @Override
    public int updateClassInfo(ClassInfo classInfo)
    {
        classInfo.setUpdateTime(DateUtils.getNowDate());
        return classInfoMapper.updateClassInfo(classInfo);
    }

    /**
     * 批量删除班级信息
     * 
     * @param classIds 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClassInfoByClassIds(Long[] classIds)
    {
        return classInfoMapper.deleteClassInfoByClassIds(classIds);
    }

    /**
     * 删除班级信息信息
     * 
     * @param classId 班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClassInfoByClassId(Long classId)
    {
        return classInfoMapper.deleteClassInfoByClassId(classId);
    }
    //endregion
    @Override
    public QueryWrapper<ClassInfo> getQueryWrapper(ClassInfoQuery classInfoQuery){
        QueryWrapper<ClassInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = classInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long classId = classInfoQuery.getClassId();
        queryWrapper.eq( StringUtils.isNotNull(classId),"class_id",classId);

        String className = classInfoQuery.getClassName();
        queryWrapper.like(StringUtils.isNotEmpty(className) ,"class_name",className);

        Long userId = classInfoQuery.getUserId();
        queryWrapper.eq( StringUtils.isNotNull(userId),"user_id",userId);

        Long deptId = classInfoQuery.getDeptId();
        queryWrapper.eq( StringUtils.isNotNull(deptId),"dept_id",deptId);

        Date createTime = classInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updatedBy = classInfoQuery.getUpdatedBy();
        queryWrapper.like(StringUtils.isNotEmpty(updatedBy) ,"updated_by",updatedBy);

        Date updateTime = classInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<ClassInfoVo> convertVoList(List<ClassInfo> classInfoList) {
        if (StringUtils.isEmpty(classInfoList)) {
            return Collections.emptyList();
        }
        return classInfoList.stream().map(ClassInfoVo::objToVo).collect(Collectors.toList());
    }

}
