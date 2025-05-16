package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.ClassInfo;
import com.lz.manage.model.vo.classInfo.ClassInfoVo;
import com.lz.manage.model.dto.classInfo.ClassInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 班级信息Service接口
 * 
 * @author YY
 * @date 2025-05-16
 */
public interface IClassInfoService extends IService<ClassInfo>
{
    //region mybatis代码
    /**
     * 查询班级信息
     * 
     * @param classId 班级信息主键
     * @return 班级信息
     */
    public ClassInfo selectClassInfoByClassId(Long classId);

    /**
     * 查询班级信息列表
     * 
     * @param classInfo 班级信息
     * @return 班级信息集合
     */
    public List<ClassInfo> selectClassInfoList(ClassInfo classInfo);

    /**
     * 新增班级信息
     * 
     * @param classInfo 班级信息
     * @return 结果
     */
    public int insertClassInfo(ClassInfo classInfo);

    /**
     * 修改班级信息
     * 
     * @param classInfo 班级信息
     * @return 结果
     */
    public int updateClassInfo(ClassInfo classInfo);

    /**
     * 批量删除班级信息
     * 
     * @param classIds 需要删除的班级信息主键集合
     * @return 结果
     */
    public int deleteClassInfoByClassIds(Long[] classIds);

    /**
     * 删除班级信息信息
     * 
     * @param classId 班级信息主键
     * @return 结果
     */
    public int deleteClassInfoByClassId(Long classId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param classInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<ClassInfo> getQueryWrapper(ClassInfoQuery classInfoQuery);

    /**
     * 转换vo
     *
     * @param classInfoList ClassInfo集合
     * @return ClassInfoVO集合
     */
    List<ClassInfoVo> convertVoList(List<ClassInfo> classInfoList);
}
