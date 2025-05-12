package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.ArchiveInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 学生档案Mapper接口
 * 
 * @author YY
 * @date 2025-05-12
 */
public interface ArchiveInfoMapper extends BaseMapper<ArchiveInfo>
{
    /**
     * 查询学生档案
     * 
     * @param archiveId 学生档案主键
     * @return 学生档案
     */
    public ArchiveInfo selectArchiveInfoByArchiveId(Long archiveId);

    /**
     * 查询学生档案列表
     * 
     * @param archiveInfo 学生档案
     * @return 学生档案集合
     */
    public List<ArchiveInfo> selectArchiveInfoList(ArchiveInfo archiveInfo);

    /**
     * 新增学生档案
     * 
     * @param archiveInfo 学生档案
     * @return 结果
     */
    public int insertArchiveInfo(ArchiveInfo archiveInfo);

    /**
     * 修改学生档案
     * 
     * @param archiveInfo 学生档案
     * @return 结果
     */
    public int updateArchiveInfo(ArchiveInfo archiveInfo);

    /**
     * 删除学生档案
     * 
     * @param archiveId 学生档案主键
     * @return 结果
     */
    public int deleteArchiveInfoByArchiveId(Long archiveId);

    /**
     * 批量删除学生档案
     * 
     * @param archiveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArchiveInfoByArchiveIds(Long[] archiveIds);
}
