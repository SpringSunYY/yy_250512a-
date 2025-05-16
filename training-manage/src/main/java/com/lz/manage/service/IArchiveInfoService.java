package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.ArchiveInfo;
import com.lz.manage.model.vo.archiveInfo.ArchiveInfoVo;
import com.lz.manage.model.dto.archiveInfo.ArchiveInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 学生档案Service接口
 *
 * @author YY
 * @date 2025-05-12
 */
public interface IArchiveInfoService extends IService<ArchiveInfo>
{
    //region mybatis代码
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
     * 批量删除学生档案
     *
     * @param archiveIds 需要删除的学生档案主键集合
     * @return 结果
     */
    public int deleteArchiveInfoByArchiveIds(Long[] archiveIds);

    /**
     * 删除学生档案信息
     *
     * @param archiveId 学生档案主键
     * @return 结果
     */
    public int deleteArchiveInfoByArchiveId(Long archiveId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param archiveInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<ArchiveInfo> getQueryWrapper(ArchiveInfoQuery archiveInfoQuery);

    /**
     * 转换vo
     *
     * @param archiveInfoList ArchiveInfo集合
     * @return ArchiveInfoVO集合
     */
    List<ArchiveInfoVo> convertVoList(List<ArchiveInfo> archiveInfoList);

    /**
     * 导入学生档案信息
     * @author: YY
     * @method: importArchiveInfo
     * @date: 2025/5/16 20:16
     * @param list 档案列表
     * @return String
     **/
    String importArchiveInfo(List<ArchiveInfo> list);
}
