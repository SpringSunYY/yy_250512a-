package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.ClassInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 班级信息Mapper接口
 * 
 * @author YY
 * @date 2025-05-16
 */
public interface ClassInfoMapper extends BaseMapper<ClassInfo>
{
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
     * 删除班级信息
     * 
     * @param classId 班级信息主键
     * @return 结果
     */
    public int deleteClassInfoByClassId(Long classId);

    /**
     * 批量删除班级信息
     * 
     * @param classIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClassInfoByClassIds(Long[] classIds);
}
